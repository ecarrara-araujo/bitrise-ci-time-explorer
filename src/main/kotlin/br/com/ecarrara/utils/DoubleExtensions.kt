package br.com.ecarrara.utils

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun Double.formatAsRate(): String = "${(this * 100).format(2)}%"