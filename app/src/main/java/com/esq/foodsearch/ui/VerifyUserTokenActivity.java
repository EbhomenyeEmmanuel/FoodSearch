package com.esq.foodsearch.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.esq.foodsearch.R;
import com.esq.foodsearch.utils.Constants;
import com.esq.foodsearch.utils.Status;
import com.esq.foodsearch.utils.UtilsKt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.paperdb.Paper;

public class VerifyUserTokenActivity extends AppCompatActivity {

    private String TAG = VerifyUserTokenActivity.class.getSimpleName();
    int tokenExpirationTime;
    private VerifyUserTokenActivityViewModel mViewModel;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up_screen);
        mViewModel = new ViewModelProvider(this).get(VerifyUserTokenActivityViewModel.class);

      /*
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(new Intent(VerifyUserTokenActivity.this, MainActivity.class));
           }
       },3000);
       */

        verifyToken();

    }

    private void verifyToken() {
        mViewModel.getTokenResponse().observe(this, userTokenModelResource -> {
            Status status = userTokenModelResource.getStatus();
            if (status == Status.LOADING) {//  progressDialog.setMessage("Please Wait... Token is generated"); // set message
                UtilsKt.longToast(VerifyUserTokenActivity.this, "Loading");
                Log.d(TAG, "verifyToken(): Loading");
            } else if (status == Status.SUCCESS) {//Store clicked item in paper db
                Paper.init(VerifyUserTokenActivity.this);
                Paper.book().delete(Constants.CREDENTIALS_RESPONSE);
                Paper.book().write(Constants.CREDENTIALS_RESPONSE, userTokenModelResource.getData());
                UtilsKt.longToast(VerifyUserTokenActivity.this, "Success");
                Log.d(TAG, "verifyToken(): Success");
                startActivity(new Intent(VerifyUserTokenActivity.this, MainActivity.class));
                finish();
            } else if (status == Status.ERROR) {
                Log.d(TAG, "verifyToken(): Error");
                dialog = new SweetAlertDialog(VerifyUserTokenActivity.this, SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Oops...");
                dialog.setContentText(userTokenModelResource.getMessage());
                dialog.setCancelable(true);
                dialog.create();
                dialog.show();
                UtilsKt.longToast(VerifyUserTokenActivity.this, "Error");
            }
        });

    }

    //Create an exit dialog
    @Override
    public void onBackPressed() {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(VerifyUserTokenActivity.this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.create();
        builder.show();

    }
}
