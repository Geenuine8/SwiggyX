package com.example.starwarx.network


/**
 * @author Yogesh Jangir.
 */

object ApiServices{

    val starWarApi: StarWarApi by lazy {
        RetrofitClient.instance.create(StarWarApi::class.java)
    }
}