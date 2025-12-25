package com.yucheng.smarthealthpro.login.facebook;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.AuthenticationTokenClaims;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.orhanobut.logger.Logger;
import com.yucheng.smarthealthpro.R;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FaceBookLogin {
    public static FaceBookLogin faceBookLogin;
    private Activity activity;
    private CallbackManager callbackManager;
    private List<String> permissions;

    private FaceBookLogin(final Activity activity) {
        this.activity = activity;
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        if (currentAccessToken != null && !currentAccessToken.isExpired()) {
            activity.sendBroadcast(new Intent("com.login.facebook").putExtra("accessToken", currentAccessToken.getToken()).putExtra("openID", currentAccessToken.getUserId()));
            return;
        }
        this.callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() { // from class: com.yucheng.smarthealthpro.login.facebook.FaceBookLogin.1
            @Override // com.facebook.FacebookCallback
            public void onSuccess(LoginResult loginResult) {
                Activity activity2 = activity;
                Toast.makeText(activity2, activity2.getString(R.string.authorization_succeeded), 0).show();
                AccessToken accessToken = loginResult.getAccessToken();
                Logger.i("==accessToken==" + accessToken.getToken() + "==accessToken==" + accessToken.getUserId(), new Object[0]);
                activity.sendBroadcast(new Intent("com.login.facebook").putExtra("accessToken", accessToken.getToken()).putExtra("openID", accessToken.getUserId()));
            }

            @Override // com.facebook.FacebookCallback
            public void onCancel() {
                Activity activity2 = activity;
                Toast.makeText(activity2, activity2.getString(R.string.authorization_cancle), 0).show();
            }

            @Override // com.facebook.FacebookCallback
            public void onError(FacebookException error) {
                error.printStackTrace();
                Activity activity2 = activity;
                Toast.makeText(activity2, activity2.getString(R.string.authorization_failed), 0).show();
            }
        });
        this.permissions = Arrays.asList("email", "user_likes", "user_photos", AuthenticationTokenClaims.JSON_KEY_USER_BIRTHDAY, "public_profile", AuthenticationTokenClaims.JSON_KEY_USER_FRIENDS);
    }

    public static synchronized FaceBookLogin getInstance(Activity activity) {
        if (faceBookLogin == null) {
            faceBookLogin = new FaceBookLogin(activity);
        }
        return faceBookLogin;
    }

    public void login() throws FacebookException, NoSuchAlgorithmException {
        LoginManager.getInstance().logInWithReadPermissions(this.activity, this.permissions);
    }

    public CallbackManager getCallbackManager() {
        return this.callbackManager;
    }

    public void clear() {
        LoginManager.getInstance().logOut();
        this.callbackManager = null;
        faceBookLogin = null;
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() { // from class: com.yucheng.smarthealthpro.login.facebook.FaceBookLogin.2
            @Override // com.facebook.GraphRequest.GraphJSONObjectCallback
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    object.getString("last_name");
                    object.getString("email");
                    String str = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=normal";
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
}
