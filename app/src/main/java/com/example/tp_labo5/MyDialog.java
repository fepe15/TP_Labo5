package com.example.tp_labo5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class MyDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        //LayoutInflater li = LayoutInflater.from(this.getContext());
        //View v = li.inflate(R.id.btnDialog, null);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setMessage("Mi Mensaje");
        b.setTitle("M Titulo");
        MyListener listener = new MyListener();
        b.setPositiveButton("OK", listener);
        b.setNeutralButton("Nada",listener);
        b.setNegativeButton("Cancel",listener);
        return b.create();
    }
}
