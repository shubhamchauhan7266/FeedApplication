package com.feedapplication;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity {

    private Dialog customProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * Shows a simple native progress dialog<br/>
     * Subclass can override below two methods for custom dialogs- <br/>
     * 1. showProgressDialog <br/>
     * 2. removeProgressDialog
     */
    public void showProgressDialog() {
        if (isFinishing()) {
            return;
        }
        if (customProgressDialog == null) {
            customProgressDialog = new Dialog(this);
            customProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            customProgressDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            customProgressDialog.setContentView(R.layout.custom_progress_bar);
            customProgressDialog.setCancelable(false);
        }

        if (!customProgressDialog.isShowing()) {
            customProgressDialog.show();
        }
    }

    /**
     * Removes the simple native progress dialog shown via showProgressDialog <br/>
     * Subclass can override below two methods for custom dialogs- <br/>
     * 1. showProgressDialog <br/>
     * 2. removeProgressDialog
     */
    public void removeProgressDialog() {
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }
    }

}
