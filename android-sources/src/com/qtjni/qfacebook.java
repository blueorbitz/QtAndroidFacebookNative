/*
Author: WaiwaiBaka
Description:
This project is a workaround to login facebook using native facebook
android API on Java layer.
Thus, the invoker function is to call to the to prompt for the login.
*/
package com.waiwaibaka.qtandroidfacebooknative;

import java.util.Arrays;
import java.util.List;

import org.qtproject.qt5.android.bindings.QtApplication;
import org.qtproject.qt5.android.bindings.QtActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import com.facebook.*;
import com.facebook.model.*;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.util.Base64;
import java.security.MessageDigest;
import android.content.pm.Signature;

public class qfacebook extends QtActivity
{
    private static native void qtDebug(String s);

    private static qfacebook m_instance;
    private static Context context;

    private UiLifecycleHelper uiHelper;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            m_instance = this;
            context = getApplicationContext();


            uiHelper = new UiLifecycleHelper(this, callback);
            uiHelper.onCreate(savedInstanceState);

            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.waiwaibaka.qtandroidfacebooknative",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                qtDebug("KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        qtDebug("onActivityResult " + requestCode + " " + resultCode + "" + data.toString());
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    public static qfacebook getInstance()
    {
        return m_instance;
    }

    public static void login()
    {
        // Called from JNI
        getInstance().instance_login_intent();
    }

    public void instance_login_intent()
    {
        Session session = Session.getActiveSession();
        if (!session.isOpened() && !session.isClosed()) {
           session.openForRead(new Session.OpenRequest(this)
               .setPermissions(Arrays.asList("public_profile"))
               .setCallback(callback));
        } else {
           Session.openActiveSession(this, true, callback);
        }
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            qtDebug("Logged in...");
        } else if (state.isClosed()) {
            qtDebug("Logged out...");
        }
    }
}
