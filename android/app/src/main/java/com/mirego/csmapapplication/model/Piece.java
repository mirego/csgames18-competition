package com.mirego.csmapapplication.model;
import org.json.*;

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
    public ArrayList<Piece> chargerDonné () throws JSONException {
        String value;
        JSONObject reader = new JSONObject(value);
        JSONObject sys  = reader.getJSONObject("sys");
        country = sys.getString("country");

        JSONObject main  = reader.getJSONObject("main");
        temperature = main.getString("temp");

    }
}