package com.example.dbhelper.room;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.root.dressme.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 10/24/18.
 */

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    private List<Clothes> mDataset;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imgview;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("dressme", "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.infoclothes);
            imgview = (ImageView) v.findViewById(R.id.clotheimage);
        }

        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {
            return imgview;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ClothesAdapter(List<Clothes> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.closet_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataset.get(position).getColor());
        //viewHolder.getImageView().setImageBitmap(BitmapFactory.decodeFile(mDataset.get(position).getPath()));
        Glide.with(mContext.getApplicationContext()).load(mDataset.get(position).getPath()).fitCenter().into(viewHolder.getImageView());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

