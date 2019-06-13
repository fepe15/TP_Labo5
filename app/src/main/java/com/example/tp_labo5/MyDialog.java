package com.example.tp_labo5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

public class MyDialog extends DialogFragment {

    public static Handler myHandler;
    public static boolean checkIprofesional;
    public static boolean checkClarin;
    public static boolean checkPerfil;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View viewAlert = li.inflate(R.layout.dialog,null);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setMessage("Seleccione sus paginas deseadas");
        b.setTitle("Paginas de noticias");
        CheckBox cbIpro = viewAlert.findViewById(R.id.cbxIProfesional);
        cbIpro.setChecked(checkIprofesional);
        CheckBox cbClarin = viewAlert.findViewById(R.id.cbxClarin);
        cbClarin.setChecked(checkClarin);
        CheckBox cbPerfil = viewAlert.findViewById(R.id.cbxPerfil);
        cbPerfil.setChecked(checkPerfil);
        MyListener listener = new MyListener(myHandler,viewAlert);
        b.setView(viewAlert);
        b.setPositiveButton("Aplicar", listener);
        return b.create();
    }

    public static void chequeosPaginas(boolean iprofesional, boolean clarin, boolean perfil)
    {
        checkIprofesional = iprofesional;
        checkClarin = clarin;
        checkPerfil = perfil;
    }
}
