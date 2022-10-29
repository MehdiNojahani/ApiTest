package com.example.apitest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String URL = "https://api.wazirx.com/sapi/v1/tickers/24hr";
    private static final int REQUEST_CODE = 1024;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    Gson gson = new Gson();
    ProgressBar progressBar;
    private CoinAdapter coinAdapter;
    private Coin coin;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.tb_toolbarMain_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawerLayout=findViewById(R.id.drawerLayout_Id);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(MainActivity.this,
               drawerLayout,  toolbar, R.string.open_navigation, R.string.close_navigation);

        drawerLayout.addDrawerListener(drawerToggle);

        coin=new Coin();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                }
        );

        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<Coin> coinList = gson.fromJson(response, new TypeToken<List<Coin>>(){}.getType());

                        progressBar = findViewById(R.id.pb_progressLoadingItem_main);
                        progressBar.setVisibility(View.GONE);

                        Log.i(TAG, "onResponse: " + coinList.size());

                        coinAdapter = new CoinAdapter(MainActivity.this, coinList, new CoinAdapter.ItemClickable() {
                            @Override
                            public void onItemClicked(int position) {
                                Intent intent=new Intent(MainActivity.this, InfoCoinActivity.class);
                                intent.putExtra("coin", coin);
                                activityResultLauncher.launch(intent);
                            }
                        });

                        recyclerView = findViewById(R.id.rv_recyclerViewItem_main);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                        recyclerView.setAdapter(coinAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.toString());
                    }
                });

        request.setTag(TAG);
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 3, 3));


        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll(TAG);
    }

}