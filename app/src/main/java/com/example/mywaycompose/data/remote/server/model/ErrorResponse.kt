package com.example.mywaycompose.data.remote.server.model

data class ErrorResponse(
	val detail: String
)

data class DetailItem(
	val msg: String? = null,
	val loc: List<String?>? = null,
	val type: String? = null
)

