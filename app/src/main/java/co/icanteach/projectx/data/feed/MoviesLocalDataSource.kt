package co.icanteach.projectx.data.feed

import co.icanteach.projectx.data.local.dao.PopularTVShowDao
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(private val popularTVShowDao: PopularTVShowDao) {

    fun fetchMovies() = popularTVShowDao.getPopularTVShowsEntity()
    fun storeMovies(sourceList: List<PopularTVShowItemEntity>) =
        popularTVShowDao.insertAll(sourceList)

}