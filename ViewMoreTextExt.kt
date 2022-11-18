import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import com.servicechannel.R

private const val SPACE = " "
private const val ZERO_INDEX = 0
private const val NUMBER_OF_LINES = 3

fun TextView.setViewMoreText(
    startText: String,
    longText: String,
    moreText: String,
    callBack: (() -> Unit)? = null
) {
    val commonText = startText + SPACE + longText
    val spannableTextColor = ContextCompat.getColor(this.context, R.color.scui_color_blue)
    val highLightColor = ContextCompat.getColor(this.context, R.color.scui_color_white)

    this.doOnPreDraw {
        text = if (lineCount > NUMBER_OF_LINES) {
            highlightColor = highLightColor
            movementMethod = LinkMovementMethod.getInstance()
            getViewMoreSpannable(
                spannableTextColor = spannableTextColor,
                startText = startText,
                commonText = commonText,
                moreText = moreText,
                lineEndIndex = layout.getLineEnd(NUMBER_OF_LINES - 1),
            ) {
                callBack?.invoke()
            }
        } else {
            getDefaultSpannable(
                spannableTextColor = spannableTextColor,
                startText = startText,
                commonText = commonText,
            )
        }
    }
}

private fun getViewMoreSpannable(
    spannableTextColor: Int,
    startText: String,
    commonText: String,
    moreText: String,
    lineEndIndex: Int,
    callBack: (() -> Unit)? = null
): SpannableStringBuilder {

    val spannableText = "...$moreText"
    var endIndex = lineEndIndex - spannableText.length
    endIndex = if (endIndex < commonText.length) endIndex else commonText.length
    val text = commonText.subSequence(ZERO_INDEX, endIndex).toString() + SPACE + spannableText

    val spannableString = getDefaultSpannable(
        spannableTextColor = spannableTextColor,
        startText = startText,
        commonText = text,
    )
    if (text.contains(spannableText)) {
        spannableString.setSpan(
            object : ViewMoreSpannable(
                spannableTextColor = spannableTextColor
            ) {
                override fun onClick(widget: View) {
                    callBack?.invoke()
                }
            },
            text.indexOf(spannableText),
            text.indexOf(spannableText) + spannableText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableString
}

private fun getDefaultSpannable(
    spannableTextColor: Int,
    startText: String,
    commonText: String,
): SpannableStringBuilder {
    val spannableString = SpannableStringBuilder(commonText)
    spannableString.setSpan(
        ForegroundColorSpan(spannableTextColor),
        ZERO_INDEX,
        startText.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}
