package  com.example.money.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bumptech.glide.Glide
import com.google.gson.JsonObject


fun ImageView?.loadImage(url: String?) {
    this?.context?.let {
        Glide.with(it)
            .load(url)
            .into(this)
    }
}


fun ImageView?.loadImage(url: Int?) {
    this?.context?.let {
        Glide.with(it)
            .load(url)
            .into(this)
    }
}

fun ImageView?.loadLocalImage(imageName: String?) {
    this?.context?.let {
        Glide.with(it)
            .load(CommonUtils.getImage(it, imageName))
            .into(this)
    }
}

fun ImageView.setDrwableVector(@DrawableRes icon: Int, @AttrRes color: Int) {
    context?.colorVectorIconWithAttrColor(icon, color)?.let {
        setImageDrawable(it)
    }
}

fun Context.colorVectorIconWithAttrColor(@DrawableRes icon: Int, @AttrRes color: Int): Drawable? {
    val drawable: Drawable? = VectorDrawableCompat.create(resources, icon, null)
    return drawable?.let {
        val colorRes = getColorFromAttr(color)
        DrawableCompat.wrap(it)
        DrawableCompat.setTint(it, colorRes)
        it
    }
}

fun Context.getColorFromAttr(@AttrRes colorAttr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(colorAttr, typedValue, true)
    return typedValue.data
}

//fun Double.formatToTwoPoint(): Double {
//    val df = DecimalFormat("0.00E0")
//    return df.format(this).toDouble()
//}

fun Double.format(point: Int): Double {
    val multi = Math.pow(10.0, point.toDouble())
    return Math.ceil(this * multi) / multi
}

fun String.convertToJson(key: String): JsonObject {
    return JsonObject().apply {
        addProperty(key, this@convertToJson)
    }
}

