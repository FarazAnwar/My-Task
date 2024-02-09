package com.task.management.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.task.management.domain.entities.TaskModel

@Database(entities = [TaskModel::class], version = 3, exportSchema = false)
abstract class TaskDetailDatabase : RoomDatabase() {

    abstract fun  userDetail():TaskDetailDao

    companion object {
        @Volatile
        private var instance: TaskDetailDatabase? = null

        fun getDatabase(context: Context): TaskDetailDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(context.applicationContext, TaskDetailDatabase::class.java,
                    "task-database")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                instance = newInstance

                return newInstance
            }
        }

        // Migration from version 1 to version 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_detail ADD COLUMN emailId TEXT NOT NULL DEFAULT ''")
            }
        }
    }


}