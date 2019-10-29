package co.icanteach.projectx.data

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import co.icanteach.projectx.data.local.dao.PopularTVShowDao
import co.icanteach.projectx.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "theMovies.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull database: AppDatabase): PopularTVShowDao {
        return database.popularTVShowDao()
    }
}