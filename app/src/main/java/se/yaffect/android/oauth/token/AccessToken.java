package se.yaffect.android.oauth.token;

import org.json.JSONException;
import org.json.JSONObject;

public class AccessToken {
    private String accessToken;
    private TokenType tokenType;
    private int expiresIn;
    private RefreshToken refreshToken;

    public AccessToken(JSONObject response) {
        try {
            accessToken = response.getString("access_token");

            switch (response.getString("token_type")) {
                case "Bearer":
                    tokenType = TokenType.BEARER;
                    break;
                default:
                    tokenType = TokenType.BEARER;
                    break;
            }

            expiresIn = response.getInt("expires_in");

            if (response.has("refresh_token"))
                refreshToken = new RefreshToken(response.getString("refresh_token"));

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    public AccessToken(String accessToken, TokenType tokenType, int expiresIn) {
        this(accessToken, tokenType, expiresIn, null);
    }

    public AccessToken(String accessToken, TokenType tokenType, int expiresIn, RefreshToken refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return accessToken;
    }
}