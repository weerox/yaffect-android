package se.yaffect.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

import se.yaffect.android.R;

public class MultichoiceAnswerView extends AnswerView {
    private LinearLayout listAlternatives;

    private ArrayList<String> alternatives = new ArrayList<String>();
    private ArrayList<Integer> checked = new ArrayList<Integer>();

    public MultichoiceAnswerView(Context context) {
        super(context);
        init(context);
    }

    public MultichoiceAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultichoiceAnswerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_multichoice, this);

        listAlternatives = (LinearLayout) this.findViewById(R.id.list_alternatives);

        super.init();
    }

    public void addAlternative(String alternative) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CheckBox checkBox = (CheckBox) layoutInflater.inflate(R.layout.item_multichoice, null);

        checkBox.setText(alternative);

        // TODO: add onClickListener

        listAlternatives.addView(checkBox);
    }

    public void addAlternatives(ArrayList<String> alternatives) {
        for (String alternative : alternatives)
            addAlternative(alternative);
    }
}
