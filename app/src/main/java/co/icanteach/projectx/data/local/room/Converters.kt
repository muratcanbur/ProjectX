package co.icanteach.projectx.data.local.room

import androidx.room.TypeConverter
import co.icanteach.projectx.data.local.entity.PopularTVShowItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converters {
    @TypeConverter
    @JvmStatic
    fun toStringFromPopularTVShowItemEntity(value: String): List<PopularTVShowItemEntity> {
        val type = object : TypeToken<List<PopularTVShowItemEntity>>() {

        }.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toPopularTVShowItemEntityFromString(list: List<PopularTVShowItemEntity>): String {
        val gsonObject = Gson()
        return gsonObject.toJson(list)
    }
}