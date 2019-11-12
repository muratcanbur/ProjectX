package co.icanteach.projectx.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import co.icanteach.projectx.data.local.dao.PopularTVShowDao
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity


@Database(
    entities = [PopularTVShowItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularTVShowDao(): PopularTVShowDao
}