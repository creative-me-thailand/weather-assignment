package com.chanothai.assignment.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chanothai.assignment.domain.entity.database.AvatarEntity
import com.chanothai.assignment.domain.error.Errors

@Database(entities = [AvatarEntity::class], version = 2)
abstract class AvatarRoomDatabase: RoomDatabase() {

    abstract fun avatarDao(): AvatarDao

    companion object {
        @Volatile
        private var instance: AvatarRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AvatarRoomDatabase {
            if (instance == null) {
                synchronized(AvatarRoomDatabase::class) {
                    //Create Database
                    instance = Room.databaseBuilder(context.applicationContext,
                            AvatarRoomDatabase::class.java, "avatar_database")
                            .fallbackToDestructiveMigration().build()
                }
            }

            return instance ?: throw Errors.UnableCreateDatabase
        }
    }
}

