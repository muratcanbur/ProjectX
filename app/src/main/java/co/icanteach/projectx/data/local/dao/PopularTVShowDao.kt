package co.icanteach.projectx.data.local.dao

import androidx.room.*
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PopularTVShowDao {

    @Query("SELECT * FROM popularTVShowItemEntity")
    fun getPopularTVShowsEntity(): Single<List<PopularTVShowItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(popularTVShowsEntity: List<PopularTVShowItemEntity>): Completable

    @Query("DELETE FROM popularTVShowItemEntity")
    fun deleteAll(): Completable


    @Transaction
    fun updatePopularTvShowsEntity(list: List<PopularTVShowItemEntity>) {
        deleteAll()
        insertAll(list)
    }
}