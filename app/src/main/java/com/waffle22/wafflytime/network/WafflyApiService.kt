package com.waffle22.wafflytime.network

import com.waffle22.wafflytime.network.dto.*
import retrofit2.Response
import retrofit2.http.*

interface WafflyApiService {
    // Auth 관련
    @POST("/api/auth/local/login")
    suspend fun basicLogin(@Body() request: LoginRequest): Response<TokenContainer>

    @POST("/api/auth/local/signup")
    suspend fun signUp(@Body() request: SignUpRequest): Response<TokenContainer>

    @PUT("/api/auth/refresh")
    suspend fun refresh(): Response<TokenContainer>

    @POST("/api/user/verify-mail")
    suspend fun emailAuth(@Body() email: EmailRequest): Response<EmailCode>

    @PATCH("/api/user/verified-mail")
    suspend fun emailPatch(@Body() email: EmailRequest): Response<TokenContainer>

    // 실험
    @GET("/api/user/me")
    suspend fun getUserInfo(): Response<UserDTO>
    
    // Board 관련
    @GET("/api/board/{boardId}")
    suspend fun getSingleBoard(
        boardId: Long
    ): Response<BoardDTO>

    @GET("/api/boards")
    suspend fun getAllBoards(): Response<List<BoardListResponse>>

    @POST("/api/board")
    suspend fun createBoard(
        @Body() boardDTO: BoardDTO
    ): Response<CreateBoardResponse>

    @DELETE("/api/board/{boardId}")
    suspend fun deleteBoard(
        boardId: Long
    ): Response<DeleteBoardResponse>

    // Post 관련
    @GET("/api/board/{boardId}/post/{postId}")
    suspend fun getSinglePost(
        boardId: Long, postId: Long
    ): Response<PostResponse>

    @GET("/api/board/{boardId}/posts")
    suspend fun getAllPosts(
        @Path("boardId") boardId: Long, @Query("page") page: Int, @Query("size") size: Int
    ): Response<PostsPage>

    @GET("/api/user/mypost?")
    suspend fun getMyPosts(
        @Body() page: Int, @Body() size: Int
    ): Response<PostsPage>

    @GET("/api/user/myscrap?")
    suspend fun getMyScraps(
        @Body() page: Int, @Body() size: Int
    ): Response<PostsPage>

    @POST("/api/board/{boardId}/post")
    suspend fun createPost(
        boardId: Long,
        @Body() postRequest: PostRequest
    ): Response<PostRequest>

    @DELETE("/api/board/{boardId}/post/{postId}")
    suspend fun deletePost(boardId: Long, postId: Long): Response<DeletePostResponse>

    @PUT("/api/board/{boardId}/post/{postId}")
    suspend fun editPost(boardId: Long, postId: Long, @Body() editPostRequest: EditPostRequest): Response<EditPostResponse>
}