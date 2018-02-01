package se.yaffect.android.oauth.token;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

public class AccessToken {
    private String accessToken;
    private TokenType tokenType;
    private int expiresIn;
    private Calendar expireTime;
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

            expireTime = calculateExpireTime(expiresIn);

            if (response.has("refresh_token"))
                refreshToken = new RefreshToken(response.getString("refresh_token"));
            else
                refreshToken = null;

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
        this.expireTime = calculateExpireTime(expiresIn);
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

    private Calendar calculateExpireTime(int expiresIn) {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar expireTime = Calendar.getInstance(utc);
        expireTime.add(Calendar.SECOND, expiresIn);

        return expireTime;
    }

    public boolean hasExpired() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar now = Calendar.getInstance(utc);

        // if compareTo is >= 0, now is after the expireTime
        return now.compareTo(expireTime) >= 0;
    }

    public boolean hasRefreshToken() {
        return refreshToken == null ? false : true;
    }

    @Override
    public String toString() {
        return accessToken;
    }
}