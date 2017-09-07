package se.yaffect.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import se.yaffect.android.R;
import se.yaffect.android.adapter.MultichoiceAdapter;

public class MultichoiceAnswerView extends AnswerView {

    private ListView listAlternatives;

    private MultichoiceAdapter multichoiceAdapter;

    public MultichoiceAnswerView(Context context) {
        super(context);
        initializeView(context);
    }

    public MultichoiceAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public MultichoiceAnswerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_multichoice, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        listAlternatives = (ListView) this.findViewById(R.id.list_alternatives);
        multichoiceAdapter = new MultichoiceAdapter(getContext());

        listAlternatives.setAdapter(multichoiceAdapter);
    }

    public void addAlternative(String alternative) {
        multichoiceAdapter.add(alternative);
    }
}
