package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDataBase
import com.example.newsapp.data.local.NewsTypeConverter
import com.example.newsapp.data.manger.LocalUserMangerImpl
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecases.news.DeleteArticle
import com.example.newsapp.domain.usecases.news.GetNews
import com.example.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.domain.usecases.news.SearchNews
import com.example.newsapp.domain.usecases.news.GetArticle
import com.example.newsapp.domain.usecases.news.GetArticles
import com.example.newsapp.domain.usecases.news.UpsertArticle
import com.example.newsapp.util.Constants.BASE_URL
import com.example.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocalUserManger(application: Application): LocalUserManger =
        LocalUserMangerImpl(application)

    @Singleton
    @Provides
    fun provideAppEntryUseCases(localUserManger: LocalUserManger) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Singleton
    @Provides
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi, newsDao)

    @Singleton
    @Provides
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getArticles = GetArticles(newsRepository),
            getArticle = GetArticle(newsRepository)
        )
    }

    @Singleton
    @Provides
    fun provideNewsDataBase(application: Application): NewsDataBase {
        return Room.databaseBuilder(
            context = application, klass = NewsDataBase::class.java, name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDataBase: NewsDataBase): NewsDao = newsDataBase.newsDao

}