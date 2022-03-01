package com.example.gavyam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;




public class HelperDB extends SQLiteOpenHelper {

    /**
     * defining database perimeters and creating a table for each table
     */

    private static final String DATABASE_NAME = "GavYam.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB( @Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+ workersTable.WORKERS_TABLE;
        strCreate+=" ("+ workersTable.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+ workersTable.CARD_ID+" TEXT,";
        strCreate+=" "+ workersTable.COMPANY_NAME+" TEXT,";
        strCreate+=" "+ workersTable.FIRST_NAME+" TEXT,";
        strCreate+=" "+ workersTable.FINAL_NAME+" TEXT,";
        strCreate+=" "+ workersTable.PERSONAL_ID+" TEXT,";
        strCreate+=" "+ workersTable.PHONE_NUMBER+" TEXT,";
        strCreate+=" "+ workersTable.IS_ACTIVE+" INTEGER";
        
        strCreate+=");";
        db.execSQL(strCreate);


        strCreate="CREATE TABLE "+ companiesTable.COMPANIES_TABLE;
        strCreate+=" ("+ companiesTable.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+ companiesTable.TAX_SERIAL+" TEXT,";
        strCreate+=" "+ companiesTable.COMPANY_NAME+" TEXT,";
        strCreate+=" "+ companiesTable.PRIMARY_PHONE+" TEXT,";
        strCreate+=" "+ companiesTable.SECONDARY_PHONE+" TEXT,";
        strCreate+=" "+ companiesTable.IS_ACTIVE+" INTEGER";



        strCreate+=");";
        db.execSQL(strCreate);


        strCreate="CREATE TABLE "+ mealsTable.MEALS_TABLE;
        strCreate+=" ("+ mealsTable.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+ mealsTable.FIRST_MEAL+" TEXT,";
        strCreate+=" "+ mealsTable.MAIN_MEAL+" TEXT,";
        strCreate+=" "+ mealsTable.ADD_ON+" TEXT,";
        strCreate+=" "+ mealsTable.DESSERT+" TEXT,";
        strCreate+=" "+ mealsTable.DRINK+" TEXT";


        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+ orders.ORDERS_TABLE;
        strCreate+=" ("+ orders.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+ orders.WORKER+" TEXT,";
        strCreate+=" "+ orders.DATE+" TEXT,";
        strCreate+=" "+ orders.DELIVERING_CMP+" TEXT,";
        strCreate+=" "+ orders.MEAL+" TEXT,";
        strCreate+=" "+ orders.HOUR+" TEXT";


        strCreate+=");";
        db.execSQL(strCreate);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        strDelete="DROP TABLE IF EXISTS "+ workersTable.WORKERS_TABLE;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+companiesTable.COMPANIES_TABLE;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+mealsTable.MEALS_TABLE;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+orders.ORDERS_TABLE;
        db.execSQL(strDelete);


        onCreate(db);

    }
}
