package se.yaffect.android.oauth.exception;

import org.json.JSONException;
import org.json.JSONObject;

public class OAuthException extends Exception {
    private String error;
    private String errorDescription = "";

    public OAuthException(JSONObject response) {
        super("The OAuth server responded with an error");
        try {
            error = response.getString("error");
            if (response.has("error_description"))
                errorDescription = response.getString("error_description");
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
