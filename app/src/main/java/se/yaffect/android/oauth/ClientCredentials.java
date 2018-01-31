package se.yaffect.android.oauth;

import android.content.Context;
import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import se.yaffect.android.R;

public class ClientCredentials {

    private String clientId, clientSecret;

    public ClientCredentials(Context context) {
        try {
            InputStream rawResource = context.getResources().openRawResource(R.raw.app);
            Properties properties = new Properties();
            properties.load(rawResource);

            clientId = properties.getProperty("CLIENT_ID");
            clientSecret = properties.getProperty("CLIENT_SECRET", "");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ClientCredentials(String clientId) {
        this(clientId, "");
    }

    public ClientCredentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public String toString() {
        try {
            return Base64.encodeToString((getClientId() + ":" + getClientSecret()).getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}