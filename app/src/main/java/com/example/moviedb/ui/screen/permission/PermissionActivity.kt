package com.example.moviedb.ui.screen.permission

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityPermissionBinding
import com.example.moviedb.ui.base.BaseActivity
import com.example.moviedb.utils.*
import kotlinx.android.synthetic.main.fragment_permisison.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PermissionActivity : BaseActivity<ActivityPermissionBinding, PermissionViewModel>() {

    override val layoutId: Int = R.layout.activity_permission

    override val viewModel: PermissionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button_1?.setSingleClick { requestSinglePermissionWithListener() }
        button_2?.setSingleClick { requestMultiplePermissionWithListener() }

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container, PermissionFragment.newInstance()
        ).commit()
    }

    // single permission
    private val singlePermission = arrayOf(Manifest.permission.WRITE_CONTACTS)
    private val singlePermissionCode = 1001

    /**
     * request single permission with listener
     */
    private fun requestSinglePermissionWithListener() {
        requestPermissions(
            singlePermission,
            singlePermissionCode,
            object : RequestPermissionListener {
                override fun onPermissionRationaleShouldBeShown(requestPermission: () -> Unit) {
                    DialogUtils.showMessage(
                        context = this@PermissionActivity,
                        message = "Please allow permission to use this feature",
                        textPositive = "OK",
                        positiveListener = {
                            requestPermission.invoke()
                        },
                        textNegative = "Cancel"
                    )
                }

                override fun onPermissionPermanentlyDenied(openAppSetting: () -> Unit) {
                    DialogUtils.showMessage(
                        context = this@PermissionActivity,
                        message = "Permission Disabled, Please allow permission to use this feature",
                        textPositive = "OK",
                        positiveListener = {
                            openAppSetting.invoke()
                        },
                        textNegative = "Cancel"
                    )
                }

                override fun onPermissionGranted() {
                    showToast("Granted, do work")
                }
            })
    }

    // multiple permissions
    private val multiplePermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )
    private val multiplePermissionsCode = 1111

    /**
     * request multiple permissions with listener
     */
    private fun requestMultiplePermissionWithListener() {
        requestPermissions(
            multiplePermissions,
            multiplePermissionsCode,
            object : RequestPermissionListener {
                override fun onPermissionRationaleShouldBeShown(requestPermission: () -> Unit) {
                    DialogUtils.showMessage(
                        context = this@PermissionActivity,
                        message = "Please allow permissions to use this feature",
                        textPositive = "OK",
                        positiveListener = {
                            requestPermission.invoke()
                        },
                        textNegative = "Cancel"
                    )
                }

                override fun onPermissionPermanentlyDenied(openAppSetting: () -> Unit) {
                    DialogUtils.showMessage(
                        context = this@PermissionActivity,
                        message = "Permission Disabled, Please allow permissions to use this feature",
                        textPositive = "OK",
                        positiveListener = {
                            openAppSetting.invoke()
                        },
                        textNegative = "Cancel"
                    )
                }

                override fun onPermissionGranted() {
                    showToast("Granted, do work")
                }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // single permission
        handleOnRequestPermissionResult(
            singlePermissionCode,
            requestCode,
            permissions,
            grantResults,
            object : PermissionResultListener {
                override fun onPermissionRationaleShouldBeShown() {
                    showToast("permission denied")
                }

                override fun onPermissionPermanentlyDenied() {
                    showToast("permission permanently disabled")
                }

                override fun onPermissionGranted() {
                    showToast("permission granted")
                }
            })

        // multiple permission
        handleOnRequestPermissionResult(
            multiplePermissionsCode,
            requestCode,
            permissions,
            grantResults,
            object : PermissionResultListener {
                override fun onPermissionRationaleShouldBeShown() {
                    showToast("permission denied")
                }

                override fun onPermissionPermanentlyDenied() {
                    showToast("permission permanently disabled")
                }

                override fun onPermissionGranted() {
                    showToast("permission granted")
                }
            }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(this, "Activity $message", Toast.LENGTH_SHORT).show()
    }
}