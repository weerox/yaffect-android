package se.yaffect.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import se.yaffect.android.R;
import se.yaffect.android.adapter.MultichoiceAdapter;

public class MultichoiceAnswerView extends AnswerView {

    private ListView listAlternatives;

    private MultichoiceAdapter multichoiceAdapter;

    private ArrayList<String> alternatives;

    public MultichoiceAnswerView(Context context, ArrayList<String> alternatives) {
        super(context);
        this.alternatives = alternatives;
        init(context);
    }

    public MultichoiceAnswerView(Context context, AttributeSet attrs, ArrayList<String> alternatives) {
        super(context, attrs);
        this.alternatives = alternatives;
        init(context);
    }

    public MultichoiceAnswerView(Context context, AttributeSet attrs, int defStyle, ArrayList<String> alternatives) {
        super(context, attrs, defStyle);
        this.alternatives = alternatives;
        init(context);
    }

    private void init(Context context) {
        multichoiceAdapter = new MultichoiceAdapter(context, alternatives);

        Log.d("se.yaffect.android", "start inflation");
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_multichoice, this);
        Log.d("se.yaffect.android", "inflation completed");

        listAlternatives = (ListView) this.findViewById(R.id.list_alternatives);
        Log.d("se.yaffect.android", "set adapter");
        listAlternatives.setAdapter(multichoiceAdapter);

        super.init();
    }

    public void addAlternative(String alternative) {
        Log.d("se.yaffect.android.d", "add alternative");
        multichoiceAdapter.add(alternative);
    }
}
