package se.yaffect.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

import se.yaffect.android.R;

public class AnswerView extends LinearLayoutCompat {

    private ImageView buttonLike;
    private TextView textTimeAsked;
    private TextView textTimeAnswered;
    private TextView textQuestion;

    private boolean liked = false;
    private String timeAsked;
    private String timeAnswered;
    private String question;

    private UUID uuid;

    public AnswerView(Context context) {
        super(context);
    }

    public AnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        buttonLike = (ImageView) this.findViewById(R.id.button_like);
        textTimeAsked = (TextView) this.findViewById(R.id.text_time_asked);
        textTimeAnswered = (TextView) this.findViewById(R.id.text_time_answered);
        textQuestion = (TextView) this.findViewById(R.id.text_question);

        buttonLike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setLiked(!isLiked()); // toggles the boolean to the other value
            }
        });
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        if (this.liked != liked) {
            this.liked = liked;

            if (liked) {
                buttonLike.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_like));
            } else {
                buttonLike.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_like_outline));
            }
        }
    }

    public void setTimeAsked(String timeAsked) {
        this.timeAsked = timeAsked;
        textTimeAsked.setText(timeAsked);
    }

    public String getTimeAsked() {
        return timeAsked;
    }

    public void setTimeAnswered(String timeAnswered) {
        this.timeAnswered = timeAnswered;
        textTimeAnswered.setText(timeAnswered);
    }

    public String getTimeAnswered() {
        return timeAnswered;
    }

    public void setQuestion(String question) {
        this.question = question;
        textQuestion.setText(question);
    }

    public String getQuestion() {
        return question;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}