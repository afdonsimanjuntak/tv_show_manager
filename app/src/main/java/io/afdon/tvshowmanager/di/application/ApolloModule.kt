package io.afdon.tvshowmanager.di.application

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.afdon.tvshowmanager.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
object ApolloModule {

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient =
        ApolloClient.builder()
            .serverUrl(BuildConfig.GRAPHQL_SERVER)
            .okHttpClient(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val req = chain.request().newBuilder()
                .addHeader("X-Parse-Client-Key", BuildConfig.PARSE_CLIENT_KEY)
                .addHeader("X-Parse-Application-Id", BuildConfig.PARSE_APPLICATION_ID)
                .build()
            chain.proceed(req)
        }
}