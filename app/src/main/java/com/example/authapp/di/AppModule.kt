package com.example.authapp.di

import com.example.authapp.data.repository.impl.remote.NetworkUserRepository
import com.example.authapp.data.remote.api.UserApiService
import com.example.authapp.domain.interfaces.repository.UserRepository
import com.example.authapp.presentation.navigation.Navigator
import com.example.authapp.presentation.navigation.NavigatorImpl
import com.example.authapp.presentation.navigation.model.AppGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://dummyjson.com/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return NavigatorImpl(startDestination = AppGraph.AuthScreen)
    }

    @Provides
    @Singleton
    fun provideNetworkUserRepository(api: UserApiService): UserRepository {
        return NetworkUserRepository(userApiService = api)
    }

    @Provides
    @Singleton
    fun provideUserApiService(): UserApiService {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserApiService::class.java)
    }
}