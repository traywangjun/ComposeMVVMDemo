package wangjun.demo.ui.login

import wangjun.demo.base.BaseViewModel

/**
 * <pre>
 *   @author : 王骏
 *   Date:  2022/2/24
 *   desc : 登录ui状态
 * </pre>
 */
class LoginUiState<T>(
        isLoading: Boolean = false,
        isSuccess: T? = null,
        isError: String? = null,
        val enableLoginButton: Boolean = false,
        val needLogin: Boolean = false
) : BaseViewModel.UiState<T>(isLoading, false, isSuccess, isError)
