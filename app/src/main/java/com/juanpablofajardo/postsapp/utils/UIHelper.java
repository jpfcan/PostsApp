package com.juanpablofajardo.postsapp.utils;

import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class UIHelper {

    private UIHelper() {
    }


    public static SpannableString underlineText(@NonNull String textToUnderLine) {
        SpannableString underlinedText = new SpannableString(textToUnderLine);
        underlinedText.setSpan(new UnderlineSpan(), 0, textToUnderLine.length(), SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        return underlinedText;
    }
}
