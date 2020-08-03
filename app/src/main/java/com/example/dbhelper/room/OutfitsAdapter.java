package com.example.dbhelper.room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.dressme.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 11/10/18.
 */

public class OutfitsAdapter extends RecyclerView.Adapter<OutfitsAdapter.ViewHolder> {

    private List<OutfitLog> mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imgview;
        private final ImageView imgview2;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("dressme", "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.myloginfo);
            imgview = (ImageView) v.findViewById(R.id.mylogimage);
            imgview2 = (ImageView) v.findViewById(R.id.mylogimage2);
        }

        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {
            return imgview;
        }
        public ImageView getImageView2() {
            return imgview2;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OutfitsAdapter(List<OutfitLog> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OutfitsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mylog_row_item, viewGroup, false);

        return new OutfitsAdapter.ViewHolder(v);
    }

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(OutfitsAdapter.ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        Date today = new Date();
        Date datecard = mDataset.get(position).getDate();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(today);
        String date2 = df.format(datecard);

        if (date.equals(date2) && position==0)
            viewHolder.getTextView().setText("You are wearing this today!");
        else
            viewHolder.getTextView().setText("You wore this on "+date2);

        //viewHolder.getImageView().setImageBitmap(BitmapFactory.decodeFile(mDataset.get(position).getPath()));
        Glide.with(mContext.getApplicationContext()).load(mDataset.get(position).getTop()).fitCenter().into(viewHolder.getImageView());
        Glide.with(mContext.getApplicationContext()).load(mDataset.get(position).getBottom()).fitCenter().into(viewHolder.getImageView2());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


