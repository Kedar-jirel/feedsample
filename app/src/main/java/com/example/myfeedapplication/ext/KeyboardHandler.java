package com.example.myfeedapplication.ext;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardHandler {

    Context mContext;

    public KeyboardHandler(Context mContext) {
        this.mContext = mContext;
    }

    public  void  hideKeyboard() {
        View view = ((Activity)mContext).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}


