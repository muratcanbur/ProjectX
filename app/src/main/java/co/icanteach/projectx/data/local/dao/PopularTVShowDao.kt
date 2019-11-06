package co.icanteach.projectx.data.local.dao

import androidx.room.*
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import io.reactivex.Single

@Dao
interface PopularTVShowDao {

    @Query("SELECT * FROM popularTVShowsEntity")
    fun getPopularTVShowsEntity(): Single<List<PopularTVShowItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(popularTVShowsEntity: List<PopularTVShowItemEntity>)

    @Delete
    fun delete(popularTVShowsEntity: List<PopularTVShowItemEntity>)
}