package se.yaffect.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import se.yaffect.android.R;

public class YesNoAnswerView extends AnswerView {

    private LinearLayout buttonNo;
    private LinearLayout buttonYes;
    private ImageView iconNo;
    private ImageView iconYes;

    private State state = State.UNSELECTED;

    public YesNoAnswerView(Context context) {
        super(context);
        initializeView(context);
    }

    public YesNoAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public YesNoAnswerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_yes_no, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        buttonNo = (LinearLayout) this.findViewById(R.id.button_no);
        buttonYes = (LinearLayout) this.findViewById(R.id.button_yes);
        iconNo = (ImageView) buttonNo.getChildAt(0);
        iconYes = (ImageView) buttonYes.getChildAt(0);

        buttonNo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == State.NO) {
                    setState(State.UNSELECTED);
                } else {
                    setState(State.NO);
                }
            }
        });

        buttonYes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == State.YES) {
                    setState(State.UNSELECTED);
                } else {
                    setState(State.YES);
                }
            }
        });
    }

    public void setState(State state) {
        if (this.state != State.UNSELECTED) {
            unselectAll();
        }

        if (state == State.NO) {
            selectNo();
        } else if (state == State.YES) {
            selectYes();
        } else if (state == State.UNSELECTED) {
            unselectAll();
        }
    }

    private void unselectAll() {
        if (state == State.NO) {
            buttonNo.setBackgroundResource(R.drawable.answer_no_background);
            iconNo.setColorFilter(ContextCompat.getColor(getContext(), R.color.no)); // set tint
        } else if (state == State.YES) {
            buttonYes.setBackgroundResource(R.drawable.answer_yes_background);
            iconYes.setColorFilter(ContextCompat.getColor(getContext(), R.color.yes)); // set tint
        }

        this.state = State.UNSELECTED;
    }

    private void selectNo() {
        buttonNo.setBackgroundResource(R.drawable.answer_no_background_selected);
        iconNo.setColorFilter(Color.WHITE);

        this.state = State.NO;
    }

    private void selectYes() {
        buttonYes.setBackgroundResource(R.drawable.answer_yes_background_selected);
        iconYes.setColorFilter(Color.WHITE);

        this.state = State.YES;
    }
}

enum State {
   NO, YES, UNSELECTED;
}