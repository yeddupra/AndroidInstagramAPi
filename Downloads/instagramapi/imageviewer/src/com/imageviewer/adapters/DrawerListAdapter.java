package com.imageviewer.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.imageviewer.R;

import java.util.ArrayList;


/**
 * DrawerListAdapter is the Custom Adapter to show each item in the Side drawer
 *
 * @author Aman Sharma
 */
public class DrawerListAdapter extends ArrayAdapter<String> {

    private Activity mContext;
    private ArrayList<String> mTitles;
    private Typeface mTypeFace;

    public DrawerListAdapter(Activity context, ArrayList<String> titles) {
        super(context, R.layout.list_item_drawer);
        this.mContext = context;
        this.mTitles = titles;
        this.mTypeFace = Typeface.createFromAsset(context.getAssets(), "timeburner_regular.ttf");

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View rowView = view;

        if (rowView == null) {

			/* Layout Inflater is used to inflate each row as a custom
             * list item
			 * */
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_drawer, null, true);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) rowView.findViewById(R.id.side_menu_title);
            rowView.setTag(viewHolder);
        }

		/* Get view holder object */
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.title.setText(mTitles.get(position));
        holder.title.setTypeface(mTypeFace);

        return rowView;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    /**
     * View holder having list item views
     *
     * @author Aman Sharma
     */
    static class ViewHolder {
        public TextView title;

    }

}

