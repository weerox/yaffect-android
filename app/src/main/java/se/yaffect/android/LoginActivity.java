package se.yaffect.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import se.yaffect.android.oauth.ClientCredentials;
import se.yaffect.android.oauth.exception.OAuthException;
import se.yaffect.android.oauth.grant.ResourceOwnerPasswordCredentialsGrant;
import se.yaffect.android.oauth.token.AccessToken;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout inputEmail;
    private TextInputLayout inputPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (TextInputLayout) findViewById(R.id.input_email);
        inputPassword = (TextInputLayout) findViewById(R.id.input_password);
        buttonLogin = (Button) findViewById(R.id.button_login);

        inputPassword.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login(inputEmail.getEditText().getText().toString(), inputPassword.getEditText().getText().toString());
                    return true;
                }
                return false;
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(inputEmail.getEditText().getText().toString(), inputPassword.getEditText().getText().toString());
            }
        });
    }

    /**
     * Attempt to log in user using provided credentials
     * @param email email to use for log in
     * @param password password to use for login
     */
    private void login(String email, String password) {
        try {
            ResourceOwnerPasswordCredentialsGrant grant = new ResourceOwnerPasswordCredentialsGrant(this, new ClientCredentials(this));
            AccessToken accessToken = grant.getAccessToken(email, password);

            startActivity(new Intent(this, MainActivity.class));
        } catch (OAuthException exception) {
            if (exception.getError().equals("unauthenticated_user")) {
                inputPassword.setError("Error authenticating!");
            }
        }
    }
}