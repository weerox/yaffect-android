package se.yaffect.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import se.yaffect.android.R;

public class QuestionView extends LinearLayout {

    private TextView question;
    private TextView answer;

    public QuestionView(Context context) {
        super(context);
        initializeView(context);
    }

    public QuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public QuestionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_question, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        question = (TextView) this.findViewById(R.id.text_question);
        answer = (TextView) this.findViewById(R.id.text_answer);
    }
}
