package com.example.tp_labo5;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MyXmlParser {

    public static List<Noticia> obtenerNoticias(String xml) {

        List<Noticia> noticias = new ArrayList<>();

        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setInput(new StringReader(xml));
            int event = xmlPullParser.getEventType();
            Noticia noti=null;
            String fuente = null;
            while (event != XmlPullParser.END_DOCUMENT)
            {

                if (event == XmlPullParser.START_TAG)
                {
                    if ("title".equals(xmlPullParser.getName()))
                    {
                        if (fuente == null) {
                            fuente=xmlPullParser.nextText();
                            fuente=fuente.substring(0,fuente.indexOf(" "));

                        }
                    }
                    if ("item".equals(xmlPullParser.getName()))
                    {
                        noti = new Noticia();
                    }
                    if ("title".equals(xmlPullParser.getName()))
                    {
                        if (noti!=null) {
                            noti.setTitulo(xmlPullParser.nextText());
                        }
                    }
                    if ("link".equals(xmlPullParser.getName()))
                    {
                        if (noti!=null) {
                            noti.setLinkNoticia(xmlPullParser.nextText());
                        }
                    }
                    if ("pubDate".equals(xmlPullParser.getName()))
                    {
                        if (noti!=null) {
                            noti.setFechaString(xmlPullParser.nextText());

                        }
                    }
                    if ("description".equals(xmlPullParser.getName()))
                    {
                        if (noti!=null) {
                            noti.setDescripcion(xmlPullParser.nextText());
                        }
                    }
                    if ("enclosure".equals(xmlPullParser.getName()))
                    {
                        if (event == XmlPullParser.START_TAG)
                        {
                            if (noti!=null) {
                             noti.setUrlImagen(xmlPullParser.getAttributeValue(null, "url"));;
                            }
                        }
                    }
                }
                else if (event == XmlPullParser.END_TAG){
                    if ("item".equals(xmlPullParser.getName())){
             //           Log.d("Noticia: ", noti.toString());
                        noti.setFuente(fuente);
                        noticias.add(noti);
                    }
                }
                event = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noticias;
    }
}
