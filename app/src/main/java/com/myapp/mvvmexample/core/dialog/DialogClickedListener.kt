package com.myapp.mvvmexample.core.dialog

import android.app.Dialog

interface DialogClickedListener {
    fun onDialogClicked(dialog: Dialog, buttonType: ButtonType): Boolean
}