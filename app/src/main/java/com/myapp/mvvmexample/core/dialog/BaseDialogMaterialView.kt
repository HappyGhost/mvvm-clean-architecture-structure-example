package com.myapp.mvvmexample.core.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View

abstract class BaseDialogMaterialView(protected val context: Context, buttonListener: DialogClickedListener?) {
    val dialog: Dialog by lazy { Dialog(this.context, android.R.style.Theme_Translucent_NoTitleBar) }

    val customView: View by lazy { LayoutInflater.from(context).inflate(layout, null) }

    abstract val layout: Int

    protected val defaultAnimation: Int
        get() = NO_ANIMATION

    init {
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.setContentView(customView)
        this.bindView()
    }

    protected abstract fun bindView()

    protected fun setAnimation(animationResources: Int) {
        if (animationResources != NO_ANIMATION) {
            dialog.window?.setWindowAnimations(animationResources)
        }
    }

    companion object {

        protected val NO_ANIMATION = -1
    }
}
