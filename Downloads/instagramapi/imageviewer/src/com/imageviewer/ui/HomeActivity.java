package com.imageviewer.ui;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.imageviewer.R;
import com.imageviewer.adapters.DrawerListAdapter;
import com.imageviewer.domain.PopularMedia;
import com.imageviewer.domain.ProfileDetails;
import com.imageviewer.utils.Constants;
import com.imageviewer.utils.UserAccount;
import com.imageviewer.utils.VolleyResources;

import org.json.JSONObject;

import java.util.ArrayList;


public class HomeActivity extends ActionBarActivity {

    ActionBarDrawerToggle mActionBarDrawerToggle;
    ProgressDialog progressDialog;
    ArrayList<String> listItems;
    private PopularMedia popularMedia;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private DrawerListAdapter mAdapter;
    private ProfileDetails profileDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String response = getIntent().getExtras().getString("PopularPicsResponse");
        popularMedia = new Gson().fromJson(response, PopularMedia.class);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);

        listItems = new ArrayList<>();
        listItems.add("Feeds");
        listItems.add("Trending");
        listItems.add("Profile");

        setUpDrawer();
        showFeeds();
    }

    private void setUpDrawer() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mAdapter = new DrawerListAdapter(this, listItems);
        mDrawerList.setAdapter(mAdapter);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switchFragment(position);

            }
        });
    }


    protected void switchFragment(int position) {
        switch (position) {
            case 0:
                getMyFeeds();
                break;

            case 1:
                getPopularFeeds();
                break;

            case 2:
                getProfileInfo();
                break;

            default:
                break;
        }

        mDrawerLayout.closeDrawers();
    }

    private void showProfile() {
        getFragmentManager().beginTransaction()
                .replace(R.id.content_holder, new ProfileFragment(), "")
                .commit();

    }

    private void showFeeds() {
        getFragmentManager().beginTransaction()
                .replace(R.id.content_holder, new FeedsFragment(), "")
                .commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    public PopularMedia getPopularMedia() {
        return popularMedia;
    }

    public void setPopularMedia(PopularMedia popularMedia) {
        this.popularMedia = popularMedia;
    }

    private void getMyFeeds() {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.instagram.com/v1/users/self/feed?access_token=" + UserAccount.getInstance()
                        .getInfo().getAccessToken(), null, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                String response = jsonObject.toString();
                popularMedia = new Gson().fromJson(response, PopularMedia.class);
                progressDialog.dismiss();
                showFeeds();
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


    private void getPopularFeeds() {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.instagram.com/v1/media/popular?client_id=" + Constants.CLIENT_ID,
                null, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                String response = jsonObject.toString();
                popularMedia = new Gson().fromJson(response, PopularMedia.class);
                progressDialog.dismiss();
                showFeeds();
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

    private void getProfileInfo() {
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://api.instagram.com/v1/users/" + UserAccount.getInstance().getInfo().getUser().getId() + "/?access_token=" + UserAccount.getInstance()
                        .getInfo().getAccessToken(),
                null, new Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject jsonObject) {
                String response = jsonObject.toString();
                profileDetails = new Gson().fromJson(response, ProfileDetails.class);
                progressDialog.dismiss();
                showProfile();
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

    public ProfileDetails getProfileDetails() {
        return profileDetails;
    }

    public void setProfileDetails(ProfileDetails profileDetails) {
        this.profileDetails = profileDetails;
    }


}
