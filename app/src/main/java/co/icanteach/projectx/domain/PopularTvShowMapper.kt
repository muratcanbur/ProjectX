package co.icanteach.projectx.domain

import co.icanteach.projectx.common.Mapper
import co.icanteach.projectx.data.feed.response.PopularTVShowItemResponse
import co.icanteach.projectx.data.feed.response.PopularTVShowsResponse
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import co.icanteach.projectx.data.local.entity.PopularTVShowsEntity
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import javax.inject.Inject

class PopularTvShowMapper @Inject constructor() {

    fun mapFromResponse(type: List<PopularTVShowItemResponse>): List<PopularTVShowItemEntity> {
        return object : Mapper<List<PopularTVShowItemResponse>, List<PopularTVShowItemEntity>> {
            override fun mapFrom(type: List<PopularTVShowItemResponse>): List<PopularTVShowItemEntity> {
                return type.map { tvShowItemResponse ->
                    PopularTVShowItemEntity(
                        imageUrl = tvShowItemResponse.imageUrl,
                        name = tvShowItemResponse.name,
                        overview = tvShowItemResponse.overview
                    )
                }
            }
        }.mapFrom(type)
    }

    fun mapFromModel(type: List<PopularTvShowItem>): List<PopularTVShowItemEntity> {
        return object : Mapper<List<PopularTvShowItem>, List<PopularTVShowItemEntity>> {
            override fun mapFrom(type: List<PopularTvShowItem>): List<PopularTVShowItemEntity> {
                return type.map { tvShowItem ->
                    PopularTVShowItemEntity(
                        imageUrl = tvShowItem.imageUrl,
                        name = tvShowItem.name,
                        overview = tvShowItem.overview
                    )
                }
            }
        }.mapFrom(type)
    }

    fun mapFromResponse(type: PopularTVShowsResponse): List<PopularTvShowItem> {
        return object : Mapper<PopularTVShowsResponse, List<PopularTvShowItem>> {
            override fun mapFrom(type: PopularTVShowsResponse): List<PopularTvShowItem> {
                return type.results.map { itemResponse ->
                    PopularTvShowItem(
                        imageUrl = itemResponse.imageUrl,
                        name = itemResponse.name,
                        overview = itemResponse.overview
                    )
                }
            }
        }.mapFrom(type)
    }

    fun mapFromEntity(type: List<PopularTVShowItemEntity>): List<PopularTvShowItem> {
        return object : Mapper<List<PopularTVShowItemEntity>, List<PopularTvShowItem>> {
            override fun mapFrom(type: List<PopularTVShowItemEntity>): List<PopularTvShowItem> {
                return type.map { itemResponse ->
                    PopularTvShowItem(
                        imageUrl = itemResponse.imageUrl,
                        name = itemResponse.name,
                        overview = itemResponse.overview
                    )
                }
            }
        }.mapFrom(type)
    }

    fun mapFromEntity(type: PopularTVShowsEntity): List<PopularTvShowItem> {
        return object : Mapper<PopularTVShowsEntity, List<PopularTvShowItem>> {
            override fun mapFrom(type: PopularTVShowsEntity): List<PopularTvShowItem> {
                return type.results.map { itemResponse ->
                    PopularTvShowItem(
                        imageUrl = itemResponse.imageUrl,
                        name = itemResponse.name,
                        overview = itemResponse.overview
                    )
                }
            }
        }.mapFrom(type)
    }

//    fun responseItem(): Mapper<PopularTVShowsResponse, List<PopularTvShowItem>> {
//        return object : Mapper<PopularTVShowsResponse, List<PopularTvShowItem>> {
//            override fun mapTo(list: List<PopularTvShowItem>): PopularTVShowsResponse {
//                return PopularTVShowsResponse(list.map { showItem ->
//                    PopularTVShowItemResponse(
//                        imageUrl = showItem.imageUrl,
//                        name = showItem.name,
//                        overview = showItem.overview
//                    )
//                })
//            }
//
//            override fun mapFrom(type: PopularTVShowsResponse): List<PopularTvShowItem> {
//                return type.results.map { itemResponse ->
//                    PopularTvShowItem(
//                        imageUrl = itemResponse.imageUrl,
//                        name = itemResponse.name,
//                        overview = itemResponse.overview
//                    )
//                }
//            }
//        }
//    }
}