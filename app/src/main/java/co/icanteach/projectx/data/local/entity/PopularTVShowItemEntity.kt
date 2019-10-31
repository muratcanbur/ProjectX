package co.icanteach.projectx.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularTVShowItemEntity")
data class PopularTVShowItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "poster_path") val imageUrl: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "overview") val overview: String?
)

