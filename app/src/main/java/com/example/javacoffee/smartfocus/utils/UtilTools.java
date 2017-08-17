package com.example.javacoffee.smartfocus.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class UtilTools {
    public static void setFont(Context mContext, TextView textView){
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(),"fonts/FONT.TTF");
        textView.setTypeface(fontType);
    }
}
