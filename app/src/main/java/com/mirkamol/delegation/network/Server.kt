package com.mirkamol.delegation.network

object Server {

    init {
        System.loadLibrary("keys")
    }
    external fun getDevelopment(): String


}