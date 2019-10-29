package co.icanteach.projectx.data.local.dao

import androidx.room.*
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import co.icanteach.projectx.data.local.entity.PopularTVShowsEntity
import io.reactivex.Single

@Dao
interface PopularTVShowDao {

    @Query("SELECT * FROM popularTVShowsEntity")
    fun getPopularTVShowsEntity(): Single<PopularTVShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(source: PopularTVShowsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sourceList: List<PopularTVShowItemEntity>)

    @Delete
    fun delete(sourceList: List<PopularTVShowItemEntity>)
}