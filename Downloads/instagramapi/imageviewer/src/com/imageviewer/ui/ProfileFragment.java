package com.imageviewer.ui;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.imageviewer.R;
import com.imageviewer.domain.ProfileDetails;
import com.imageviewer.domain.UserInfo;
import com.imageviewer.utils.UserAccount;
import com.imageviewer.utils.VolleyResources;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    UserInfo userInfo;
    ArrayList<String> listItems;
    private TextView mUserNameTv, mFollowersTv, mFollowingTv;
    private ProfileDetails profileDetails;
    private Typeface mTypeFace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_profile, container, false);
        userInfo = UserAccount.getInstance().getInfo();
        mTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "timeburner_regular.ttf");
        NetworkImageView profileImageView = (NetworkImageView) parentView.findViewById(R.id.profile_pic_iv);
        mUserNameTv = (TextView) parentView.findViewById(R.id.username_tv);
        mFollowersTv = (TextView) parentView.findViewById(R.id.followers_tv);
        mFollowingTv = (TextView) parentView.findViewById(R.id.following_tv);

        mUserNameTv.setTypeface(mTypeFace);
        mFollowersTv.setTypeface(mTypeFace);
        mFollowingTv.setTypeface(mTypeFace);

        profileDetails = ((HomeActivity) getActivity()).getProfileDetails();

        profileImageView.setImageUrl(profileDetails.getData().getProfilePicture(),
                VolleyResources.getInstance(getActivity()).getmImageLoader());
        mUserNameTv.setText(profileDetails.getData().getFullName());
        mFollowersTv.setText("Followers: " + profileDetails.getData().getCounts().getFollowedBy());
        mFollowingTv.setText("Following: " + profileDetails.getData().getCounts().getFollows());

        return parentView;
    }

}
