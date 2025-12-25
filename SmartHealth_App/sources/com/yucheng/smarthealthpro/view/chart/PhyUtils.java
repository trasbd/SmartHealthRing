package com.yucheng.smarthealthpro.view.chart;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import com.yucheng.ycbtsdk.Constants;

/* loaded from: classes5.dex */
public class PhyUtils {
    public static SpannableString parseTime(int runTime) {
        int i2 = runTime - (runTime % 60);
        int i3 = (i2 / 60) % 60;
        return setSpaning(String.format("%d h %d min", Integer.valueOf((i2 - (i3 * 60)) / Constants.DATATYPE.FactoryTest), Integer.valueOf(i3)));
    }

    private static SpannableString setSpaning(String str) {
        SpannableString spannableString = new SpannableString(str);
        int iLastIndexOf = spannableString.toString().lastIndexOf(" h ");
        int iLastIndexOf2 = spannableString.toString().lastIndexOf(" min");
        if (iLastIndexOf != -1) {
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#808080"));
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);
            spannableString.setSpan(foregroundColorSpan, iLastIndexOf, " h ".length() + iLastIndexOf, 17);
            spannableString.setSpan(absoluteSizeSpan, iLastIndexOf, " h ".length() + iLastIndexOf, 17);
        }
        if (iLastIndexOf2 != -1) {
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(Color.parseColor("#808080"));
            AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(14, true);
            spannableString.setSpan(foregroundColorSpan2, iLastIndexOf2, " min".length() + iLastIndexOf2, 17);
            spannableString.setSpan(absoluteSizeSpan2, iLastIndexOf2, " min".length() + iLastIndexOf2, 17);
        }
        return spannableString;
    }
}
