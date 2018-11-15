package io.jcal.thenews.ui.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class DataBindingAdapter {
    companion object {

        private const val DF_SIMPLE_STRING = "yyyy-MM-dd HH:mm:ss"
        private const val DF_TIME_STRING = "HH:mm"
        private const val DF_STANDARD_STRING = "MMM-dd-yyyy"
        private const val DF_UTC_STRING = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val DF_DAY_STRING = "MMM dd"

        @JvmStatic
        @BindingAdapter("android:setDate")
        fun setDate(textView: TextView, date: String?) {
            date?.let {
                val utcDate =
                    SimpleDateFormat(DF_UTC_STRING, Locale.getDefault()).parse(it)
                val format = SimpleDateFormat(DF_DAY_STRING, Locale.getDefault())
                val text = format.format(utcDate)
                textView.text = text ?: ""
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get()
                    .load(url)
                    .centerCrop()
                    .fit()
                    .into(imageView)
            }
        }
    }
}