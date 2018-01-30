package se.yaffect.android.oauth.token;

public class RefreshToken {
    private String refreshToken;

    public RefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return refreshToken;
    }
}
