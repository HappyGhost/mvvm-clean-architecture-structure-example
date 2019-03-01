package com.myapp.mvvmexample.core.view

import com.myapp.mvvmexample.core.dialog.LoadingDialogMaterial
import dagger.android.support.DaggerFragment


abstract class BaseFragment : DaggerFragment() {
    var loadingDialogMaterial: LoadingDialogMaterial? = null

    fun showProcessDialog() {
        if (loadingDialogMaterial == null) {
            loadingDialogMaterial = LoadingDialogMaterial(context!!)
        }
        loadingDialogMaterial?.dialog?.show()
    }

    fun hideProgressDialog() {
        loadingDialogMaterial?.dialog?.hide()
    }
}