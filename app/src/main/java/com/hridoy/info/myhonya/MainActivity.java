package com.hridoy.info.myhonya;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.util.List;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Banner Ad
    private AdView mAdView;
    //Interstitial Ad
    private InterstitialAd mInterstitialAd;
    //Rewarded Ad
    private RewardedVideoAd mRewardedVideoAd;

    //Button
    private Button myButton;
    private Button rewardedAbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();




        if(!amIConnected()){

            buildDialog(this).show();
        }
        else {
            setContentView(R.layout.activity_main);
            AppRate.showRateDialogIfMeetsConditions(this);
            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar1);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }



        //Banner Ad Load Request
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //----------------------------------------------------------------------------------------------------


        Button n5button =findViewById(R.id.n5);
        Button share=findViewById(R.id.share);
        Button tutorial=findViewById(R.id.turorial);
        Button info=findViewById(R.id.information);


        //InterstitialAd Load Request
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-1352722733825128~1363994238");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1352722733825128/9789279166");




        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener(){

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        super.onPermissionRationaleShouldBeShown(permissions, token);
                    }
                }).check();




        n5button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {

                    startActivity(new Intent(MainActivity.this,N5Activity.class));
                    mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }

            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody="Honya Android Application. \n Learning Japanese App. N5 to N1 all Book in here! \n " +
                        "Download Link:"+""+"\n Thanks to Honya Apps Publisher";
                String shareSub="Honya APP || N5 to N1 all Book in here!";

                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Share your friend!"));


            }
        });


        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tuto = new Intent(MainActivity.this,TutorialActivity.class);
                startActivity(tuto);

            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(info);
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.hiragana) {


            Intent hiragana = new Intent(MainActivity.this,HiraganaActivity.class);
            startActivity(hiragana);
            return true;
        }
        if (id == R.id.katakana) {


            Intent katakana = new Intent(MainActivity.this,KatakanaActivity.class);
            startActivity(katakana);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_n5) {

            Intent n5 = new Intent(MainActivity.this,N5Activity.class);
            startActivity(n5);

            // Handle the camera action
        } else if (id == R.id.nav_n4) {

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {

                    startActivity(new Intent(MainActivity.this,N4Activity.class));
                    // Load the next interstitial.
                    mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }

            });

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {

                startActivity(new Intent(MainActivity.this,N4Activity.class));
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }


        }



        else if (id == R.id.nav_n3) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {

                startActivity(new Intent(MainActivity.this,N3Activity.class));
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }

        }




        else if (id == R.id.nav_n2) {
            Intent n2 = new Intent(MainActivity.this,N2Activity.class);
            startActivity(n2);

        }
        else if (id == R.id.nav_n1) {

            Intent n1 = new Intent(MainActivity.this,N1Activity.class);
            startActivity(n1);

        }
        else if (id == R.id.nav_share) {

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody="Honya Android Application. \n Learning Japanese App. N5 to N1 all Book in here! \n " +
                    "Download Link:"+""+"\n Thanks to Honya Apps Publisher";
            String shareSub="Honya APP || N5 to N1 all Book in here!";

            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(myIntent,"Share your friend!"));

        } else if (id == R.id.nav_info) {


            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {

                    startActivity(new Intent(MainActivity.this,N4Activity.class));
                    // Load the next interstitial.
                    mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                }

            });

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {

                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private boolean amIConnected(){

        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo =connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }







}
