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
import com.myapp.mvvmexample.core.dialog.LoadingDialogMaterial
import com.myapp.mvvmexample.feature.login.viewmodel.LoginViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : DaggerFragment(), Injectable {

    lateinit var loginViewModel: LoginViewModel
    private var loadingDialogMaterial: LoadingDialogMaterial? = null
    private var loginLiveData: LiveData<Resource<String>>? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    private fun handleLoginResult(view: View, result: Resource<String>?) {
        when {
            result?.status == Status.SUCCESS -> {
                hideProgressDialog()
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

    private fun showProcessDialog() {
        if (loadingDialogMaterial == null) {
            loadingDialogMaterial = LoadingDialogMaterial(context!!)
        }
        loadingDialogMaterial?.dialog?.show()
    }

    private fun hideProgressDialog() {
        loadingDialogMaterial?.dialog?.hide()
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