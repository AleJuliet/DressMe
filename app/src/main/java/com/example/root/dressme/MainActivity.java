package com.example.root.dressme;

import android.Manifest;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dbhelper.room.Clothes;
import com.example.dbhelper.room.ClothesDatabase;
import com.example.dbhelper.room.DressMeCard;
import com.example.dbhelper.room.Outfit;
import com.example.dbhelper.room.OutfitLog;
import com.example.dbhelper.room.Utils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveResourceClient;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ClothesDatabase clothesDatabase;

    private SwipePlaceHolderView mSwipeView;
    RelativeLayout checkwear;
    private Context mContext;

    private int totaloutfits = 5;

    public View preView;

    /*
    * Necessary for google drive
     */
    /*private GoogleSignInClient mGoogleSignInClient;
    private DriveClient mDriveClient;
    private DriveResourceClient mDriveResourceClient;
    private Bitmap mBitmapToSave;*/

    private static final String TAG = "DressMe";

    /*
    * For the menu
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(MainActivity.this, AddPhotoTagsActivity.class);
                    MainActivity.this.startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(MainActivity.this, MyCloset.class);
                    MainActivity.this.startActivity(intent2);
                    /*Intent intent2 = new Intent(MainActivity.this, TagScreens2.class);
                    MainActivity.this.startActivity(intent2);*/
                    return true;
            }
            return false;
        }
    };

    /*
    * Necessary for Storage Permissions
     */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logbutton = (ImageView) findViewById(R.id.loghistory);
        ImageView medalpoints = (ImageView) findViewById(R.id.medalpoints);

        mContext = getApplicationContext();

        /*
        * Checks if db exists
        *  if it doesn't creates a new instance and
        *   inserts a few rows
         */
        clothesDatabase = ClothesDatabase
                .getDatabase(getApplicationContext());
        Clothes first = clothesDatabase.daoAccess().getFirst();
        if (first==null) {
            Log.d(TAG,"Db doesnt exist");
            createdb();
        }
        else
            Log.d(TAG,"Db does exist");

        preView = getWindow().getDecorView();



        /*
        * Creates the dress me cards
         */
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        ;

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));


        for(Outfit profile : Utils.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new DressMeCard(mContext, profile, mSwipeView));
            RelativeLayout sharebutton = (RelativeLayout) findViewById(R.id.sharethislayout);
            sharebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    takeScreenShotAndShare(mContext, mSwipeView, true, "What do you think about this outfit?");
                }
            });
        }

        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                // do something
                totaloutfits -= 1;
                if (totaloutfits==1) {
                    addoutfits();
                    totaloutfits = 5;
                }
                RelativeLayout sharebutton = (RelativeLayout) findViewById(R.id.sharethislayout);
                sharebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takeScreenShotAndShare(mContext, mSwipeView, true, "What do you think about this outfit?");
                    }
                });
            }
        });

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogScreen.class);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });

        medalpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilePoints.class);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });

        findViewById(R.id.nop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totaloutfits -= 1;
                if (totaloutfits==1) {
                    addoutfits();
                    totaloutfits = 5;
                }
                mSwipeView.doSwipe(false);
                RelativeLayout sharebutton = (RelativeLayout) findViewById(R.id.sharethislayout);
                sharebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takeScreenShotAndShare(mContext, mSwipeView, true, "What do you think about this outfit?");
                    }
                });
            }
        });

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totaloutfits -= 1;
                if (totaloutfits==1) {
                    addoutfits();
                    totaloutfits = 5;
                }
                mSwipeView.doSwipe(true);
            }
        });

        findViewById(R.id.yesp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkwear = (RelativeLayout) findViewById(R.id.wearthislayout);
                checkwear.setVisibility(View.VISIBLE);

                /*
                * Get top and bottom and save it to the log
                 */
                List<Object> test = mSwipeView.getAllResolvers();
                DressMeCard test2 = (DressMeCard) test.get(0);
                clothesDatabase = ClothesDatabase
                        .getDatabase(getApplicationContext());
                OutfitLog newoutfit = new OutfitLog();
                Date c = Calendar.getInstance().getTime();
                newoutfit.setallcontent(test2.getoutfitTop(),test2.getoutfitBottom(),c,test2.getoutfitid());

                clothesDatabase.daoAccess().insertOutfit(newoutfit);

                Intent intent = new Intent(MainActivity.this, LogScreen.class);
                MainActivity.this.startActivityForResult(intent,1);

            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static void takeScreenShotAndShare(final Context context, View view, boolean incText, String text){
        try{

            File mPath = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "screenshot.png");
            //File imageDirectory = new File(mPath, "screenshot.png");

            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            FileOutputStream fOut = new FileOutputStream(mPath);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, fOut);
            fOut.flush();
            fOut.close();

            final Intent shareIntent = new Intent(Intent.ACTION_SEND);
            Uri pictureUri = Uri.fromFile(mPath);
            shareIntent.setType("image/*");
            if(incText){
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
            }
            shareIntent.putExtra(Intent.EXTRA_STREAM, pictureUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(shareIntent, "Share image using"));
        }catch (Throwable tr){
            Log.d(TAG, "Couldn't save screenshot", tr);
        }

    }


    public void onClicklog(View view) {

    }

    /*
    * To add outfits when there are no more cards
     */
    public void addoutfits() {
        for(Outfit profile : Utils.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new DressMeCard(mContext, profile, mSwipeView));
        }
    }

    public  void isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("not", "Permission is granted");
            } else {

                Log.v("not", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("not", "Permission is granted");
        }
    }

    private void checkdbcontent() {
        clothesDatabase = ClothesDatabase
                .getDatabase(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Clothes clothesf = clothesDatabase.daoAccess().getFirst();
            }
        }) .start();
    }

    private void createdb() {
        clothesDatabase = ClothesDatabase
                .getDatabase(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    clothesDatabase.daoAccess().nukeTable();
                } catch (SQLException expected) {
                    // OK
                    Log.d(TAG,"Error when deleting table content");
                }
                Clothes cl1 =new Clothes();
                cl1.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "red", "all", "informal");
                Clothes cl2 =new Clothes();
                cl2.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "blue", "all", "everyday");
                Clothes cl3 =new Clothes();
                cl3.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "gray", "all", "everyday");
                Clothes cl4 =new Clothes();
                cl4.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "purple", "all", "everyday");
                Clothes cl5 =new Clothes();
                cl5.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "green", "all", "formal");
                Clothes cl6 =new Clothes();
                cl6.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "red", "all", "informal");
                Clothes cl7 =new Clothes();
                cl7.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "purple", "all", "everyday");
                Clothes cl8 =new Clothes();
                cl8.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "black", "all", "everyday");
                Clothes cl9 =new Clothes();
                cl9.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "pattern", "all", "everyday");
                Clothes cl10 =new Clothes();
                cl10.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "white", "all", "everyday");
                Clothes cl11 =new Clothes();
                cl11.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "black", "all", "everyday");
                Clothes cl12 =new Clothes();
                cl12.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "blue", "all", "formal");
                Clothes cl13 =new Clothes();
                cl13.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "black", "all", "everyday");
                Clothes cl14 =new Clothes();
                cl14.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "black", "all", "informal");
                Clothes cl15 =new Clothes();
                cl15.setallcontent("https://docs.google.com/uc?id=1Pka_1xb56xSJ7U-RMQ3NrZ_wfAKvXtaL", "top", "white", "all", "everyday");

                Clothes cl16 =new Clothes();
                cl16.setallcontent("https://docs.google.com/uc?id=1ySWkAlse5TsRyJ4bVCbUcvk5cX0lUaqg", "bottom", "black", "all", "informal");
                Clothes cl17 =new Clothes();
                cl17.setallcontent("https://docs.google.com/uc?id=1ySWkAlse5TsRyJ4bVCbUcvk5cX0lUaqg", "bottom", "pattern", "all", "everyday");
                Clothes cl18 =new Clothes();
                cl18.setallcontent("https://docs.google.com/uc?id=1ySWkAlse5TsRyJ4bVCbUcvk5cX0lUaqg", "bottom", "black", "all", "everyday");
                Clothes cl19 =new Clothes();
                cl19.setallcontent("https://docs.google.com/uc?id=1ySWkAlse5TsRyJ4bVCbUcvk5cX0lUaqg", "bottom", "blue", "all", "everyday");
                Clothes cl20 =new Clothes();
                cl20.setallcontent("https://docs.google.com/uc?id=1ySWkAlse5TsRyJ4bVCbUcvk5cX0lUaqg", "bottom", "pattern", "summer", "informal");
                clothesDatabase.daoAccess().insertAll(cl1,cl2,cl3,cl4,cl5,cl6,cl7,cl8,cl9,cl10,cl11,cl12,cl13,cl14,cl15,cl16,cl17,cl18,cl19,cl20);
            }
        }) .start();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            checkwear = (RelativeLayout) findViewById(R.id.wearthislayout);
            checkwear.setVisibility(View.INVISIBLE);
            // Do something...
        }
    }


}
