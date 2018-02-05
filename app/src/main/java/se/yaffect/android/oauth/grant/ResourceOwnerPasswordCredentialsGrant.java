package se.yaffect.android.oauth.grant;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import se.yaffect.android.R;
import se.yaffect.android.oauth.ClientCredentials;
import se.yaffect.android.oauth.exception.OAuthException;
import se.yaffect.android.oauth.token.AccessToken;

public class ResourceOwnerPasswordCredentialsGrant extends AsyncTask<String, Integer, Bundle> {

    private Context context;
    private ClientCredentials credentials;

    public ResourceOwnerPasswordCredentialsGrant(Context context, ClientCredentials credentials) {
        this.context = context;
        this.credentials = credentials;
    }

    public AccessToken getAccessToken(String username, String password) throws OAuthException {
        Bundle bundle = null;
        try {
            bundle = this.execute(username, password).get();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (ExecutionException exception) {
            exception.printStackTrace();
        }

        int responseCode = bundle.getInt("responseCode");
        String responseMessage = bundle.getString("responseMessage");
        String responseBody = bundle.getString("responseBody");

        JSONObject jsonResponse = null;

        try {
            jsonResponse = new JSONObject(responseBody);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }

        if (responseCode >= 400) {
            throw new OAuthException(jsonResponse);
        }

        return new AccessToken(context, jsonResponse);
    }

    @Override
    protected Bundle doInBackground(String... loginCredentials) {
        try {
            InputStream rawResource = context.getResources().openRawResource(R.raw.app);
            Properties properties = new Properties();
            properties.load(rawResource);

            String requestBody = "grant_type=password&username=" + URLEncoder.encode(loginCredentials[0], "UTF-8") + "&password=" + URLEncoder.encode(loginCredentials[1], "UTF-8");
            URL url = new URL(properties.getProperty("URL_OAUTH2") + "/token");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", "Basic " + credentials.toString());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.append(requestBody);
            writer.flush();
            writer.close();

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader;

            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException exception) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            Bundle bundle = new Bundle();
            bundle.putInt("responseCode", connection.getResponseCode());
            bundle.putString("responseMessage", connection.getResponseMessage());
            bundle.putString("responseBody", stringBuilder.toString());

            return bundle;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        throw new OAuthException(new JSONObject("{}"));
        super.onPostExecute(s);
    }
}
