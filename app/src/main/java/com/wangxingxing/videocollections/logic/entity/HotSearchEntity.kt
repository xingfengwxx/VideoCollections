package com.wangxingxing.videocollections.logic.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HotSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val keyword: String
)