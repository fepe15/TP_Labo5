package com.example.tp_labo5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;

public class MyListener implements DialogInterface.OnClickListener {
    @Override
    public void onClick(DialogInterface dialog, int which) {

        if (Dialog.BUTTON_POSITIVE == which) {
            Log.d("Click:::", "Click en OKEY");
        }
        if (Dialog.BUTTON_NEGATIVE == which) {
            Log.d("Click:::", "Click en CANCELAR");
        }
    }
}
