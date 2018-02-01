package se.yaffect.android.oauth.token;

class AccessTokenNew {
    private static final AccessTokenNew ourInstance = new AccessTokenNew();

    static AccessTokenNew getInstance() {
        return ourInstance;
    }

    private AccessTokenNew() {
    }
}
