package se.yaffect.android.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
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
        init(context);
    }

    public YesNoAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public YesNoAnswerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_yes_no, this, true);

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

        super.init();
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
            ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                    buttonNo.getBackground().mutate(),
                    "tint",
                    new ArgbEvaluator(),
                    ContextCompat.getColor(getContext(), R.color.no),
                    Color.WHITE
            );
            colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            colorAnimator.start();

            iconNo.setColorFilter(ContextCompat.getColor(getContext(), R.color.no));

        } else if (state == State.YES) {
            ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                    buttonYes.getBackground().mutate(),
                    "tint",
                    new ArgbEvaluator(),
                    ContextCompat.getColor(getContext(), R.color.yes),
                    Color.WHITE
            );
            colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            colorAnimator.start();

            iconYes.setColorFilter(ContextCompat.getColor(getContext(), R.color.yes));
        }

        this.state = State.UNSELECTED;
    }

    private void selectNo() {
        ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                buttonNo.getBackground().mutate(),
                "tint",
                new ArgbEvaluator(),
                Color.WHITE,
                ContextCompat.getColor(getContext(), R.color.no)
        );
        colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        colorAnimator.start();

        iconNo.setColorFilter(Color.WHITE);

        this.state = State.NO;
    }

    private void selectYes() {
        ObjectAnimator colorAnimator = ObjectAnimator.ofObject(
                buttonYes.getBackground().mutate(),
                "tint",
                new ArgbEvaluator(),
                Color.WHITE,
                ContextCompat.getColor(getContext(), R.color.yes)
        );
        colorAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        colorAnimator.start();

        iconYes.setColorFilter(Color.WHITE);

        this.state = State.YES;
    }
}

enum State {
   NO, YES, UNSELECTED;
}