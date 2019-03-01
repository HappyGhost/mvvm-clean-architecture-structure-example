/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.github.di

import com.myapp.api.rss.article.repository.ArticleRepositoryImpl
import com.myapp.api.rss.article.service.ArticleService
import com.myapp.api.rss.login.repository.LoginRepositoryImpl
import com.myapp.business.rss.article.repository.ArticleRepository
import com.myapp.business.rss.article.usecase.GetArticleListUseCase
import com.myapp.business.rss.article.usecase.GetArticleListUseCaseImpl
import com.myapp.business.rss.login.repository.LoginRepository
import com.myapp.business.rss.login.usecase.LoginUseCase
import com.myapp.business.rss.login.usecase.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient2(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.cache(null)
        builder.followRedirects(false)
        builder.followSslRedirects(false)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiAdapter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://feeds.bbci.co.uk")
            .addConverterFactory(
                SimpleXmlConverterFactory.createNonStrict(
                    Persister(AnnotationStrategy())
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCaseImpl(loginRepository)
    }

    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    fun provideGetArticleListUseCase(repository: ArticleRepository): GetArticleListUseCase {
        return GetArticleListUseCaseImpl(repository)
    }

    @Provides
    fun provideArticleRepository(retrofit: Retrofit): ArticleRepository {
        return ArticleRepositoryImpl(retrofit.create(ArticleService::class.java))
    }
}
