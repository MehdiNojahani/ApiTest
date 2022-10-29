package com.example.apitest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class InfoCoinActivity extends AppCompatActivity {

    public static final String URL = "https://api.wazirx.com/sapi/v1/tickers/24hr";
    private Coin coin;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_coin);

        coin = new Coin();

        Toolbar toolbarInfo = findViewById(R.id.tb_toolbarInfoCoin_InfoCoin);
        setSupportActionBar(toolbarInfo);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_back_24);

        TextView tvHighPrice = findViewById(R.id.tv_highPrice_InfoCoid);
        TextView tvLowPrice = findViewById(R.id.tv_LowPrice_InfoCoin);
        TextView tvLastPrice = findViewById(R.id.tv_LastPrice_InfoCoin);
        TextView tvSymbol = findViewById(R.id.tv_symbol_showItemPrice);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }
}