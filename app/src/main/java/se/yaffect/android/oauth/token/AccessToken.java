package se.yaffect.android.oauth.token;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import se.yaffect.android.R;

public class AccessToken {
    private String accessToken;
    private TokenType tokenType;
    private int expiresIn;
    private Calendar expireTime;
    private RefreshToken refreshToken;

    public AccessToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferences_oauth), Context.MODE_PRIVATE);
        TimeZone utc = TimeZone.getTimeZone("UTC");
        Calendar expireTime = Calendar.getInstance(utc);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(utc);

        accessToken = sharedPreferences.getString("access_token", null);

        switch (sharedPreferences.getString("token_type", "")) {
            case "Bearer":
                tokenType = TokenType.BEARER;
                break;
            case "":
            default:
                tokenType = TokenType.BEARER;
                break;
        }

        expiresIn = sharedPreferences.getInt("expires_in", 0);

        try {
            expireTime.setTime(simpleDateFormat.parse(sharedPreferences.getString("expire_time", "")));
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        if (sharedPreferences.contains("refresh_token"))
            refreshToken = new RefreshToken(sharedPreferences.getString("refresh_token", null));
        else
            refreshToken = null;
    }

    public AccessToken(Context context, JSONObject response) {
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

            saveAccessToken(context);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    public AccessToken(Context context, String accessToken, TokenType tokenType, int expiresIn) {
        this(context, accessToken, tokenType, expiresIn, null);
    }

    public AccessToken(Context context, String accessToken, TokenType tokenType, int expiresIn, RefreshToken refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.expireTime = calculateExpireTime(expiresIn);
        this.refreshToken = refreshToken;

        saveAccessToken(context);
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

    public static boolean accessTokenExists(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferences_oauth), Context.MODE_PRIVATE);
        return sharedPreferences.contains("access_token");
    }

    private void saveAccessToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferences_oauth), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(utc);

        editor.putString("access_token", accessToken);

        switch (tokenType) {
            case BEARER:
                editor.putString("token_type", "Bearer");
                break;
        }

        editor.putInt("expires_in", expiresIn);
        editor.putString("expire_time", simpleDateFormat.format(expireTime.getTime()));

        if (hasRefreshToken())
            editor.putString("refresh_token", refreshToken.getRefreshToken());

        editor.apply();
    }

    @Override
    public String toString() {
        return accessToken;
    }
}