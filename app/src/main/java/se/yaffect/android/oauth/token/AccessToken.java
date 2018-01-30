package se.yaffect.android.oauth.token;

public class AccessToken {
    private String accessToken;
    private TokenType tokenType;
    private int expiresIn;
    private RefreshToken refreshToken;

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