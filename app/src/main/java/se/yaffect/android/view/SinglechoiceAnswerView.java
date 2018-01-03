package se.yaffect.android.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import se.yaffect.android.R;

public class SinglechoiceAnswerView extends AnswerView {
    private RadioGroup listAlternatives;

    private ArrayList<String> alternatives = new ArrayList<String>();
    private ArrayList<Integer> checked = new ArrayList<Integer>();

    public SinglechoiceAnswerView(Context context) {
        super(context);
        init(context);
    }

    public SinglechoiceAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SinglechoiceAnswerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_singlechoice, this, true);

        listAlternatives = (RadioGroup) this.findViewById(R.id.list_alternatives);

        super.init();
    }

    public void addAlternative(String alternative) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.item_singlechoice, listAlternatives, false);

        radioButton.setText(alternative);

        // TODO: add onCheckedChangeListener

        listAlternatives.addView(radioButton);
    }

    public void addAlternatives(ArrayList<String> alternatives) {
        for (String alternative : alternatives)
            addAlternative(alternative);
    }
}
