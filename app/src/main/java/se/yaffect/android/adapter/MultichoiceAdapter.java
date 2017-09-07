package se.yaffect.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import se.yaffect.android.R;

public class MultichoiceAdapter extends ArrayAdapter<String> {
    public MultichoiceAdapter(Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String alternative = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_multichoice, parent, false);
        }

        CheckBox checkBoxAlternative = (CheckBox) convertView.findViewById(R.id.checkBox);
        checkBoxAlternative.setText(alternative);

        return convertView;
    }
}
