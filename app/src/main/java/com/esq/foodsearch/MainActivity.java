package com.esq.foodsearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String TOKEN = "token";
    String TAG = "MainActivity.this";
    int tokenExpirationChecker;
    int tokenTimeLeft; //From intent
    List<SearchListResponseModel> searchListResponseData ;//List of results found
    ProgressDialog progressDialog;

    @Override
    protected void onStart() {
        super.onStart();
        //handlerMethod();
    }

    //Check if token time has expired
    public void handlerMethod(){
        int SPLASH_TIME = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tokenExpirationChecker == tokenTimeLeft) {
                    Toast.makeText(MainActivity.this, "Token Expired... Requesting a new one", Toast.LENGTH_SHORT).show();
                    new LoginActivity().loginToSearchFood();
                }else {
                    ++tokenExpirationChecker;
                }
                Log.i("MainActivity", "Handler method is incrementing value");
            }
        }, SPLASH_TIME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressDialog.dismiss(); //dismiss progress dialog
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getUserListData(); // call a method in which we have implement our GET type web API
    }

    //Use token gotten from intent to set upRecyclerView
    private void getUserListData() {
        progressDialog = new ProgressDialog(MainActivity.this);// display a progress dialog
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Setting up..."); // set message
        progressDialog.show(); // show progress dialog
        Intent intent = getIntent();
        String token = intent.getStringExtra(TOKEN);
        tokenTimeLeft = intent.getIntExtra("tokenTimeLeft", 0);
        Toast.makeText(this, "Token time left is " + tokenTimeLeft + " \n Token is " + token , Toast.LENGTH_LONG).show();
        Log.d(TAG, " \n Token is " + token);
        String searchKey = "toast";//Food to search for

        Api.getClient().getResultsList(token, "json", searchKey).enqueue(new Callback<List<SearchListResponseModel>>() {
            @Override
            public void onResponse(Call<List<SearchListResponseModel>> call, Response<List<SearchListResponseModel>> response) {
                progressDialog.dismiss(); //dismiss progress dialog
                if (response.isSuccessful()) {
                    Log.d("responseGET ", response.body().get(0).getName());
                    searchListResponseData = response.body();//Add all the results in a list
                    setDataInRecyclerView();
                }else{
                    Log.d("Response errorBody", String.valueOf(response.errorBody()));
                    Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SearchListResponseModel>> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                //progressDialog.dismiss(); //dismiss progress dialog
            }
        });
    }

    private void setDataInRecyclerView(){
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UserAdapter usersAdapter = new UserAdapter(MainActivity.this, searchListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }

}
