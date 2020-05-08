package `in`.nitin.newsapp.ui.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan

object SpannableClass {

    fun spannable(func: () -> SpannableString) = func()
    private fun span(s: CharSequence, o: Any) =
        (if (s is String) SpannableString(s) else s as? SpannableString
            ?: SpannableString("")).apply {
            setSpan(
                o,
                0,
                length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

    operator fun SpannableString.plus(s: SpannableString) =
        SpannableString(TextUtils.concat(this, s))

    fun underline(s: SpannableString) = span(s, UnderlineSpan())

    fun color(color: Int, s: String) =
        span(
            s,
            ForegroundColorSpan(color)
        )

}