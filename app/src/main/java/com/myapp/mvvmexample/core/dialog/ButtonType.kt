package com.myapp.mvvmexample.core.dialog

enum class ButtonType private constructor(val id: Int) {
    POSITIVE(0), NEGATIVE(1), NEUTRAL(2), CLOSE(3)
}