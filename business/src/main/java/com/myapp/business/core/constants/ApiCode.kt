package com.myapp.business.core.constants

object ApiCode {
    val UNKNOWN = "-1"

    enum class ExampleEnum private constructor(val value: String) {
        SUCCESS(""),
        UNKNOWN(ApiCode.UNKNOWN);

        companion object {

            fun getFromValue(value: String): ExampleEnum {
                for (c in ExampleEnum.values()) {
                    if (c.value == value)
                        return c
                }
                return UNKNOWN
            }
        }

    }
}
