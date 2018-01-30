package se.yaffect.android.oauth.grant;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import se.yaffect.android.BuildConfig;
import se.yaffect.android.oauth.ClientCredentials;
import se.yaffect.android.oauth.token.AccessToken;

public class ResourceOwnerPasswordCredentialsGrant {

    private ClientCredentials credentials;

    public ResourceOwnerPasswordCredentialsGrant(ClientCredentials credentials) {
        this.credentials = credentials;
    }

    public AccessToken getAccessToken(String username, String password) {
        try {
            String requestBody = "grant_type=password&username=" + URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
            URL url = new URL(BuildConfig.URL_OAUTH2 + "/token");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Basic " + credentials.toString());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.append(requestBody);
            writer.flush();
            writer.close();

            // TODO: read the response from the server
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}