package com.example.theimpossiblehangman.External;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton to manage request queue
 */
public class RequestManager {

    private static RequestQueue queue;

    public static void instantiateRequestManager(Context appContext ) {
        if ( queue == null ) {
            queue = Volley.newRequestQueue(appContext);
        }
    }

    public static void addRequest(Request<String> request) {
        queue.add(request);
    }

}
