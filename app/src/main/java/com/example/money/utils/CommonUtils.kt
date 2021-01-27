package  com.example.money.utils


import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object CommonUtils {


    private val TAG = "CommonUtils"

    @SuppressLint("all")
    fun getDeviceId(context: Context): String = Settings.Secure.getString(
        context.contentResolver
        , Settings.Secure.ANDROID_ID
    )


    fun isEmailValid(email: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {

        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer)
    }

    val timeStamp: String
        get() = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())


    fun getImage(context: Context, imageName: String?): Int {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName())
    }
}// This utility class is not publicly instantiable
