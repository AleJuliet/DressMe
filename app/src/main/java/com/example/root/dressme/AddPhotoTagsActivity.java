package com.example.root.dressme;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dbhelper.room.Clothes;
import com.example.dbhelper.room.ClothesDatabase;
import com.example.dbhelper.room.Outfit;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class AddPhotoTagsActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView mImageView;
    String mCurrentPhotoPath;
    List<Clothes> clothesl;
    Clothes newcloth;

    private static final String DATABASE_NAME = "clothes_database_alexa";
    private ClothesDatabase clothesDatabase;

    Map<String, Integer> stateButtons = new HashMap<>();
    Map<String, String> colorButtons = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_tags);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageView = (ImageView) findViewById(R.id.imagephot);
        clothesDatabase = ClothesDatabase
                .getDatabase(getApplicationContext());

        newcloth = new Clothes();

        /*
        * Set the colors of the tags
         */
        setcolors();

        dispatchTakePictureIntent();
    }

    public void onClickFin(View v) {
        /*Log.d("SAVED2",newcloth.getColor());
        Log.d("SAVED2",newcloth.getOcassion());
        Log.d("SAVED2",newcloth.getType());
        Log.d("SAVED2",newcloth.getPath());

        clothesDatabase.daoAccess().insertAll(newcloth);*/
        Intent intent = new Intent(AddPhotoTagsActivity.this, MainActivity.class);
        AddPhotoTagsActivity.this.startActivityForResult(intent,1);
    }

    public void onClick(View v) {

        Button button = (Button) findViewById(v.getId());
        String namebutton = getResources().getResourceEntryName(v.getId());

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape2.setStroke(5, Color.parseColor(colorButtons.get(namebutton)));

        if (stateButtons.get(namebutton)==0) {
            shape2.setColor(Color.parseColor("#8389a5"));
            stateButtons.put(namebutton,1);
        } else {
            shape2.setColor(Color.parseColor("#ffffff"));
            stateButtons.put(namebutton,0);
        }

        switch (namebutton) {
            case "elegant":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setOcassion("elegant");
                } else {
                    newcloth.setOcassion("");
                }

                break;

            case "casual":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setOcassion("casual");
                } else {
                    newcloth.setOcassion("");
                }
                break;

            case "cotton":
                break;

            case "wool":
                break;

            case "linen":
                break;

            case "silk":
                break;
            case "stripes":
                break;
            case "checks":
                break;
            case "other":
                break;

            default:
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setColor(namebutton);
                } else {
                    newcloth.setColor("");
                }
                break;
        }

        button.setBackground(shape2);


    }

    public void onClick2(View v) {

        FrameLayout button = (FrameLayout) findViewById(v.getId());
        String namebutton = getResources().getResourceEntryName(v.getId());

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape2.setStroke(2, Color.parseColor("#602d8f"));

        if (stateButtons.get(namebutton)==0) {
            shape2.setColor(Color.parseColor("#8389a5"));
            stateButtons.put(namebutton,1);
        } else {
            shape2.setColor(Color.parseColor("#ffffff"));
            stateButtons.put(namebutton,0);
        }


        switch (namebutton) {
            case "topf":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setType("top");
                } else {
                    newcloth.setType("");
                }

                break;

            case "pantsf":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setType("bottom");
                } else {
                    newcloth.setType("");
                }
                break;

            case "coldf":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setWeather("cold");
                } else {
                    newcloth.setWeather("");
                }

                break;

            case "warmf":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setWeather("warm");
                } else {
                    newcloth.setWeather("");
                }

                break;

            case "allwf":
                if (stateButtons.get(namebutton)==1) {
                    newcloth.setWeather("all");
                } else {
                    newcloth.setWeather("");
                }

                break;

            default:
                break;
        }

        button.setBackground(shape2);


    }

    private void setcolors(){
        /*GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape.setColor(Color.parseColor("#ffffff"));
        shape.setStroke(5, Color.parseColor("#8389a5"));
        Button top = (Button) findViewById(R.id.top);
        top.setBackground(shape);
        stateButtons.put("top",0);
        colorButtons.put("top","#8389a5");
        Button pants = (Button) findViewById(R.id.pants);
        pants.setBackground(shape);
        stateButtons.put("pants",0);
        colorButtons.put("pants","#8389a5");*/
        stateButtons.put("topf",0);
        stateButtons.put("pantsf",0);

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape2.setColor(Color.parseColor("#ffffff"));
        shape2.setStroke(5, Color.parseColor("#8389a5"));
        /*Button weather = (Button) findViewById(R.id.cold);
        weather.setBackground(shape2);
        stateButtons.put("cold",0);
        colorButtons.put("cold","#8389a5");
        Button weather2 = (Button) findViewById(R.id.warm);
        weather2.setBackground(shape2);
        stateButtons.put("warm",0);
        colorButtons.put("warm","#8389a5");*/
        /*Button weather3 = (Button) findViewById(R.id.allweathers);
        weather3.setBackground(shape2);
        stateButtons.put("allweathers",0);
        colorButtons.put("allweathers","#8389a5");*/
        stateButtons.put("coldf",0);
        stateButtons.put("warmf",0);
        stateButtons.put("allwf",0);

        GradientDrawable shape3 = new GradientDrawable();
        shape3.setShape(GradientDrawable.RECTANGLE);
        shape3.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape3.setColor(Color.parseColor("#ffffff"));
        shape3.setStroke(5, Color.parseColor("#babbbb"));
        Button matt = (Button) findViewById(R.id.wool);
        matt.setBackground(shape3);
        stateButtons.put("wool",0);
        colorButtons.put("wool","#babbbb");
        Button matt2 = (Button) findViewById(R.id.cotton);
        matt2.setBackground(shape3);
        stateButtons.put("cotton",0);
        colorButtons.put("cotton","#babbbb");
        Button matt3 = (Button) findViewById(R.id.linen);
        matt3.setBackground(shape3);
        stateButtons.put("linen",0);
        colorButtons.put("linen","#babbbb");
        Button matt4 = (Button) findViewById(R.id.silk);
        matt4.setBackground(shape3);
        stateButtons.put("silk",0);
        colorButtons.put("silk","#babbbb");

        GradientDrawable shape4 = new GradientDrawable();
        shape4.setShape(GradientDrawable.RECTANGLE);
        shape4.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape4.setColor(Color.parseColor("#ffffff"));
        shape4.setStroke(5, Color.parseColor("#8389a5"));
        Button typ = (Button) findViewById(R.id.stripes);
        typ.setBackground(shape4);
        stateButtons.put("stripes",0);
        colorButtons.put("stripes","#8389a5");
        Button typ2 = (Button) findViewById(R.id.checks);
        typ2.setBackground(shape4);
        stateButtons.put("checks",0);
        colorButtons.put("checks","#8389a5");
        Button typ3 = (Button) findViewById(R.id.otherpattern);
        typ3.setBackground(shape4);
        stateButtons.put("otherpattern",0);
        colorButtons.put("otherpattern","#8389a5");

        GradientDrawable shape5 = new GradientDrawable();
        shape5.setShape(GradientDrawable.RECTANGLE);
        shape5.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape5.setColor(Color.parseColor("#ffffff"));
        shape5.setStroke(5, Color.parseColor("#babbbb"));
        Button occa = (Button) findViewById(R.id.elegant);
        occa.setBackground(shape5);
        stateButtons.put("elegant",0);
        colorButtons.put("elegant","#babbbb");
        Button occa2 = (Button) findViewById(R.id.casual);
        occa2.setBackground(shape5);
        stateButtons.put("casual",0);
        colorButtons.put("casual","#babbbb");

        GradientDrawable shape6 = new GradientDrawable();
        shape6.setShape(GradientDrawable.RECTANGLE);
        shape6.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape6.setColor(Color.parseColor("#ffffff"));
        shape6.setStroke(5, Color.parseColor("#000000"));
        Button color1 = (Button) findViewById(R.id.black);
        color1.setBackground(shape6);
        stateButtons.put("black",0);
        colorButtons.put("black","#000000");

        GradientDrawable shape7 = new GradientDrawable();
        shape7.setShape(GradientDrawable.RECTANGLE);
        shape7.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape7.setColor(Color.parseColor("#ffffff"));
        shape7.setStroke(5, Color.parseColor("#ffffff"));
        Button color2 = (Button) findViewById(R.id.white);
        color2.setBackground(shape7);
        stateButtons.put("white",0);
        colorButtons.put("white","#ffffff");

        GradientDrawable shape8 = new GradientDrawable();
        shape8.setShape(GradientDrawable.RECTANGLE);
        shape8.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape8.setColor(Color.parseColor("#ffffff"));
        shape8.setStroke(5, Color.parseColor("#808080"));
        Button color3 = (Button) findViewById(R.id.gray);
        color3.setBackground(shape8);
        stateButtons.put("gray",0);
        colorButtons.put("gray","#808080");

        GradientDrawable shape9 = new GradientDrawable();
        shape9.setShape(GradientDrawable.RECTANGLE);
        shape9.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape9.setColor(Color.parseColor("#ffffff"));
        shape9.setStroke(5, Color.parseColor("#ffff00"));
        Button color4 = (Button) findViewById(R.id.yellow);
        color4.setBackground(shape9);
        stateButtons.put("yellow",0);
        colorButtons.put("yellow","#ffff00");

        GradientDrawable shape10 = new GradientDrawable();
        shape10.setShape(GradientDrawable.RECTANGLE);
        shape10.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape10.setColor(Color.parseColor("#ffffff"));
        shape10.setStroke(5, Color.parseColor("#ff0698"));
        Button color5 = (Button) findViewById(R.id.pink);
        color5.setBackground(shape10);
        stateButtons.put("pink",0);
        colorButtons.put("pink","#ff0698");

        GradientDrawable shape11 = new GradientDrawable();
        shape11.setShape(GradientDrawable.RECTANGLE);
        shape11.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape11.setColor(Color.parseColor("#ffffff"));
        shape11.setStroke(5, Color.parseColor("#6b2600"));
        Button color6 = (Button) findViewById(R.id.brown);
        color6.setBackground(shape11);
        stateButtons.put("brown",0);
        colorButtons.put("brown","#6b2600");

        GradientDrawable shape12 = new GradientDrawable();
        shape12.setShape(GradientDrawable.RECTANGLE);
        shape12.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape12.setColor(Color.parseColor("#ffffff"));
        shape12.setStroke(5, Color.parseColor("#8600ac"));
        Button color7 = (Button) findViewById(R.id.purple);
        color7.setBackground(shape12);
        stateButtons.put("purple",0);
        colorButtons.put("purple","#8600ac");

        GradientDrawable shape13 = new GradientDrawable();
        shape13.setShape(GradientDrawable.RECTANGLE);
        shape13.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape13.setColor(Color.parseColor("#ffffff"));
        shape13.setStroke(5, Color.parseColor("#299201"));
        Button color8 = (Button) findViewById(R.id.green);
        color8.setBackground(shape13);
        stateButtons.put("green",0);
        colorButtons.put("green","#299201");

        GradientDrawable shape14 = new GradientDrawable();
        shape14.setShape(GradientDrawable.RECTANGLE);
        shape14.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape14.setColor(Color.parseColor("#ffffff"));
        shape14.setStroke(5, Color.parseColor("#0100ff"));
        Button color9 = (Button) findViewById(R.id.blue);
        color9.setBackground(shape14);
        stateButtons.put("blue",0);
        colorButtons.put("blue","#0100ff");

        GradientDrawable shape15 = new GradientDrawable();
        shape15.setShape(GradientDrawable.RECTANGLE);
        shape15.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape15.setColor(Color.parseColor("#ffffff"));
        shape15.setStroke(5, Color.parseColor("#ff0000"));
        Button color10 = (Button) findViewById(R.id.red);
        color10.setBackground(shape15);
        stateButtons.put("red",0);
        colorButtons.put("red","#ff0000");
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.root.dressme.fileprovider",
                        photoFile);



                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(10, 10);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Glide.with(this).load(mCurrentPhotoPath).fitCenter().into(mImageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        newcloth.setPath(mCurrentPhotoPath);
        return image;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(AddPhotoTagsActivity.this, MainActivity.class);
                    AddPhotoTagsActivity.this.startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    Intent intent2 = new Intent(AddPhotoTagsActivity.this, MyCloset.class);
                    AddPhotoTagsActivity.this.startActivity(intent2);
                    return true;
            }
            return false;
        }
    };

}
