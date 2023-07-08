package com.example.mywaycompose.domain.model

data class DateServer(
    val year: String,
    val month:String,
    var day:String
)

fun String.toDateServer():DateServer{
    val splitDate = this.split("-")
    return DateServer(
        year = splitDate[2],
        month = splitDate[1],
        day = splitDate[0]
    )
}

fun DateServer.toDateString():String{
  return  "${this.day}-${this.month}-${this.year}"
}