package  com.example.money.network.retrofit

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.money.App
import com.google.gson.Gson

/**
 * LoginPref used as SharedPreferences holder among all the application
 * @author Mohamed Ibrahim
 * @param context is application context
 */
class LoginPref(context: Context) {

    private var prefs: SharedPreferences? = null
    private var settingsPrefs: SharedPreferences? = null
    var accessToken: String? = null

    init {
        try {
            prefs =
                context.applicationContext.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)
            settingsPrefs =
                context.applicationContext.getSharedPreferences(SETTINGS_PREF, Context.MODE_PRIVATE)
            accessToken = prefs!!.getString(KEY_ACCESS_TOKEN, null)
        } catch (e: Exception) {
        }
    }

    /**
     * setAccessToken used to save user access token in pref
     * @author Mohamed Ibrahim
     * @param context is application context
     * @param accessToken is user private access token
     */
    fun setAccessToken(context: Context, accessToken: String) {
        try {
            if (!TextUtils.isEmpty(accessToken)) {
                this.accessToken = accessToken
                prefs!!.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply()
                setSecureConnection()
            }

        } catch (e: Exception) {
        }
    }

    fun saveNotificationToken(token: String?) {
        if (!TextUtils.isEmpty(token)) {
            prefs?.edit()?.putString(KEY_NOTIFICATION_ACCESS_TOKEN, token)?.apply()
        }
    }

    /**
     * logout used to clear user data from pref
     * @author Mohamed Ibrahim
     */
    fun logout() {
        prefs?.edit()?.clear()?.apply()
    }

    fun getNotificationToken(): String? {
        return prefs?.getString(KEY_NOTIFICATION_ACCESS_TOKEN, null)
    }

    fun removeAccessToken(context: Context) {
        try {
            this.accessToken = null
            prefs?.edit()?.putString(KEY_ACCESS_TOKEN, null)?.apply()
            removeSecureConnection()
        } catch (e: Exception) {
        }
    }

    /**
     * setSecureConnection used to call App.createApi to create instance of api call client
     * @author Mohamed Ibrahim
     */
    fun setSecureConnection() {
        try {
            App.createApi(
                AuthInterceptor(this.accessToken),
                APInterceptor(),
                APIContentInterceptor()
            )
        } catch (e: Exception) {
        }
    }

    private fun removeSecureConnection() {
        App.createApi(null, APInterceptor(), APIContentInterceptor())
    }

    companion object {
        private val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        private val LOGIN_PREF = "LOGIN_PREF"
        private val SETTINGS_PREF = "SETTINGS_PREF"
        private val KEY_NOTIFICATION_ACCESS_TOKEN = "KEY_NOTIFICATION_ACCESS_TOKEN"
    }

    fun saveTheme(color: String) {
        prefs?.edit()?.putString("theme_color", color)?.apply()
    }

    fun getTheme(): String {
        return prefs?.getString("theme_color", "blue")!!
    }

    /**
     * saveUser used to save user in pref
     * @author Mohamed Ibrahim
     * @param user User data saved in UserModel class from api response
     */
//    fun saveUser(user: UserModel) {
//        val gson = Gson()
//        val json = gson.toJson(user)
//        prefs?.edit()?.putString("user", json)?.apply()
//    }

//    fun getUser(): UserModel? {
//        val gson = Gson()
//        val json = prefs!!.getString("user", null)
//        return gson.fromJson(json, UserModel::class.java)
//    }

    fun saveLang(lang: String) {
        prefs?.edit()?.putString("lang", lang)?.apply()
    }

    fun getLang(): String {
        return prefs?.getString("lang", "English")!!
    }

    fun saveCurrencyName(cur: String) {
        settingsPrefs?.edit()?.putString("cur", cur)?.apply()
    }

    fun getCurrencyName(): String {
        return settingsPrefs?.getString("cur", "USD")!!
    }

    fun saveCurrencyID(curId: String) {
        settingsPrefs?.edit()?.putString("curId", curId)?.apply()
    }

    fun getCurrencyID(): String {
        return settingsPrefs?.getString("curId", "151")!!
    }

    fun saveDistanceUnit(unit: String) {
        settingsPrefs?.edit()?.putString("unit", unit)?.apply()
    }

    fun getDistanceUnit(): String {
        return settingsPrefs?.getString("unit", "km")!!
    }

}
