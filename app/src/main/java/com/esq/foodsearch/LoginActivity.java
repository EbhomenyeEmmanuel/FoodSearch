package com.esq.foodsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button button;
    private String TAG = "LoginActivity.this";
    int tokenExpirationTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up_screen);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button) {
            loginToSearchFood();
            Log.i("OnClick: ", "loginToSearchFood() is called");
        }
    }

    //Login using values of string gotten from editText
    public void loginToSearchFood() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait... Token is generated"); // set message
        progressDialog.show(); // show progress dialog
        String username = "15763421aecc458294ee304cd7cab984";//Client ID gotten from developers account
        String password = "5ed557eaca4b4307a7cd21dc506a088f";//Client Secret gotten from developers account
        //String[] clientCredentials= {username, password};
        // Create a user model
        final LoginModel loginModel = new LoginModel(username, password);
        //Call the retrofit Interface built.
        //The parameters are according to the website {grantType, ClientId, ClientSecret, scope}
        Api.getClient().login("client_credentials",loginModel.getUser(), loginModel.getPassword(),"basic").enqueue(new Callback<UserTokenModel>() {
            @Override
            public void onResponse(Call<UserTokenModel> call, Response<UserTokenModel> response) {
                progressDialog.dismiss(); //dismiss progress dialog
                if (response.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    String token = response.body().getToken();
                    String clientId = loginModel.getUser();
                    tokenExpirationTime = response.body().getTokenExpirationSeconds();
                    //Log.i(TAG, "onResponse: If statement. \n Token expires in " + tokenExpirationTime + " secs" );
                    //Clients should store the access token expires_in value and request a new Access Token before the token is expired
                    intent.putExtra(MainActivity.TOKEN, token);
                    intent.putExtra("tokenTimeLeft",tokenExpirationTime);
                    startActivity(intent);
                }else{
                    Log.d("onResponse: Else statem", String.valueOf(response.errorBody()));
                    Toast.makeText(LoginActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserTokenModel> call, Throwable t) {
                progressDialog.dismiss(); //dismiss progress dialog
                Toast.makeText(LoginActivity.this, "Error :( -> " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i( TAG, "onFailure(): " + t.getLocalizedMessage());
            }
        });
    }


}
