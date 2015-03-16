package com.imageviewer.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.NetworkImageView;
import com.imageviewer.R;
import com.imageviewer.domain.FeedInfo;
import com.imageviewer.utils.VolleyResources;

import java.util.ArrayList;


/**
 * TrendingShowListAdapter is the Custom Adapter to show each Show
 * in a custom styled List Row
 *
 * @author Aman Sharma
 */
public class PictureListAdapter extends ArrayAdapter<FeedInfo> {

    private Activity mContext;
    private ArrayList<FeedInfo> mFeeds;
    private Typeface mTypeFace;

    public PictureListAdapter(Activity context, ArrayList<FeedInfo> feeds) {
        super(context, R.layout.list_item_drawer);
        this.mContext = context;
        this.mFeeds = feeds;
        this.mTypeFace = Typeface.createFromAsset(context.getAssets(), "timeburner_regular.ttf");

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        View rowView = view;

//		if(rowView == null) {

			/* Layout Inflater is used to inflate each row as a custom
             * list item
			 * */
        LayoutInflater inflater = mContext.getLayoutInflater();
        rowView = inflater.inflate(R.layout.list_item_feeds, null, true);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.imagesNiv = (NetworkImageView) rowView.findViewById(R.id.list_item_niv);
        viewHolder.userNameTv = (TextView) rowView.findViewById(R.id.user_name_tv);
        viewHolder.likesTv = (TextView) rowView.findViewById(R.id.likes_tv);
        viewHolder.videoView = (VideoView) rowView.findViewById(R.id.list_item_vv);

        rowView.setTag(viewHolder);
//		}

		/* Get view holder object */
        final ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.userNameTv.setText(mFeeds.get(position).getFullName());
        holder.userNameTv.setTypeface(mTypeFace);

        holder.likesTv.setText(mFeeds.get(position).getLikes() + " likes");
        holder.likesTv.setTypeface(mTypeFace);

        String url = mFeeds.get(position).getUrl();
//		if(mFeeds.get(position).getType().equals("video")) {
//			holder.videoView.setVisibility(View.VISIBLE);
//			holder.imagesNiv.setVisibility(View.GONE);
//			holder.videoView.setVideoPath(url);
//		}
//		else {
        holder.imagesNiv.setVisibility(View.VISIBLE);
        holder.videoView.setVisibility(View.GONE);
        holder.imagesNiv.setImageUrl(url,
                VolleyResources.getInstance(mContext).getmImageLoader());
        holder.videoView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (holder.videoView.isPlaying()) {
                    holder.videoView.pause();
                } else {
                    holder.videoView.start();
                }

            }
        });
        if (mFeeds.get(position).getType().equals("video")) {

            holder.imagesNiv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    holder.videoView.setVideoPath(mFeeds.get(position).getVideoUrl());
                    holder.videoView.requestFocus();
                    holder.videoView.start();
                    holder.imagesNiv.setVisibility(View.GONE);
                    holder.videoView.setVisibility(View.VISIBLE);
                }
            });
        }

//		}
        return rowView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFeeds.size();
    }

    /**
     * View holder having list item views
     *
     * @author Aman Sharma
     */
    static class ViewHolder {
        public NetworkImageView imagesNiv;
        public TextView userNameTv;
        public TextView likesTv;
        public VideoView videoView;
    }

}

