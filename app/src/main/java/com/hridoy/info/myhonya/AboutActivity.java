package com.hridoy.info.myhonya;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class AboutActivity extends AppCompatActivity {

    //Banner Ad
    private AdView mAdView;
    //Interstitial Ad
    private InterstitialAd mInterstitialAd;
    //Rewarded Ad
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        //Banner Ad Load Request
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //----------------------------------------------------------------------------------------------------




        Button fb = findViewById(R.id.fb);
        Button msg = findViewById(R.id.msg);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent =openFacebook(AboutActivity.this);
                startActivity(facebookIntent);

            }
        });


        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"hridoychandradas141@gmail.com"});


                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Honya App Subject..");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"Type your Text Body...");

                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent,"Choose email Client..."));

            }
        });
    }

    public static Intent openFacebook(Context context){

        try{context.getPackageManager()
                .getPackageInfo("com.facebook.katana",0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/realhridoydas"));
        }catch (Exception e){
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ihridoydas"));

        }

    }
}
