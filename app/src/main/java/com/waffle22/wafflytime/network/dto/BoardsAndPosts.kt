package com.waffle22.wafflytime.network.dto

import com.squareup.moshi.Json

data class BoardAbstract(
    @Json(name = "boardId") val boardId : Long,
    @Json(name = "name") val name: String
)

data class TimeDTO(
    @Json(name ="year") val year: Int,
    @Json(name ="month") val month: Int,
    @Json(name ="day") val day: Int,
    @Json(name ="hour") val hour: Int,
    @Json(name ="minute")val minute: Int
)

data class BoardListResponse(
    @Json(name = "id") val id : Long,
    @Json(name = "category") val category : String,
    @Json(name = "size") val size : Int,
    @Json(name = "defaultDisplayColumnSize") val defaultDisplayColumnSize : Int,
    @Json(name = "boards") val boards : List<BoardAbstract>?
)

data class BoardDTO(    //BoardResponse
    @Json(name = "boardId") val boardId: Long,
    @Json(name = "boardType") val boardType: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "allowAnonymous") val allowAnonymous: Boolean
)

data class ImageRequest(
    @Json(name = "imageId") val imageId: Int,
    @Json(name = "fileName") val fileName: String,
    @Json(name = "description") val description: String
)

data class PostRequest(
    @Json(name = "title") val title: String,
    @Json(name = "contents") val contents: String,
    @Json(name = "isQuestion") val isQuestion: Boolean,
    @Json(name = "isWriterAnonymous") val isWriterAnonymous : Boolean,
    @Json(name = "images") val images: List<ImageRequest>
)

data class ImageResponse(
    @Json(name = "imageId") val imageId: Int,
    @Json(name = "preSignedUrl") val preSignedUrl: String,
    @Json(name = "description") val description: String
)

data class EditPostRequest(
    @Json(name = "title") val title: String,
    @Json(name = "contents") val contents: String,
    @Json(name = "isQuestion") val isQuestion: Boolean,
    @Json(name = "isWriterAnonymous") val isWriterAnonymous : Boolean,
    @Json(name = "images") val images: List<ImageRequest>,
    @Json(name = "deletedImages") val deletedImages: List<String>
)

data class PostResponse( // PostResponse
    @Json(name = "boardId") val boardId: Long,
    @Json(name = "boardTitle") val boardTitle: String,
    @Json(name = "postId") val postId: Long,
    @Json(name = "createdAt") val createdAt: TimeDTO,
    @Json(name = "writerId") val writerId: Long,
    @Json(name = "nickname") val nickname: String?,
    @Json(name = "isWriterAnonymous") val isWriterAnonymous : Boolean,
    @Json(name = "isQuestion") val isQuestion: Boolean,
    @Json(name = "title") val title: String?,
    @Json(name = "contents") val contents: String,
    @Json(name = "images") val images: List<ImageResponse>,
    @Json(name = "nlikes") val nlikes: Int,
    @Json(name = "nscraps") val nscraps: Int,
    @Json(name = "nreplies") val nreplies: Int
)

data class CreateBoardResponse(
    @Json(name = "userId") val userId : Long,
    @Json(name = "boardId") val boardId: Long,
    @Json(name = "boardType") val boardType: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "allowAnonymous") val allowAnonymous: Boolean
)

data class DeleteBoardResponse(
    @Json(name = "boardId") val boardId: Long,
    @Json(name = "title") val title: String
)

data class DeletePostResponse(
    @Json(name = "boardId") val boardId: Long,
    @Json(name = "boardTitle") val boardTitle: String,
    @Json(name = "postId") val postId: Long,
    @Json(name = "postTitle") val postTitle: String
)

data class EditPostResponse(
    @Json(name = "postId") val postId: Long,
    @Json(name = "writerId") val writerId: Long,
    @Json(name = "isWriterAnonymous") val isWriterAnonymous : Boolean,
    @Json(name = "title") val title: String,
    @Json(name = "contents") val contents: Boolean
)

data class PageableSorted(
    @Json(name = "empty") val empty: Boolean,
    @Json(name = "sorted") val sorted: Boolean,
    @Json(name = "unsorted") val unsorted: Boolean
)

data class Pageable(
    @Json(name = "sort") val sort: PageableSorted,
    @Json(name = "offset") val offset: Int,
    @Json(name = "pageNumber") val pageNumber: Int,
    @Json(name = "pageSize") val pageSize: Int,
    @Json(name = "paged") val paged: Boolean,
    @Json(name = "unpaged") val unpaged: Boolean
)

data class PostsPage(
    @Json(name = "contents") val contents: List<PostResponse>?,
    @Json(name = "pageable") val pageable: Pageable,
    @Json(name = "totalPages") val totalPages: Boolean,
    @Json(name = "totalElements") val totalElements: Boolean,
    @Json(name = "last") val last: Boolean,
    @Json(name = "size")  val size: Int,
    @Json(name = "number") val number: Int,
    @Json(name = "sort") val sort: PageableSorted,
    @Json(name = "numberOfElements") val first: Boolean,
    @Json(name = "empty") val empty: Boolean
)

enum class BoardType{
    Common, MyPosts, MyReplies, Scraps, Hot, Best
}