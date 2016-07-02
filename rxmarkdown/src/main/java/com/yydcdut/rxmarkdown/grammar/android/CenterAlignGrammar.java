package com.yydcdut.rxmarkdown.grammar.android;

import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;

import com.yydcdut.rxmarkdown.RxMDConfiguration;

/**
 * Created by yuyidong on 16/5/4.
 */
class CenterAlignGrammar extends AbsAndroidGrammar {

    protected static final String KEY_BACKSLASH_VALUE_1 = KEY_BACKSLASH + KEY_1_CENTER_ALIGN;

    CenterAlignGrammar(@NonNull RxMDConfiguration rxMDConfiguration) {
        super(rxMDConfiguration);
    }

    @Override
    boolean isMatch(@NonNull String text) {
        return text.startsWith(KEY_0_CENTER_ALIGN) && text.endsWith(KEY_1_CENTER_ALIGN);
    }

    @NonNull
    @Override
    SpannableStringBuilder encode(@NonNull SpannableStringBuilder ssb) {
        int index1 = -1;
        while (true) {
            String text = ssb.toString();
            index1 = text.indexOf(KEY_BACKSLASH_VALUE_1);
            if (index1 == -1) {
                break;
            }
            ssb.replace(index1, index1 + KEY_BACKSLASH_VALUE_1.length(), BackslashGrammar.KEY_ENCODE_1);
        }
        return ssb;
    }

    @NonNull
    @Override
    SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb) {
        ssb.delete(0, 1).delete(ssb.length() - 1, ssb.length());
        ssb.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    @NonNull
    @Override
    SpannableStringBuilder decode(@NonNull SpannableStringBuilder ssb) {
        int index1 = -1;
        while (true) {
            String text = ssb.toString();
            index1 = text.indexOf(BackslashGrammar.KEY_ENCODE_1);
            if (index1 == -1) {
                break;
            }
            ssb.replace(index1, index1 + BackslashGrammar.KEY_ENCODE_1.length(), KEY_BACKSLASH_VALUE_1);
        }
        return ssb;
    }
}
