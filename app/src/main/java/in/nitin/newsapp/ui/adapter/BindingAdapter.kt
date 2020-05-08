package `in`.nitin.newsapp.ui.adapter

import `in`.nitin.newsapp.R
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImageUrl(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }


}

/*date format converter*/
@BindingAdapter("dateConverter")
fun convertDate(textView: TextView, mDate: String?) {
    var date = mDate
    val DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    val DATE_FORMAT_O = "h:mm a dd/MMM/yy"
    var spf = SimpleDateFormat(DATE_FORMAT_I, Locale.UK)
    var newDate: Date? = null
    try {
        newDate = spf.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    spf = SimpleDateFormat(DATE_FORMAT_O, Locale.UK)
    date = spf.format(newDate)
    textView.text = date
}





