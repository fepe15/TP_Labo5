package com.example.tp_labo5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class MyDialog extends DialogFragment {

    public static Handler myHandler;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View viewAlert = li.inflate(R.layout.dialog,null);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setMessage("Selecciones sus paginas deseadas");
        b.setTitle("Paginas de noticias");
        MyListener listener = new MyListener(myHandler,viewAlert);
        b.setView(viewAlert);
        b.setPositiveButton("Aplicar", listener);
        // b.setNeutralButton("Nada",listener);
        // b.setNegativeButton("Cancel",listener);
        return b.create();
    }
}
