package com.bnn.pos

import com.bnn.pos.model.ResultModel

interface ConnectionListener {
	fun onResult(model: ResultModel)
	fun onError(model: ResultModel)

}