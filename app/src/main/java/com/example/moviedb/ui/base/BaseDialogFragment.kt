package com.example.moviedb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.moviedb.BR
import com.example.moviedb.R
import com.example.moviedb.utils.DialogUtils

abstract class BaseDialogFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> :
    DialogFragment() {

    lateinit var viewBinding: ViewBinding

    abstract val viewModel: ViewModel

    @get:LayoutRes
    abstract val layoutId: Int

    var loadingDialog: AlertDialog? = null
    var messageDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            setVariable(BR.viewModel, viewModel)
            root.isClickable = true
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer {
                handleShowLoading(it == true)
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                hideLoading()
                if (it.isNullOrBlank().not()) {
                    handleShowErrorMessage(it)
                }
            })
            noInternetConnectionEvent.observe(viewLifecycleOwner, Observer {
                handleShowErrorMessage(getString(R.string.no_internet_connection))
            })
            connectTimeoutEvent.observe(viewLifecycleOwner, Observer {
                handleShowErrorMessage(getString(R.string.connect_timeout))
            })
            forceUpdateAppEvent.observe(viewLifecycleOwner, Observer {
                handleShowErrorMessage(getString(R.string.force_update_app))
            })
            serverMaintainEvent.observe(viewLifecycleOwner, Observer {
                handleShowErrorMessage(getString(R.string.server_maintain_message))
            })
        }
    }

    /**
     * override this if not use loading dialog (example progress bar)
     */
    open fun handleShowLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = DialogUtils.createLoadingDialog(context)
        }
        loadingDialog?.show()
    }

    fun hideLoading() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }

    fun handleShowErrorMessage(message: String) {
        if (messageDialog?.isShowing == true) {
            messageDialog?.hide()
        }

        messageDialog = DialogUtils.showMessage(
            context = context,
            message = message,
            textPositive = getString(R.string.ok)
        )
    }

    override fun onDestroy() {
        loadingDialog?.dismiss()
        messageDialog?.dismiss()
        super.onDestroy()
    }
}