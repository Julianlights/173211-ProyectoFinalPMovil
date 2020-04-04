package com.example.mvp.Modelos;

import com.example.mvp.Urls.UrlsApis;
import com.example.mvp.Ngrok.Ngrok;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginModelo {

    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams params =  new  RequestParams ();


    boolean conectedSucces = false;
    String password;
    String name;
    String token;

    public void setName(String name) {
    this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConectedSucces() {
        return conectedSucces;
    }
    public String getToken() {
        return token;
    }
    public String getBaseUrl() {
        return "";
    }

    public void Login(){

        params.put("username", name);
        params.put("password", password);

        client.post(Ngrok.URL + UrlsApis.login , params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                token = response.optString("token");
                conectedSucces = true;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
            }
        });
    }
}
