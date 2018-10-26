package com.vanshika.hackabit.medai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.FileProvider;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;*/
import com.vanshika.hackabit.medai.Adapters.ViewPagerAdapter;
import com.vanshika.hackabit.medai.Camera.CustomDialogActivity;
import com.vanshika.hackabit.medai.Camera.CustomDialogClass;
import com.vanshika.hackabit.medai.Fragments.CurrentDoseFragment;
import com.vanshika.hackabit.medai.Fragments.HistoryFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    CurrentDoseFragment currentDoseFragment;
    HistoryFragment historyFragment;
    ViewPagerAdapter viewPagerAdapter;
    FloatingActionButton fab;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);

        //Initializing the tablayout
        textView=findViewById(R.id.imageText);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {



            //getTextFromImage();
                CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
                cdd.show();

                
               /* if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    //dispatchTakePictureIntent();
                }*/
            }
        });
    }
    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        currentDoseFragment=new CurrentDoseFragment();
        historyFragment=new HistoryFragment();
        adapter.addFragment(currentDoseFragment,"Current Dose");
        adapter.addFragment(historyFragment,"HISTORY");
        viewPager.setAdapter(adapter);
   }

    public void getTextFromImage() {
        Bitmap bitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.trial);
        /*TextRecognizer textRecognizer=new TextRecognizer.Builder(getApplicationContext()).build();
        if (textRecognizer.isOperational()){
            Toast.makeText(this, "Could not to take text", Toast.LENGTH_SHORT).show();
        }
        else {
            Frame frame=new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items=textRecognizer.detect(frame);
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<items.size();i++){
                TextBlock myItem=items.valueAt(i);
                sb.append(myItem.getValue());sb.append("/n");
            }
            textView.setText(sb.toString());
        }*/

    }
   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                return;
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "socity.app.camera.provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/myCapturedImages");
        if (!myDir.exists())
            myDir.mkdirs();
        File image = File.createTempFile(
                imageFileName,  *//* prefix *//*
                ".jpg",         *//* suffix *//*
                myDir      *//* directory *//*
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }*/

}
