package  com.example.money.network

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Extension function to convert string to Request body to use with Multipart request
 * @author Mohamed Ibrahim
 */
fun String?.toTextRequestBody(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), this)
}

/**
 * Extension function to convert file to Multipart.Part to use with Multipart request
 * @author Mohamed Ibrahim
 */
fun File.toMultiPart(tag: String): MultipartBody.Part {
    val requestBody = RequestBody.create(MediaType.parse("image/*"), this)
    return MultipartBody.Part.createFormData(tag, name, requestBody)
}

/**
 * Extension function to convert MutableList<File> to Multipart.Part to use with Multipart request
 * @author Mohamed Ibrahim
 */
fun MutableList<File>.totMultiParts(tag: String): MutableList<MultipartBody.Part> {
    return this.asSequence().map {
        it.toMultiPart(tag)
    }.toMutableList()
}