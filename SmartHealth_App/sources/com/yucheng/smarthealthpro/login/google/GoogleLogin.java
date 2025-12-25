package com.yucheng.smarthealthpro.login.google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orhanobut.logger.Logger;

/* loaded from: classes5.dex */
public class GoogleLogin {
    public static final int REQUEST_CODE_GG = 1435;
    public static GoogleLogin googleLogin;
    private Activity context;
    private SignInClient oneTapClient;

    private GoogleLogin(Activity context) {
        this.context = context;
    }

    public static synchronized GoogleLogin getInstance(Activity context) {
        if (googleLogin == null) {
            googleLogin = new GoogleLogin(context);
        }
        return googleLogin;
    }

    public void login(String clientID, int selfRequestCode) {
        Log.wtf(FirebaseAnalytics.Event.LOGIN, "google_client_id:" + clientID);
        this.context.startActivityForResult(GoogleSignIn.getClient(this.context, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(clientID).requestEmail().build()).getSignInIntent(), selfRequestCode);
    }

    public void callBack(int requestCode, Intent data, int selfRequestCode) throws Throwable {
        if (requestCode == selfRequestCode) {
            handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(data));
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) throws Throwable {
        try {
            GoogleSignInAccount result = completedTask.getResult(ApiException.class);
            String idToken = result.getIdToken();
            result.getEmail();
            String id = result.getId();
            result.getAccount();
            this.context.sendBroadcast(new Intent("com.login.google").putExtra("accessToken", idToken).putExtra("openID", id));
        } catch (ApiException e2) {
            e2.printStackTrace();
        }
    }

    public void clear() {
        googleLogin = null;
    }

    public void loginFirebase(final Activity activity, String webClientId, final int selfRequestCode) {
        Log.w(FirebaseAnalytics.Event.LOGIN, webClientId);
        BeginSignInRequest beginSignInRequestBuild = BeginSignInRequest.builder().setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder().setSupported(true).build()).setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true).setServerClientId(webClientId).setFilterByAuthorizedAccounts(false).build()).setAutoSelectEnabled(true).build();
        SignInClient signInClient = Identity.getSignInClient(this.context);
        this.oneTapClient = signInClient;
        signInClient.beginSignIn(beginSignInRequestBuild).addOnSuccessListener(new OnSuccessListener() { // from class: com.yucheng.smarthealthpro.login.google.GoogleLogin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                GoogleLogin.lambda$loginFirebase$0(activity, selfRequestCode, (BeginSignInResult) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.yucheng.smarthealthpro.login.google.GoogleLogin$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                GoogleLogin.lambda$loginFirebase$1(exc);
            }
        });
    }

    static /* synthetic */ void lambda$loginFirebase$0(Activity activity, int i2, BeginSignInResult beginSignInResult) {
        try {
            ActivityCompat.startIntentSenderForResult(activity, beginSignInResult.getPendingIntent().getIntentSender(), i2, null, 0, 0, 0, null);
        } catch (IntentSender.SendIntentException e2) {
            Logger.e("Couldn't start One Tap UI: " + e2.getLocalizedMessage(), new Object[0]);
        }
    }

    static /* synthetic */ void lambda$loginFirebase$1(Exception exc) {
        Logger.w("" + exc.getMessage(), new Object[0]);
        exc.printStackTrace();
    }

    public void callback(Context context, int requestCode, Intent data, int selfRequestCode) {
        try {
            if (this.oneTapClient == null) {
                this.oneTapClient = Identity.getSignInClient(context);
            }
            SignInCredential signInCredentialFromIntent = this.oneTapClient.getSignInCredentialFromIntent(data);
            String googleIdToken = signInCredentialFromIntent.getGoogleIdToken();
            String id = signInCredentialFromIntent.getId();
            if (TextUtils.isEmpty(googleIdToken) || TextUtils.isEmpty(id)) {
                return;
            }
            Logger.d("Got ID token.");
            context.sendBroadcast(new Intent("com.login.google").putExtra("accessToken", googleIdToken).putExtra("openID", id));
        } catch (ApiException e2) {
            e2.printStackTrace();
        }
    }
}
