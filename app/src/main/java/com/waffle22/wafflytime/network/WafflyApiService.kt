package com.waffle22.wafflytime.network

import com.waffle22.wafflytime.network.dto.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface WafflyApiService {
    //url에 이미지를 업로드
    @PUT
    suspend fun uploadImage(@Url preSignedUrl: String, @Body() request: RequestBody): Response<Unit>

    // Auth 관련
    @POST("/api/auth/local/login")
    suspend fun basicLogin(@Body() request: LoginRequest): Response<TokenContainer>

    @POST("/api/auth/local/signup")
    suspend fun signUp(@Body() request: SignUpRequest): Response<TokenContainer>

    @PUT("/api/auth/refresh")
    suspend fun refresh(): Response<TokenContainer>

    @POST("/api/user/verify-mail")
    suspend fun emailAuth(@Body() email: EmailRequest): Response<ResponseBody>

    @PATCH("/api/user/verify-mail")
    suspend fun emailPatch(@Body() email: EmailCodeRequest): Response<TokenContainer>

    @GET("/api/user/me")
    suspend fun getUserInfo(): Response<UserDTO>

    @GET("/api/user/check/nickname/{nickname}")
    suspend fun checkNickname(@Path("nickname") nickname: String): Response<ResponseBody>

    @PUT("/api/user/me")
    suspend fun changeNickname(@Body() request: ChangeNicknameRequest): Response<UserDTO>

    @PUT("/api/user/me")
    suspend fun changePassword(@Body() request: ChangePasswordRequest): Response<UserDTO>

    @PUT("/api/user/me/profile")
    suspend fun setProfilePic(@Body() request: SetProfilePicRequest): Response<UserDTO>

    @DELETE("/api/auth/logout")
    suspend fun logout(): Response<ResponseBody>

    @DELETE("/api/user/me/profile")
    suspend fun deleteProfilePic(): Response<UserDTO>

    // Board 관련
    @GET("/api/board/{boardId}")
    suspend fun getSingleBoard(
        @Path("boardId") boardId: Long
    ): Response<BoardDTO>

    @GET("/api/boards")
    suspend fun getAllBoards(): Response<List<BoardListResponse>>

    @POST("/api/board")
    suspend fun createBoard(
        @Body() createBoardRequest: CreateBoardRequest
    ): Response<CreateBoardResponse>

    @DELETE("/api/board/{boardId}")
    suspend fun deleteBoard(
        @Path("boardId") boardId: Long
    ): Response<DeleteBoardResponse>

    // Post 관련
    @GET("/api/board/{boardId}/post/{postId}")
    suspend fun getSinglePost(
        @Path("boardId") boardId: Long, @Path("postId") postId: Long
    ): Response<PostResponse>

    @GET("/api/board/{boardId}/posts")
    suspend fun getAllPosts(
        @Path("boardId") boardId: Long, @Query("cursor") cursor: Int?, @Query("size") size: Int
    ): Response<PostsPage>

    @GET("/api/user/mypost")
    suspend fun getMyPosts(
        @Query("cursor") cursor: Int?, @Query("size") size: Int
    ): Response<PostsPage>

    @GET("/api/user/myrepliedpost")
    suspend fun getMyRepliedPost(
        @Query("cursor") cursor: Int?, @Query("size") size: Int
    ): Response<PostsPage>

    @GET("/api/user/myscrap")
    suspend fun getMyScraps(
        @Query("cursor") cursor: Int?, @Query("size") size: Int
    ): Response<PostsPage>

    @GET("/api/hotpost")
    suspend fun getHotPosts(
        @Query("cursor") cursor: Int?, @Query("size") size: Int
    ): Response<PostsPage>

    @GET("/api/bestpost")
    suspend fun getBestPosts(
        @Query("first") first: Int?, @Query("size") size: Int
    ): Response<PostsPage>

    @POST("/api/board/{boardId}/post")
    suspend fun createPost(
        @Path("boardId") boardId: Long,
        @Body() postRequest: PostRequest
        ): Response<PostResponse>

    @DELETE("/api/board/{boardId}/post/{postId}")
    suspend fun deletePost(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long): Response<DeletePostResponse>

    @PUT("/api/board/{boardId}/post/{postId}")
    suspend fun editPost(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long,
        @Body() editPostRequest: EditPostRequest): Response<PostResponse>

    @POST("/api/board/{boardId}/post/{postId}/like")
    suspend fun likePost(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long
    ): Response<PostResponse>

    @POST("/api/board/{boardId}/post/{postId}/scrap")
    suspend fun scrapPost(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long
    ): Response<PostResponse>

    @DELETE("/api/user/myscrap")
    suspend fun scrapCancel(
        @Query("post") postId: Long
    ): Response<cancelScrapResponse>

    //Reply 관련
    @GET("/api/board/{boardId}/post/{postId}/replies")
    suspend fun getReplies(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long,
        @Query("page") page: Int?,
        @Query("first") first: Int?,
        @Query("second") second: Int?,
        @Query("size") size: Int?
    ): Response<RepliesPage>

    @POST("/api/board/{boardId}/post/{postId}/reply")
    suspend fun createReply(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long,
        @Body() replyRequest: ReplyRequest
    ): Response<ReplyResponse>

    @DELETE("/api/board/{boardId}/post/{postId}/reply/{replyId}")
    suspend fun deleteReply(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long,
        @Path("replyId") replyId: Long
    ): Response<ReplyResponse>

    @PUT("/api/board/{boardId}/post/{postId}/reply/{replyId}")
    suspend fun editReply(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long,
        @Path("replyId") replyId: Long,
        @Body() contents: EditReplyRequest
    ): Response<ReplyResponse>

    // Notification 관련 Api
    @GET("/api/user/notifications")
    suspend fun getNotifications(@Query("cursor") cursor: Int?, @Query("size") size: Int): Response<Notification>

    // Chat 관련 Api
    @POST("/api/board/{boardId}/post/{postId}/chat")
    suspend fun sendNewChat(
        @Path("boardId") boardId: Long,
        @Path("postId") postId: Long,
        @Query("replyId") replyId: Long?,
        @Body() firstChatRequest: NewChatRequest
    ): Response<NewChatResponse>

    @GET("/api/chats")
    suspend fun getChatListPaged(
        @Query("size") size: Long,
        @Query("cursor") cursor: Long?,
    ) : Response<GetChatListResponse>

    @GET("/api/chat/{chatId}/messages")
    suspend fun getMessagesPaged(
        @Path("chatId") chatId: Long,
        @Query("cursor") cursor: Long?,
        @Query("size") size: Long,
    ): Response<GetMessagesResponse>

    @POST("/api/chat/{chatId}")
    suspend fun sendChatMessage(
        @Path("chatId") chatId: Long,
        @Body() sendChatRequest: SendChatRequest
    ): Response<MessageInfo>

    @PUT("/api/chat/{chatId}")
    suspend fun blockChatRoom(
        @Path("chatId") chatId: Long,
        @Body() blockChatRoomRequest: BlockChatRoomRequest
    ): Response<ChatSimpleInfo>
}