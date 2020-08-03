package com.example.dbhelper.room;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.root.dressme.R;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by root on 10/20/18.
 */
@Layout(R.layout.dress_card_view)
public class DressMeCard {
    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.bottom)
    private ImageView profileImageView2;

    private Outfit mOutfit;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public DressMeCard(Context context, Outfit outfit, SwipePlaceHolderView swipeView) {
        mContext = context;
        mOutfit = outfit;
        mSwipeView = swipeView;
    }

    public int getoutfitid() {
        return mOutfit.getIdC();
    }

    public String getoutfitTop() {
        return mOutfit.getTop();
    }

    public String getoutfitBottom() {
        return mOutfit.getBottom();
    }

    @Resolve
    private void onResolved(){
        Glide.with(mContext).load(mOutfit.getTop()).fitCenter().into(profileImageView);
        Glide.with(mContext).load(mOutfit.getBottom()).fitCenter().into(profileImageView2);
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        //mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}
