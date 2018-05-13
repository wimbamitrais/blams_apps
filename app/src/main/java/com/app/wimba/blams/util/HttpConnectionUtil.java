package com.app.wimba.blams.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.app.wimba.blams.R;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by IT02107 on 5/13/2018.
 */

public class HttpConnectionUtil {

    private static Context context;

    public static HttpURLConnection createConnection(String path) throws MalformedURLException, ProtocolException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", context.getResources().getString(R.string.content_type));
            connection.addRequestProperty("Client-Service", context.getResources().getString(R.string.client_service));
            connection.addRequestProperty("Auth-Key", context.getResources().getString(R.string.auth_key));
            connection.setDoOutput(true);
        } catch (Exception ex){
            Log.e("ERROR","Error when creating connection with URL " + path);
        }

        return connection;
    }

    public static void setContext(Context ctx) {
        context = ctx;
    }
}
