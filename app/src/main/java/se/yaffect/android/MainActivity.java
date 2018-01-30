package se.yaffect.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (!sharedPreferences.contains("access_token")) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        setContentView(R.layout.activity_main);

        LinearLayout main = (LinearLayout) this.findViewById(R.id.layout_main);
    }
}
