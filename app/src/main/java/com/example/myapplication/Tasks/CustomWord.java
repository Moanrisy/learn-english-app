package com.example.myapplication.Tasks;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.Tasks.WordTask.CustomLayout;
import com.nex3z.flowlayout.FlowLayout;

public class CustomWord extends AppCompatTextView {

    private static final String TAG = "CustomWord";

    private String word;

    public CustomWord(Context context, String word) {
        super(context);
        this.word = word;

        setText(word);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(15, 15,15, 0);

        setTextColor(getResources().getColor(R.color.custom_view_text_color));

        setLayoutParams(layoutParams);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(20);

        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.custom_word_border));
    }

    public void goToViewGroup(CustomLayout customLayout, FlowLayout sentenceLayout) {

        ViewParent parent = getParent();

        if (parent instanceof CustomLayout) {

            customLayout.removeViewCustomLayout(this);
            sentenceLayout.addView(this);

        } else {

            sentenceLayout.removeView(this);

            customLayout.addViewAt(this);
        }
    }
}
