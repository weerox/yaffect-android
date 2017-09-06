package se.yaffect.android.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

import se.yaffect.android.R;

public class QuestionView extends LinearLayoutCompat {

    private ImageView buttonLike;
    private TextView textTimeAsked;
    private TextView textTimeAnswered;
    private TextView textQuestion;
    private TextView textAnswer;

    private boolean liked = false;
    private String timeAsked;
    private String timeAnswered;
    private String question;
    private String answer;

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

        buttonLike = (ImageView) this.findViewById(R.id.button_like);
        textTimeAsked = (TextView) this.findViewById(R.id.text_time_asked);
        textTimeAnswered = (TextView) this.findViewById(R.id.text_time_answered);
        textQuestion = (TextView) this.findViewById(R.id.text_question);
        textAnswer = (TextView) this.findViewById(R.id.text_answer);

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

    public void setAnswer(String answer) {
        this.answer = answer;
        textAnswer.setText(answer);
    }

    public String getAnswer() {
        return answer;
    }
}
