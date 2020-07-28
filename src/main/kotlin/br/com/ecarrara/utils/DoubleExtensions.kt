package br.com.ecarrara.utils

fun Double.format(digits: Int) = "%.${digits}f".format(this)