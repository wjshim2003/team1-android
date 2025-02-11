package com.waffle22.wafflytime.ui.login


import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import com.waffle22.wafflytime.network.WafflyApiService
import com.waffle22.wafflytime.network.dto.LoginRequest
import com.waffle22.wafflytime.util.AuthStorage
import com.waffle22.wafflytime.util.SlackState
import com.waffle22.wafflytime.util.parseError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val wafflyApiService: WafflyApiService,
    private val authStorage: AuthStorage,
    private val moshi: Moshi
) : ViewModel() {

    // TODO: Change String type to Enum Class!!!
    private val _loginState = MutableStateFlow(SlackState("0",null,null,null))
    val loginState: StateFlow<SlackState<Nothing>> = _loginState

    fun resetAuthState(){
        _loginState.value = SlackState("0",null,null,null)
    }

    fun login(id: String, password: String){
        viewModelScope.launch {


            try {
                val response = wafflyApiService.basicLogin(LoginRequest(id, password))
                if (response.isSuccessful) {
                    authStorage.setTokenInfo(response.body()!!.accessToken, response.body()!!.refreshToken)
                    _loginState.value = SlackState("200",null,null,null)
                } else {
                    val errorResponse = HttpException(response).parseError(moshi)!!
                    _loginState.value = SlackState(errorResponse.statusCode,errorResponse.errorCode,errorResponse.message,null)
                }
            } catch (e:java.lang.Exception) {
                _loginState.value = SlackState("-1",null,"System Corruption",null)
            }
        }
    }


    fun kakaoSocialLogin(context : Context) {

    }
    fun naverSocialLogin() {

    }
    fun googleSocialLogin() {

    }
    fun githubSocialLogin() {

    }

}
