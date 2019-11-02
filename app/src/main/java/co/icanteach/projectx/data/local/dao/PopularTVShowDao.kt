package co.icanteach.projectx.data.local.dao

import androidx.room.*
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import io.reactivex.Single

@Dao
interface PopularTVShowDao {

    @Query("SELECT * FROM PopularTVShowItemEntity")
    fun getPopularTVShowsEntity(): Single<List<PopularTVShowItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(popularTVShowsEntity: List<PopularTVShowItemEntity>)

    @Query("DELETE FROM PopularTVShowItemEntity")
    fun delete()

    @Transaction
    fun updateLatest(popularTVShowsEntity: List<PopularTVShowItemEntity>) {
        delete()
        insertAll(popularTVShowsEntity)
    }
}