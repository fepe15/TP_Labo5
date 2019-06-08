package com.example.tp_labo5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;

import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class MyListener implements DialogInterface.OnClickListener {

    Handler myHandler;
    View myView;

    public MyListener(Handler handler, View view){
        this.myHandler = handler;
        this.myView = view;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        List<String> paginas = new ArrayList<>();
        CheckBox cbIprofesional = (CheckBox) myView.findViewById(R.id.cbxIProfesional);
        CheckBox cbClarin = (CheckBox) myView.findViewById(R.id.cbxIProfesional);
        CheckBox cbPerfil = (CheckBox) myView.findViewById(R.id.cbxIProfesional);
        if (cbIprofesional.isChecked())
            paginas.add("https://www.iprofesional.com/rss/tecnologia");
        if (cbClarin.isChecked())
            paginas.add("https://www.clarin.com/rss/tecnologia/");
        if (cbPerfil.isChecked())
            paginas.add("https://www.perfil.com/rss/tecnologia");

        for (String pagina: paginas) {
            MyThread myThread = new MyThread(this.myHandler, pagina, "XML", 0);
            myThread.start();
        }
    }
}
