package com.wangxingxing.videocollections.logic.repository

import com.wangxingxing.videocollections.logic.model.VideoDetail
import com.wangxingxing.videocollections.logic.model.VideoReplies

interface VideoRepository {

    suspend fun getVideoReplies(url: String): VideoReplies

    suspend fun getVideoRelatedAndVideoReplies(videoId: Long, repliesUrl: String): VideoDetail

    suspend fun getVideoDetail(videoId: Long, repliesUrl: String): VideoDetail
}