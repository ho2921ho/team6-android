package com.example.todomateclone.network

import com.example.todomateclone.network.dto.*
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*
import java.time.LocalDate

interface RestService {
    // Login, SignUp, Social Login API
    @POST("/accounts/login/")
    suspend fun login(@Body() request: LoginRequest): LoginResult

    @POST("/accounts/logout/")
    suspend fun logout(
        @Header("authorization") accessToken: String,
    ): Response<Unit>

    @POST("/accounts/registration/")
    suspend fun signup(@Body() request: SignupRequest): SignupResult

    @POST("/accounts/registration/confirm/")
    suspend fun signupConfirm(@Body() request: SignUpConfirmRequest): SignUpConfirmResult

    @POST("/accounts/resend-email/")
    suspend fun resendEmail(@Body() request: ResendEmailRequest): ResendEmailResult

    @GET("/accounts/user/")
    suspend fun getUser(): GetUserResult

    @PUT("/accounts/user/")
    suspend fun updateUser(
        @Body() request: UpdateUserRequest
    ): UpdateUserResult

    @DELETE("/accounts/user/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ): Response<Unit>

    @POST("/accounts/kakao/login/")
    suspend fun kakaoLogin(@Body() request: KakaoLoginRequest): LoginResult

    @POST("/accounts/google/login/")
    suspend fun googleLogin(@Body() request: GoogleLoginRequest): LoginResult


    // Task API

    @POST("/task/list/{date}/")
    suspend fun createTask(@Body() request: CreateTaskRequest, @Path("date") date: String): TaskDTO

    @GET("/task/list/{date}/")
    suspend fun getTasksByDateFirstPage(@Path("date") date: String): GetTasksByDateResponse

    @GET("/task/list/{date}/")
    suspend fun getTasksByDatePaged(@Path("date") date: String, @Query("page") page: Int): GetTasksByDateResponse

    @GET("/task/list/")
    suspend fun getAllTasksPaged(): GetAllTasksResponse

    @GET("/task/detail/{tid}/check/")
    suspend fun checkTask(@Path("tid") tid: Int): TaskDTO

    @DELETE("/task/detail/{tid}/")
    suspend fun deleteTask(@Path("tid") tid: Int): TaskDTO?

    @GET("/task/detail/{tid}/delay/")
    suspend fun delayTask(@Path("tid") tid: Int): TaskDTO?

    @PUT("/task/detail/{tid}/update/")
    suspend fun changeTask(@Body() request: ChangeTaskRequest, @Path("tid") tid: Int): TaskDTO?

    // Diary API
    // 해당 사용자의 모든 일기 불러오기
    @GET("/diary/mydiary/")
    suspend fun getDiaryList(): GetDiaryListResponse
    // 해당 날짜의 다이어리 불러오기
    @GET("/diary/mydiary/{date}/")
    suspend fun getDateDiary(@Path("date") date: String): GetDateDiaryResponse
    // 해당 날짜에 일기 등록하기
    @POST("/diary/mydiary/{date}/create/")
    suspend fun createDateDiary(@Body() request: CreateDiaryRequest, @Path("date") date: String): DiaryDTO
    // 해당 날짜의 일기 수정하기
    @PATCH("/diary/mydiary/{date}/update/")
    suspend fun updateDateDiary(@Body() request: UpdateDiaryRequest, @Path("date") date: String): DiaryDTO
    // 해당 날짜의 일기 삭제하기
    @DELETE("/diary/mydiary/{date}/update/")
    suspend fun deleteDateDiary(@Path("date") date: String): Response<Unit>
    // 해당 id의 일기 불러오기
    @GET("/diary/watch/{did}/")
    suspend fun getIdDiary(@Path("did") did: Int): DiaryDTO
    // 해당 id의 일기 수정하기
    @PATCH("/diary/watch/{did}/")
    suspend fun updateIdDiary(@Body() request: UpdateDiaryRequest, @Path("did") did: Int): DiaryDTO
    // 해당 id의 일기 삭제하기
    @DELETE("/diary/watch/{did}/")
    suspend fun deleteIdDiary(@Path("did") did: Int): Response<Unit>
    // 해당 일기 id의 댓글 불러오기
    @GET("/diary/comment/{did}/")
    suspend fun getCommentList(@Path("did") did: Int): GetCommentListResponse
    // 해당 일기 id에 댓글 등록하기
    @POST("/diary/comment/{did}/")
    suspend fun createComment(@Body() request: CreateCommentRequest, @Path("did") did: Int): CommentDTO
    // 해당 id의 댓글 불러오기
    @GET("/diary/comment/detail/{cid}/")
    suspend fun getIdComment(@Path("cid") cid: Int): CommentDTO
    // 해당 id의 댓글 수정하기
    @PATCH("/diary/comment/detail/{cid}/")
    suspend fun updateIdComment(@Body request: UpdateCommentRequest ,@Path("cid") cid: Int): CommentDTO
    // 해당 id의 댓글 삭제하기
    @DELETE("/diary/comment/detail/{cid}/")
    suspend fun deleteIdComment(@Path("cid") cid: Int): Response<Unit>
}