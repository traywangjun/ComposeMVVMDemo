package wangjun.demo.model.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import wangjun.demo.App
import wangjun.demo.model.api.BaseRepository
import wangjun.demo.model.api.ApiService
import wangjun.demo.model.bean.User
import wangjun.demo.model.bean.doError
import wangjun.demo.model.bean.doSuccess
import wangjun.demo.ui.login.LoginUiState
import wangjun.demo.util.Preference
import javax.inject.Inject

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/3/2
 *   desc : 登录数据仓库
 * </pre>
 */
class LoginRepository @Inject constructor(val service: ApiService) : BaseRepository() {

    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")

    suspend fun loginFlow(userName: String, passWord: String) = flow {

        // 输入不能为空
        if (userName.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }

        service.login(userName, passWord).doSuccess { user ->
            isLogin = true
            userJson = Gson().toJson(user)
            App.CURRENT_USER = user
            emit(LoginUiState(isSuccess = user, enableLoginButton = true))
        }.doError { errorMsg ->
            emit(LoginUiState<User>(isError = errorMsg, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
            .catch { emit(LoginUiState(isError = it.message, enableLoginButton = true)) }


    suspend fun registerFlow(userName: String, passWord: String) = flow<LoginUiState<User>> {

        // 输入不能为空
        if (userName.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }

        service.register(userName, passWord, passWord).doSuccess {
            loginFlow(userName,passWord)
        }.doError { errorMsg ->
            emit(LoginUiState(isError = errorMsg, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
            .catch { emit(LoginUiState(isError = it.message, enableLoginButton = true)) }

}