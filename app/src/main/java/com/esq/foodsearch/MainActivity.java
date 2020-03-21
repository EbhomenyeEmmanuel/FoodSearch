package com.esq.foodsearch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static final String TOKEN = "token";
    String TAG = "MainActivity.this";
    int tokenTimeLeft; //From intent
    ArrayList searchListResponseData ;//List of results found
    ProgressDialog progressDialog;
    String searchKey;//String Value of food to search for

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        popUpDialog();
    }

    private void popUpDialog() {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Search Key")
                .setMessage("Add food to search for :)")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        searchKey = String.valueOf(taskEditText.getText()); //Make the value of the editText equal to the searchKey
                        Log.d(TAG, "Value of editText is " + searchKey);
                        if (searchKey != null) {
                           getUserListData();// call a method in which we have implement our GET type web API
                        }else{
                            Toast.makeText(MainActivity.this, "Search Key is empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create();
        dialog.show();
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
        Log.d(TAG, " \n Token is " + token);
        Api.getClient().getResultsList("Bearer " +token,"foods.search", searchKey, "json")
                .enqueue(new Callback<FoodModelClass>(){
            @Override
            public void onResponse(Call<FoodModelClass> call, Response<FoodModelClass> response) {
                progressDialog.dismiss(); //dismiss progress dialog
                if (response.isSuccessful()) {
                   Log.d(TAG,"Login was successful");
                    if (response.body() == null || response.body().getFoods() == null || response.body().getFoods().getFood() == null) {
                        Log.d(TAG,"Response Body is null");
                    }else{
                        Log.d(TAG,"Response Body is not null");
                        searchListResponseData = response.body().getFoods().getFood();//Add all the results in a list
                        if (searchListResponseData == null) {
                            Log.d(TAG,"Successful Response: searchListResponseData is null" );
                        }else{
                            Log.d(TAG,"Successful Response: searchListResponseData is not null" );
                        }
                        setDataInRecyclerView();
                    }

                }else{
                    Log.d(TAG,"OnResponse () -> Error: "+ response.code());
                    try {
                        Log.d(TAG,"Error: "+ response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "Error: "+ response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FoodModelClass> call, Throwable t) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDataInRecyclerView(){
        Log.d(TAG,"setDataInRecyclerView(): Data in recyclerView is set up");
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UserAdapter usersAdapter = new UserAdapter(MainActivity.this, searchListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }

}
