package wangjun.demo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import wangjun.demo.base.BaseViewModel

import wangjun.demo.model.bean.User
import wangjun.demo.model.repository.LoginRepository
import javax.inject.Inject

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/24
 *   desc : 登录视图模型
 * </pre>
 */
@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) :
    BaseViewModel() {

    private val _uiState = MutableLiveData<LoginUiState<User>>()
    val uiState: LiveData<LoginUiState<User>>
        get() = _uiState

    private fun isInputValid(userName: String, passWord: String) =
        userName.isNotBlank() && passWord.isNotBlank()

    fun login(userName: String, passWord: String) {
        launchOnUI {
            // repo 返回的是一个 flow
            repository.loginFlow(userName, passWord)
                .collect {
                    _uiState.postValue(it)
                }
        }
    }

    fun register(userName: String, passWord: String) {
        launchOnUI {
            repository.registerFlow(userName, passWord)
                .collect {
                    _uiState.postValue(it)
                }
        }
    }
}