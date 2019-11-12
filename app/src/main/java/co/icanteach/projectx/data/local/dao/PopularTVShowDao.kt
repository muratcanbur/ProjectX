package co.icanteach.projectx.data.local.dao

import androidx.room.*
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import io.reactivex.Single

@Dao
interface PopularTVShowDao {

    @Query("SELECT * FROM PopularTVShowItemEntity")
    fun getPopularTVShowsEntity(): Single<List<PopularTVShowItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tvShowsEntity: List<PopularTVShowItemEntity>)

    @Query("DELETE FROM PopularTVShowItemEntity")
    fun deleteAll()


    @Transaction
    fun updatePopularTvShowsEntity(list: List<PopularTVShowItemEntity>) {
        deleteAll()
        insertAll(list)
    }
}