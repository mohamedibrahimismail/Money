package com.example.money.ui.base

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.money.App
import com.example.money.R
import com.example.money.network.retrofit.LoginPref
import com.example.money.ui.base.viewmodel.CommanVM
import com.example.money.ui.main.MainActivity
import com.example.money.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.item_no_internet_connection.*
import kotlinx.android.synthetic.main.item_no_internet_connection.view.*
import kotlinx.android.synthetic.main.layout_base.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.progress_dialog.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * BaseActivity is used as parent activity class for all activists and have all helper methods
 * @author Mohamed Ibrahim
 */
abstract class BaseActivity : AppCompatActivity(), BaseViewCallBack, BaseFragment.Callback {

    private var activityView: ViewGroup? = null

    private val commanVM: CommanVM by viewModel()

    var color: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        activityView = LayoutInflater.from(this)
            .inflate(getActivityView(), layout_base_view, false) as ViewGroup
        layout_base_view.addView(activityView)

        afterInflation(savedInstanceState)

        App.isNotAuth.observe(this, Observer {
            if (it == true) {
                val loginPref: LoginPref = LoginPref(this)
                loginPref.logout()
                toast(getString(R.string.your_toke_expired_login_again))
                var logoutIntent = Intent(this@BaseActivity, MainActivity::class.java)
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(logoutIntent)
                App.isNotAuth.value = false
                this@BaseActivity.finishAffinity()
            }
        })

        if (!isNetworkConnected()) {
            noInternetLY.visibility = View.VISIBLE
            activityView!!.visibility = View.GONE
        } else {
            noInternetLY.visibility = View.GONE
            activityView!!.visibility = View.VISIBLE
        }
//        noInternetLY.retryBTN.setOnClickListener {
//            if (!isNetworkConnected()) {
//                noInternetLY.visibility = View.VISIBLE
//                activityView!!.visibility = View.GONE
//            } else {
//                noInternetLY.visibility = View.GONE
//                activityView!!.visibility = View.VISIBLE
//            }
//        }

    }


    protected abstract fun getActivityView(): Int

    protected abstract fun afterInflation(savedInstance: Bundle?)

    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED


    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(
            findViewById(R.id.layout_base_view),
            message, Snackbar.LENGTH_LONG
        )
        val sbView = snackbar.view
        val textView = sbView
            .findViewById<View>(R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    override fun onError(message: String?) {
        if (message != null) {
            //here to check error type from CallBackWrapper and do different actions..
            showMessage(message)
        } else {
            showSnackBar(getString(R.string.some_error))
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }

    override fun onError(
        textString: String, actionString: String?, icon: Drawable?,
        actionListener: View.OnClickListener?
    ) {
        progressL.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        activityView!!.visibility = View.GONE
        if (actionString != null) {
            errorActionB.setOnClickListener(actionListener)
            errorActionB.visibility = View.VISIBLE
        }
        if (icon != null) {
            errorIcon!!.setImageDrawable(icon)
        }
        errorText.text = textString
        errorActionB.text = actionString
    }

    override fun onError(
        @StringRes errorTextRes: Int,
        @StringRes errorActionRes: Int,
        @DrawableRes errorIcon: Int,
        errorActionListener: View.OnClickListener?
    ) {
        val errorActionString =
            if (errorActionRes == 0) null else resources.getString(errorActionRes)
        val errorActionIcon =
            if (errorIcon == 0) null else ContextCompat.getDrawable(this, errorIcon)
        onError(
            resources.getString(errorTextRes),
            errorActionString,
            errorActionIcon,
            errorActionListener
        )
    }

    fun isConnected(): Boolean {
        var connected:Boolean
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connected = cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        if(connected){
            noInternetLY.visibility = View.GONE
            activityView!!.visibility = View.VISIBLE
        }else{
            noInternetLY.visibility = View.VISIBLE
            activityView!!.visibility = View.GONE
        }
        return connected
    }


    override fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)


    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun hideKeyboard(activity: Activity) {
        val imm =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun hideLoading() {
        errorView.visibility = View.GONE
        progressL.visibility = View.GONE
        activityView!!.visibility = View.VISIBLE
    }

    override fun showLoading(loading: Boolean) {
        if (loading) {
//            lodaing.show()
            progressL.visibility = View.VISIBLE
            activityView!!.visibility = View.GONE
        } else {
            progressL.visibility = View.GONE
            activityView!!.visibility = View.VISIBLE
        }
    }

    override fun openActivityOnTokenExpire() {
        finish()
    }
}
