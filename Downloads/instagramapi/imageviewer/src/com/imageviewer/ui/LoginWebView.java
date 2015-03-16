package com.imageviewer.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.imageviewer.R;
import com.imageviewer.domain.UserInfo;
import com.imageviewer.ui.HomeActivity;
import com.imageviewer.utils.Constants;
import com.imageviewer.utils.UserAccount;
import com.imageviewer.utils.VolleyResources;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginWebView extends Activity {

    private String authURLString = Constants.AUTHURL + "?client_id=" + Constants.CLIENT_ID + "&redirect_uri=" + Constants.CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    private String tokenURLString = Constants.TOKENURL + "?client_id=" + Constants.CLIENT_ID + "&client_secret=" + Constants.CLIENT_SECRET + "&redirect_uri=" + Constants.CALLBACKURL + "&grant_type=code";
    private String requestToken = "";
    private WebView webView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_webview);

        webView = (WebView) findViewById(R.id.login_webview);

        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setWebViewClient(new AuthWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(authURLString);

        progressDialog = new ProgressDialog(LoginWebView.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
    }

    public void process() {
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) new URL(tokenURLString).openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
            outputStreamWriter.write("client_id=" + Constants.CLIENT_ID +
                    "&client_secret=" + Constants.CLIENT_SECRET +
                    "&grant_type=authorization_code" +
                    "&redirect_uri=" + Constants.CALLBACKURL +
                    "&code=" + requestToken);

            outputStreamWriter.flush();
            String response = convertStreamToString(con.getInputStream());
            UserInfo info = new Gson().fromJson(response, UserInfo.class);
            UserAccount.init(info);
            getFeeds();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private void getFeeds() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.instagram.com/v1/users/self/feed?access_token=" + UserAccount.getInstance()
                        .getInfo().getAccessToken(), null, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                String response = jsonObject.toString();
                startHomeActivity(response);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError err) {
                System.out.println();
                progressDialog.dismiss();
            }
        });

        VolleyResources.getInstance(getBaseContext()).getmRequestQueue().add(jsonObjectRequest);
    }

    protected void startHomeActivity(String response) {
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("PopularPicsResponse", response);
        startActivity(i);
        progressDialog.dismiss();
        finish();
    }

    public class AuthWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith(Constants.CALLBACKURL)) {
                progressDialog.show();
                System.out.println(url);
                String parts[] = url.split("=");
                requestToken = parts[1];  //Gets the request token
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        process();

                    }
                }).start();

                return true;
            }
            return false;
        }
    }

}
