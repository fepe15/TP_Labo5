package com.example.tp_labo5;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Message;
import android.util.Log;

import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class MyListener implements DialogInterface.OnClickListener {

    Handler myHandler;
    View myView;
    boolean checkIprofesional;
    boolean checkClarin;
    boolean checkPerfil;

    public MyListener(Handler handler, View view){
        this.myHandler = handler;
        this.myView = view;
        this.checkIprofesional = false;
        this.checkClarin = false;
        this.checkPerfil = false;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        List<String> paginas = new ArrayList<>();
        CheckBox cbIprofesional = (CheckBox) myView.findViewById(R.id.cbxIProfesional);
        CheckBox cbClarin = (CheckBox) myView.findViewById(R.id.cbxClarin);
        CheckBox cbPerfil = (CheckBox) myView.findViewById(R.id.cbxPerfil);
        if (cbIprofesional.isChecked()) {
            this.checkIprofesional = true;
            paginas.add("https://www.iprofesional.com/rss/tecnologia");
        }
        if (cbClarin.isChecked()) {
            this.checkClarin = true;
            paginas.add("https://www.clarin.com/rss/tecnologia/");
        }
        if (cbPerfil.isChecked()) {
            this.checkPerfil = true;
            paginas.add("https://www.perfil.com/rss/tecnologia");
        }
        MyDialog.chequeosPaginas(checkIprofesional, checkClarin, checkPerfil);

        if (paginas.size() == 0) {
            Message msg = new Message();
            msg.arg2=1;
            myHandler.sendMessage(msg);
        } else {
            for (String pagina : paginas) {
                MyThread myThread = new MyThread(myHandler, pagina, "XML", 0);
                myThread.start();
            }
        }

        SharedPreferences sharedPreferences = myView.getContext().getSharedPreferences("SharePreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor e =sharedPreferences.edit();
        e.putBoolean("Inicio",true);
        e.putBoolean("checkIprofesional", this.checkIprofesional);
        e.putBoolean("checkClarin", this.checkClarin);
        e.putBoolean("checkPerfil", this.checkPerfil);
        e.commit();
    }
}
