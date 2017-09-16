package se.yaffect.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

import se.yaffect.android.R;
import se.yaffect.android.adapter.MultichoiceAdapter;

public class MultichoiceAnswerView extends AnswerView {

    private ListView listAlternatives;

    private MultichoiceAdapter multichoiceAdapter;

    private ArrayList<String> alternatives;
    private ArrayList<Integer> checked;

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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_answer_multichoice, this);

        listAlternatives = (ListView) this.findViewById(R.id.list_alternatives);
        listAlternatives.setAdapter(multichoiceAdapter);
        listAlternatives.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    if (!checked.contains(position)) {
                        checked.add(position);
                    }
                } else {
                    if (checked.contains(position)) {
                        checked.remove(position);
                    }
                }
            }
        });

        super.init();
    }

    public void addAlternative(String alternative) {
        multichoiceAdapter.add(alternative);
    }
}
