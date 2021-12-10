package com.example.animalbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

public class AnimalDataSource {

    private SQLiteDatabase database;
    private AnimalDBHelper dbHelper;

    public AnimalDataSource(Context context) {
        dbHelper = new AnimalDBHelper(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertAnimal(Animal c) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("species", c.getSpecies());
            values.put("habitat", c.getHabitat());
            values.put("color", c.getColor());
            didSucceed = database.insert("animal", null, values) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public boolean updateAnimal(Animal c) {
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("speciesName", c.getSpecies());
            values.put("habitat", c.getHabitat());
            values.put("color", c.getColor());
            Long id = (long) c.getAnimalID();
            didSucceed = database.update("animal", values, "_id=" + id, null) > 0;
        } catch (Exception e) {

        }
        return didSucceed;
    }

    public ArrayList<Animal> getAnimals() {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        try {
            String query = "Select * from animal";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Animal c = new Animal();
                c.setSpecies(cursor.getString(1));
                c.setHabitat(cursor.getString(2));
                c.setColor(cursor.getString(3));
                animals.add(c);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return animals;
    }
    public Animal getAnimal(int id) {
        Animal c = new Animal();
        try {
            String query = "Select * from contact where _id=" + id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            c.setAnimalID(id);
            c.setSpecies(cursor.getString(1));
            c.setHabitat(cursor.getString(2));
            c.setColor(cursor.getString(3));
            cursor.close();
        } catch (Exception e) {

        }
        return c;
    }
    public ArrayList<String> getSpecies() {
        ArrayList<String> species = new ArrayList<String>();
        try {
            String query = "Select species from animal";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                species.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return species;
    }
    public ArrayList<String> getHabitat() {
        ArrayList<String> habitat = new ArrayList<String>();
        try {
            String query = "Select habitat from animal";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                habitat.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {

        }
        return habitat;
    }

    public int getLastAnimalID() {
        int lastID = -1;
        try {
            String query = "Select MAX(_id) from animal";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        } catch (Exception e) {

        }
        return lastID;
    }

    }


