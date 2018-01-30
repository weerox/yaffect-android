package se.yaffect.android.oauth;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class ClientCredentials {

    private String clientId, clientSecret;

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