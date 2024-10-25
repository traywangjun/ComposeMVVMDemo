package wangjun.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wangjun.demo.model.api.RetrofitClient
import wangjun.demo.model.api.ApiService
import javax.inject.Singleton

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/21
 *   desc : AppModule
 * </pre>
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideService(): ApiService =
        RetrofitClient.getService(ApiService::class.java, ApiService.BASE_URL)
}