package com.hridoy.info.myhonya;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class N5Activity extends AppCompatActivity {

    private  PDFView pdfView;
    //Banner Ad
    private AdView mAdView;
    //Interstitial Ad
    private InterstitialAd mInterstitialAd;
    //Rewarded Ad
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("N5 Grammer and Vocabulary");
        setContentView(R.layout.activity_n5);

        //Banner Ad Load Request
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //----------------------------------------------------------------------------------------------------

        pdfView=(PDFView) findViewById(R.id.pdf_viewer);



        pdfView.fromAsset("n5.pdf")
                .password(null)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {


                    }
                })

                .onDrawAll(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {

                        Toast.makeText(getApplicationContext(),"Error.."+page,Toast.LENGTH_LONG).show();


                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .onTap(new OnTapListener() {
                    @Override
                    public boolean onTap(MotionEvent e) {
                        return true;
                    }
                }).onRender(new OnRenderListener() {
            @Override
            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                pdfView.fitToWidth();
            }
        })
                .enableAnnotationRendering(true)
                .invalidPageColor(Color.WHITE)
                .load();

        Toast.makeText(getApplicationContext(),"Please Swipe Right To Left..",Toast.LENGTH_LONG).show();

    }
}
