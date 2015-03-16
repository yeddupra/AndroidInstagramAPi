package com.imageviewer.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.imageviewer.R;
import com.imageviewer.adapters.PictureListAdapter;
import com.imageviewer.domain.Datum;
import com.imageviewer.domain.FeedInfo;
import com.imageviewer.domain.PopularMedia;

import java.util.ArrayList;


public class FeedsFragment extends Fragment {

    ArrayList<FeedInfo> feeds;
    private ListView mImagesLv;
    private PictureListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_feeds, null);
        mImagesLv = (ListView) parentView.findViewById(R.id.pics_lv);
        feeds = new ArrayList<FeedInfo>();

        PopularMedia popularMedia = ((com.imageviewer.ui.HomeActivity) getActivity()).getPopularMedia();

        for (Datum data : popularMedia.getData()) {
            if (data.getType().equals("video")) {
                feeds.add(new FeedInfo(data.getImages().getLowResolution().getUrl(),
                        data.getVideos().getLowResolution().getUrl(),
                        data.getUser().getFullName(), data.getLikes().getCount(),
                        data.getType()));
            } else {
                feeds.add(new FeedInfo(data.getImages().getStandardResolution().getUrl(),
                        data.getUser().getFullName(), data.getLikes().getCount(),
                        data.getType()));
            }
        }

        mAdapter = new PictureListAdapter(getActivity(), feeds);
        mImagesLv.setAdapter(mAdapter);

        System.out.println();
        return parentView;
    }
}
