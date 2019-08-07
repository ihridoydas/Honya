package com.hridoy.info.myhonya;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.util.List;

public class N1Activity extends AppCompatActivity {
    //Banner Ad
    private AdView mAdView;
    //Interstitial Ad
    private InterstitialAd mInterstitialAd;
    //Rewarded Ad
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Nihongo Sou Matome N1");
        setContentView(R.layout.activity_n1);

        //Banner Ad Load Request
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //----------------------------------------------------------------------------------------------------


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


        Button bunpou = (Button) findViewById(R.id.bunpou);
        bunpou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// Click event trigger here


                Intent n1 = new Intent(N1Activity.this, N1ActivityBunpou.class);
                startActivity(n1);
            }
        });




        Button listening = (Button) findViewById(R.id.listening);
        listening.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// Click event trigger here


                Intent n1 = new Intent(N1Activity.this, N1ActivityListening.class);
                startActivity(n1);
            }
        });

        Button kanji = (Button) findViewById(R.id.kanji);
        kanji.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// Click event trigger here


                Intent n2 = new Intent(N1Activity.this, N1ActivityKanji.class);
                startActivity(n2);
            }
        });

    }
    }

