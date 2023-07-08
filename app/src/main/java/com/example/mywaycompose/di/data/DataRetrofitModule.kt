package com.example.mywaycompose.di.data

import com.example.mywaycompose.data.remote.server.api.MyWayApi
import com.example.mywaycompose.data.remote.server.repository.RemoteRepository
import com.example.mywaycompose.utils.Constants.MY_WAY_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataRetrofitModule {

    @Singleton
    @Provides
    fun provideMyWayApi(): MyWayApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(MY_WAY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(MyWayApi::class.java)
    }

    @Provides
    fun provideRemoteRepository(
        myWayApi: MyWayApi
    ): RemoteRepository {
        return RemoteRepository(myWayApi = myWayApi)
    }

}