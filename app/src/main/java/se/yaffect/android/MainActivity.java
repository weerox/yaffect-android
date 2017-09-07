package se.yaffect.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

import se.yaffect.android.view.MultichoiceAnswerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout main = (LinearLayout) this.findViewById(R.id.layout_main);
        ArrayList<String> alternatives = new ArrayList<String>();
        alternatives.add("Test");
        alternatives.add("Test 3");

        MultichoiceAnswerView multichoiceAnswerView = new MultichoiceAnswerView(this, alternatives);

        main.addView(multichoiceAnswerView);
    }
}
