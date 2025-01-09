//package com.example.authapp.data
//
//import com.example.authapp.network.UserApiService
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//interface AppContainer {
//    val userRepository: UserRepository
//}
//
//class DefaultAppContainer : AppContainer {
//    private val interceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//    private val client = OkHttpClient.Builder()
//        .addInterceptor(interceptor)
//        .build()
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://dummyjson.com/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(client)
//        .build()
//
//    private val retrofitApiService: UserApiService by lazy {
//        retrofit.create(UserApiService::class.java)
//    }
//
//    override val userRepository by lazy {
//        NetworkUserRepository(retrofitApiService)
//    }
//}