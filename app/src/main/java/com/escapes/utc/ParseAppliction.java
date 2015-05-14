package com.escapes.utc;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;


/**
 * Created by empcl_000 on 14/05/2015.
 */
public class ParseAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "o1cuqFu2Youcuswsd2UfZ3AYNhLESBcIKdEqYAI2", "LkEynL2araTA3THPAUGYP6JXc6MZwTTCrNq0rKKo");
      //  ParseInstallation.getCurrentInstallation().saveInBackground();

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}
