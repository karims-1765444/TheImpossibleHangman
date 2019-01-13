package com.example.theimpossiblehangman.External;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.common.base.Function;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatamuseClient {

    private static final String URL = "http://api.datamuse.com/words?ml=%s&max=4";

    public static void getHint(String word, final Function<String, Void> completionHandler ) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                String.format(URL, word),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String hint = "Related words:\n";
                        try {
                            JSONArray words = new JSONArray(response);
                            if (words.length()==0){
                                hint = "Wow! That is a tough word. Good luck!";
                            } else
                                for (int i = 0; i < words.length(); i++) {
                                    JSONObject wordObj = words.getJSONObject(i);
                                    hint+= wordObj.getString("word")+" ";
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        completionHandler.apply(hint);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                completionHandler.apply("That didn't work!");
            }
        });
        RequestManager.addRequest(stringRequest);

    }

}
