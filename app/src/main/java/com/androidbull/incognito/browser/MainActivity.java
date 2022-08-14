package com.androidbull.incognito.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;
import com.adcolony.sdk.AdColonyCustomMessage;
import com.adcolony.sdk.AdColonyCustomMessageListener;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonySignalsListener;
import com.adcolony.sdk.AdColonyZone;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean configure = AdColony.configure(this, "app5ca9a14d8f5e464ea1");
        Log.i(TAG, "onCreate: configure: " + configure);

        AdColony.collectSignals(new AdColonySignalsListener() {
            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "onSuccess: " + s);
            }
        });

        AdColonyAdViewListener listener = new AdColonyAdViewListener() {
            @Override
            public void onRequestFilled(AdColonyAdView ad) {
                Log.i(TAG, "onRequestFilled: banner");
                /* Add this ad object to whatever layout you have set up for this placement */
                ((RelativeLayout) findViewById(R.id.rl_ad_container)).addView(ad);
            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {
                Log.e(TAG, "onRequestNotFilled: banner");
                super.onRequestNotFilled(zone);
            }
        };

        AdColony.requestAdView("vz0645ff734db444d69d", listener, AdColonyAdSize.BANNER);
        AdColony.requestInterstitial("vz48fe038be8d34f659b", new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
                Log.i(TAG, "onRequestFilled: interstitial ");
            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {
                Log.e(TAG, "onRequestNotFilled: interstitial");
                super.onRequestNotFilled(zone);
            }
        });

    }
}