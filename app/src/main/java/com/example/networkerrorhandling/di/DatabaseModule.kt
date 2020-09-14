package com.example.networkerrorhandling.di

import android.content.Context
import com.example.networkerrorhandling.data.CemeteryRepository
import com.example.networkerrorhandling.data.local.CemeteryDao
import com.example.networkerrorhandling.data.local.CemeteryRoomDatabase
import com.example.networkerrorhandling.network.CemeteryService
import com.example.networkerrorhandling.data.remote.CemeteryRemoteDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/*
    The function body of the @Provides annotated function will be executed every time Hilt needs to provide an instance of that type.
    The return type of the @Provides-annotated function tells Hilt the binding's type or how to provide instances of that type.
    The function parameters are the dependencies of the type.
 */
@InstallIn(ApplicationComponent::class) //InstallIn tells Hilt where these bindings are available
@Module                                 //@Module means we have an interface that cannot be constructor injected so we are putting it in this module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCemeteryDao(db: CemeteryRoomDatabase) = db.cemDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
            = CemeteryRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource : CemeteryRemoteDataSource, localDataSource: CemeteryDao )
            = CemeteryRepository(remoteDataSource, localDataSource)


    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://dts.scott.net")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

    @Provides
    fun provideOkHttpclient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideCemeteryService(retrofit: Retrofit): CemeteryService = retrofit.create(CemeteryService::class.java)

    @Provides
    fun provideCemeteryRemoteDataSource(cemeteryService: CemeteryService): CemeteryRemoteDataSource = CemeteryRemoteDataSource(cemeteryService)

}