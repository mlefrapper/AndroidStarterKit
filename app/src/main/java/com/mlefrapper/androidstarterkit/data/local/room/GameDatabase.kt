package com.mlefrapper.androidstarterkit.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mlefrapper.androidstarterkit.data.local.dao.GameDao
import com.mlefrapper.androidstarterkit.data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringTypeConverter::class, DateTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
