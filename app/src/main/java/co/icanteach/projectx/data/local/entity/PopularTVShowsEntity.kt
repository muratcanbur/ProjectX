package co.icanteach.projectx.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import co.icanteach.projectx.data.local.room.Converters


@Entity(tableName = "PopularTVShowsEntity")
@TypeConverters(Converters::class)
data class PopularTVShowsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,

    @ColumnInfo(name = "results") val results: List<PopularTVShowItemEntity>
)


