package com.mirego.csmapapplication.model;
import android.app.DownloadManager;

import com.google.gson.JsonParser;

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public final class Piece {

    public final String name;

    public final String component;

    public final String notes;

    public final String type;

    public final float lat;

    public final float lon;

    public final String adress;


    public Piece(String nom,String comp,String no,String typ,Float la, Float lo,String adr) {


        this.name= nom;
        this.component = comp;
        this.notes = no;
        this.type=typ;
        this.lat=la;
        this.lon=lo;
        this.adress=adr;
    }
   // public ArrayList<Piece> chargerDonné () throws JSONException {







}