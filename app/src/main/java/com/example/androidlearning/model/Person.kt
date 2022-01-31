package com.example.androidlearning.model

import com.example.androidlearning.utils.isMajor

class Person(var firstname : String = "",
             var lastname : String = "",
             var age: Int? = null,
             var status : STATUS = STATUS.OTHER,
             var isAuthorized : Boolean = false){

    fun isMajor(): Boolean{
        age?.let{
            return it.isMajor()
        }

        return false
    }

}
