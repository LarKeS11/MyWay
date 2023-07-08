package com.example.mywaycompose.domain.model

data class EffectiveStat(
    val priority:Int = 0,
    val appliedMainTask:Boolean = false,
    val appliedSubtask:Boolean = false
)