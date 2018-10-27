package com.vanshika.hackabit.medai;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.FileProvider;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;*/
import com.soundcloud.android.crop.Crop;
import com.soundcloud.android.crop.CropImageActivity;
import com.vanshika.hackabit.medai.Adapters.ViewPagerAdapter;
import com.vanshika.hackabit.medai.Camera.CustomDialogActivity;
import com.vanshika.hackabit.medai.Camera.CustomDialogClass;
import com.vanshika.hackabit.medai.Fragments.CurrentDoseFragment;
import com.vanshika.hackabit.medai.Fragments.HistoryFragment;
import com.vanshika.hackabit.medai.Models.MedicineDb;
import com.vanshika.hackabit.medai.Retrofit.APIService;
import com.vanshika.hackabit.medai.Retrofit.ApiUtils;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.NewDose;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    CurrentDoseFragment currentDoseFragment;
    HistoryFragment historyFragment;
    ViewPagerAdapter viewPagerAdapter;
    FloatingActionButton fab;
    TextView textView;
    HashSet<String> extractedText;
    private APIService mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        mAPIService = ApiUtils.getAPIService();
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

        //fab = findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {



            //getTextFromImage();


                
               *//* if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    //dispatchTakePictureIntent();
                }*//*
            }
        });*/
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.capture){
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            } else {
                dispatchTakePictureIntent();
            }

            return true;
        }return true;
    }

    @Override
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
Uri photoURI;
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
                 photoURI = FileProvider.getUriForFile(this,
                        "com.vanshika.hackabit.medai.provider",
                        photoFile);
                //Uri photoURI= FileProvider.getUriForFile(R.drawable.camera);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                //workOnDatabase(testExtract[0]);


            }
        }
        //workOnDatabase(testExtract[0]);
    }

    private void workOnDatabase(String s) {
        mAPIService.sendMedName("[\"ac\"]").enqueue(new Callback<MedicineDb>() {
            @Override
            public void onResponse(Call<MedicineDb> call, Response<MedicineDb> response) {
                //Log.v("line 195",response.body().toString());
                Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
                CustomDialogClass.addData(response.body().getMedicineName(),response.body().getDosage()+" "+response.body().getSideEffects()+" "+response.body().getNtb());
                CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
                cdd.show();
            }

            @Override
            public void onFailure(Call<MedicineDb> call, Throwable t) {
                Log.v("line200",t.getMessage());
            }
        });
       /* mAPIService.getMedicineInfo().enqueue(new Callback<MedicineDb>() {
            @Override
            public void onResponse(Call<MedicineDb> call, final Response<MedicineDb> response) {
                if (response.isSuccessful()){
                    Log.v("line207",response.body().getDosage());
                    *//*final AppDatabase
                            db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"new_dose")
                            .build();
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.userDao().inserNewDose(new NewDose(response.body().getMedicineName(),response.body().getSideEffects(),response.body().getDosage(),response.body().getNtb(),"","",""));
                        }
                    });*//*

                }
            }

            /*@Override
            public void onFailure(Call<MedicineDb> call, Throwable t) {
                Log.v("line213",t.getMessage());
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == 1){
            Crop.of(photoURI,photoURI ).asSquare().start(this);

        }else if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            //doSomethingWithCroppedImage(outputUri);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        extractedText = VisionAPI.extractTextFromImage(mCurrentPhotoPath);
                        String testExtract = "";
                        /*for(String s: extractedText){
                            testExtract = s;
                            Log.v("mainfile295", testExtract);

                        }*/
                        Log.v("mainfile262", extractedText.toString());
                        workOnDatabase(extractedText.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /*private File createImageFile() throws IOException {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/myCapturedImages");
            if (!myDir.exists())
                myDir.mkdirs();
            final File image = File.createTempFile(
                    imageFileName,  *//* prefix *//*
                ".jpg",         *//* suffix *//*
                myDir      *//* directory *//*
        );
        Log.v("mainfile248", ""+image.length());
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.v("mainfile249", String.valueOf(image.exists()));
        Log.v("mainfile195",mCurrentPhotoPath);
        final String[] testExtract = {""};
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    extractedText=VisionAPI.extractTextFromImage(mCurrentPhotoPath);
                    Log.v("mainfile200",extractedText.toString());
                    for(String s: extractedText){
                        testExtract[0] = s;

                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        textView.setText(testExtract[0]);
        return image;
    }*/
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_2_";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/myCapturedImages");
        if (!myDir.exists())
            myDir.mkdirs();
        final File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                myDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
