package com.bnn.pos

import com.bnn.pos.Tools.authToken
import com.bnn.pos.Tools.parseResult
import com.bnn.pos.model.RequestModel
import com.bnn.pos.model.ResultModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

class ConnectionApi(var listener: ConnectionListener) {

	private var endPoint: String = ""
	private var client: OkHttpClient? = null

	interface AuthInterface {
		@POST
		fun getAuthResultModel(
			@Url url: String?, @Body requestModel: RequestModel?
		): Call<ResultModel>
	}

	interface ResultInterface {
		@POST
		fun getResultModel(
			@Url url: String?, @Body requestModel: RequestModel?
		): retrofit2.Call<ResultModel>
	}

	private fun setClient() {
		client = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
			.writeTimeout(120, TimeUnit.SECONDS).addInterceptor { chain ->
				val request: Request =
					chain.request().newBuilder().addHeader("Authorization", authToken ?: "").build()
				chain.proceed(request)
			}.readTimeout(60, TimeUnit.SECONDS).build()
	}


	fun setEndPoint(endPoint: String) {
		this.endPoint = endPoint
	}

	fun callApi(requestModel: RequestModel?) {
		if (requestModel == null) {
			return
		}

		setClient()

		/*requestModel.userFirebase = Tools.userFirebase
		requestModel.userMobile = Tools.userMobile
		requestModel.userCompany = Tools.userCompany
		requestModel.userDevice = Tools.userDevice*/

		Tools.showLog("callApi", String.format("%s", Gson().toJson(requestModel)))
		val retrofit: Retrofit = Retrofit.Builder().baseUrl(Tools.api).client(client!!)
			.addConverterFactory(GsonConverterFactory.create()).build()

		val resultInterface = retrofit.create(ResultInterface::class.java)
		resultInterface.getResultModel(Tools.api+endPoint, requestModel)
			.enqueue(object : Callback<ResultModel> {
				override fun onResponse(
					call: retrofit2.Call<ResultModel>, response: Response<ResultModel>
				) {
					val model = parseResult(response.body(), requestModel)
					processHeader(response)
					listener.onResult(model)
				}

				override fun onFailure(call: retrofit2.Call<ResultModel>, t: Throwable) {
					Tools.showLog("callback-error", t.message!!)
					val resultModel = ResultModel(0, t.message, requestModel.action)
					listener.onResult(resultModel)
				}

			})
	}

	private fun processHeader(response: Response<ResultModel>) {
		val headers = response.headers()
		if (headers["Authorization"] != null) {
			authToken = headers["Authorization"]
		}
	}


	fun authApi(requestModel: RequestModel?) {
		if (requestModel == null) {
			return
		}

		setClient()

		val retrofit = Retrofit.Builder().baseUrl(Tools.api).client(client!!)
			.addConverterFactory(GsonConverterFactory.create()).build()

		//init auth interface
		val authInterface = retrofit.create(AuthInterface::class.java)
			authInterface.getAuthResultModel(Tools.api+endPoint, requestModel)
			.enqueue(object : Callback<ResultModel> {
				override fun onResponse(call: Call<ResultModel>, response: Response<ResultModel>) {
					val model = parseResult(response.body(), requestModel)
					listener.onResult(model)
				}

				override fun onFailure(call: Call<ResultModel>, t: Throwable) {
					val model = ResultModel(0, t.message, requestModel.action)
					listener.onResult(model)
				}


			})
	}

}