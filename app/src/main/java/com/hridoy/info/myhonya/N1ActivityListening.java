package com.hridoy.info.myhonya;


import android.app.DownloadManager;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;


public class N1ActivityListening extends AppCompatActivity {


    private ProgressBar progressBar;
    private PDFView pdfView;

    //Banner Ad
    private AdView mAdView;
    //Interstitial Ad
    private InterstitialAd mInterstitialAd;
    //Rewarded Ad
    private RewardedVideoAd mRewardedVideoAd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("N1 Bunpou");
        setContentView(R.layout.activity_n1_listening);


        //Banner Ad Load Request
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //----------------------------------------------------------------------------------------------------



        pdfView=(PDFView) findViewById(R.id.pdf_viewer);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        FileLoader.with(this)
                .load("https://drive.google.com/uc?authuser=0&id=1G6e7FOguxhKIvqdqJZ_AsW20iOyqfslK&export=download")
                .fromDirectory("HonyaPDFFile",FileLoader.DIR_EXTERNAL_PUBLIC)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest fileLoadRequest, FileResponse<File> fileResponse) {

                        progressBar.setVisibility(View.GONE);


                        File pdfFile= fileResponse.getBody();

                        pdfView.fromFile(pdfFile)
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

                    @Override
                    public void onError(FileLoadRequest fileLoadRequest, Throwable throwable) {

                        Toast.makeText(N1ActivityListening.this,""+throwable.getMessage(),Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);

                    }
                });

    }
}
