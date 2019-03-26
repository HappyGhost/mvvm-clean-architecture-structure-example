package com.myapp.mvvmexample.feature.login.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.myapp.business.core.callback.Resource
import com.myapp.business.core.callback.Status
import com.myapp.mvvmexample.R
import com.myapp.mvvmexample.core.application.Injectable
import com.myapp.mvvmexample.core.view.BaseFragment
import com.myapp.mvvmexample.feature.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.include_header_action_right.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), Injectable {

    private lateinit var loginViewModel: LoginViewModel
    private var loginLiveData: LiveData<Resource<String>>? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHeader()
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
        btnLogin.setOnClickListener {
            if (isInputValid(edtUsername.text.toString(), edtPassword.text.toString())) {
                inputLayoutUsername.error = null
                inputLayoutPassword.error = null
                loginLiveData = loginViewModel.login(edtUsername.text.toString(), edtPassword.text.toString())
                loginLiveData?.observe(viewLifecycleOwner, Observer { result ->
                    handleLoginResult(view, result)
                })

            }
        }

        loginViewModel.loadUserInfoResult.observe(viewLifecycleOwner, Observer { result ->
            edtUsername.setText(result?.data?.username)
            edtPassword.setText(result?.data?.password)
            switchRemember.isChecked = result?.data?.isRemember!!
        })
        loginViewModel.loadData(context!!)
    }

    private fun initHeader() {
        tvHeader.text = getString(R.string.login_header)
    }

    private fun handleLoginResult(view: View, result: Resource<String>?) {
        when {
            result?.status == Status.SUCCESS -> {
                hideProgressDialog()
                if (switchRemember.isChecked) {
                    loginViewModel.saveUserInfo(context!!, edtUsername.text.toString(), edtPassword.text.toString())
                }
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_articleListFragment)
            }
            result?.status == Status.ERROR -> {
                hideProgressDialog()
                inputLayoutUsername.error = getString(R.string.validation_error_login_fail)
                edtPassword.setText("")
            }
            result?.status == Status.LOADING -> showProcessDialog()
        }
    }

    private fun isInputValid(username: String, password: String): Boolean {
        var result = true
        if (username.isEmpty()) {
            inputLayoutUsername.error = getString(R.string.validation_error_field_is_empty)
            result = false
        }

        if (password.isEmpty()) {
            inputLayoutPassword.error = getString(R.string.validation_error_field_is_empty)
            result = false
        }
        return result
    }
}