package com.smekhnyov.quicknotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val body: String = "",
    var updatedAt: Long = System.currentTimeMillis()
)