package rikkeisoft.nguyenducdung.com.contactrecycleview.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextView extends AppCompatTextView {
    private static Typeface tf = null;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(getTypeFace(getContext()));
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(getTypeFace(getContext()));
    }

    public synchronized static Typeface getTypeFace(Context context){
        if (tf == null ){
            tf = Typeface.createFromAsset(
                    context.getAssets(),
                    "font_i.TTF"
            );
        }
        return tf;
    }
}
