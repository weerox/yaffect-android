package se.yaffect.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import se.yaffect.android.oauth.token.AccessToken;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!AccessToken.accessTokenExists(this)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            // TODO: access token exists
        }

        setContentView(R.layout.activity_main);

        LinearLayout main = (LinearLayout) this.findViewById(R.id.layout_main);
    }
}
