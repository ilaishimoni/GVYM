package com.example.gavyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class viewComapnies extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    Intent gi;

    ListView lvrecords;
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter adp;

    ArrayList<String> id_check;

    Spinner flt;
    String [] f = {"", "companyName", "primaryphone", "secondaryPhone"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comapnies);

        hlp = new HelperDB(this);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        hlp=new HelperDB(this);
        db=hlp.getReadableDatabase();
        db.close();

        flt = (Spinner) findViewById(R.id.srt);
        ArrayAdapter<String> fff = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, f);
        flt.setAdapter(fff);
        flt.setOnItemSelectedListener(this);


    }


    public void onResume() {
        super.onResume();
        hlp = new HelperDB(this);
        lvrecords = (ListView) findViewById(R.id.lvrecords);

        hlp = new HelperDB(this);
        id_check = new ArrayList<>();

        lvrecords = (ListView) findViewById(R.id.lvrecords);
        lvrecords.setOnItemClickListener(this);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        db=hlp.getWritableDatabase();
        crsr = db.query(companiesTable.COMPANIES_TABLE, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(companiesTable.TAX_SERIAL);
        int col2 = crsr.getColumnIndex(companiesTable.COMPANY_NAME);
        int col3 = crsr.getColumnIndex(companiesTable.PRIMARY_PHONE);
        int col4 = crsr.getColumnIndex(companiesTable.SECONDARY_PHONE);
        int col5 = crsr.getColumnIndex(companiesTable.KEY_ID);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String tax = crsr.getString(col1);
            String company = crsr.getString(col2);
            String p_phone = crsr.getString(col3);
            String s_phone = crsr.getString(col4);
            String kid =  crsr.getString(col5);
            String tmp = "" + tax + ", " + company + ", " + p_phone + ", " + s_phone;
            id_check.add(kid);
            tbl.add(tmp);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
        adp.notifyDataSetChanged();
        lvrecords.setAdapter(adp);

    }



    /**
     * inflates and creates OptionsMenu
     * @return	method creates optionsMenu and xml file
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.optionsmenu, menu);

        return true;
    }
    /**
     * gets info from user's choice and sends him to the right activity
     */

    public boolean onOptionsItemSelected(MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("worker")){
            gi = new Intent(this, workerss.class);
            startActivity(gi);
        }
        if(st.equals("company")){
            gi = new Intent(this, company.class);
            startActivity(gi);

        }
        if(st.equals("credits")){
            gi = new Intent(this, credits.class);
            startActivity(gi);
        }
        if(st.equals("Go Back")){
            finish();
        }
        if(st.equals("Home screen")){
            gi = new Intent(this, MainActivity.class);
            startActivity(gi);
        }



        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        gi = new Intent(this, edit_companies.class);
        String rlv = id_check.get(position);
        gi.putExtra("key_id", rlv);
        startActivity(gi);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        try{

            //company name
            if (position == 1){
                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = companiesTable.COMPANY_NAME;
                String[] selectionArgs = {"nvidia"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db.query(companiesTable.COMPANIES_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(companiesTable.TAX_SERIAL);
                int col2 = crsr.getColumnIndex(companiesTable.PRIMARY_PHONE);
                int col3 = crsr.getColumnIndex(companiesTable.SECONDARY_PHONE);
                int col4 = crsr.getColumnIndex(companiesTable.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(companiesTable.KEY_ID);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    String tax = crsr.getString(col1);
                    String primary = crsr.getString(col2);
                    String secondary = crsr.getString(col3);
                    String company = crsr.getString(col4);
                     String kid = crsr.getString(col5);



                    String tmp = "" + tax + ", " + primary + ", " + secondary + ", " + company + ", " + kid;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }
            //primary name
            if (position == 2){

                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = companiesTable.PRIMARY_PHONE;
                String[] selectionArgs = {"david"};
                String groupBy = null;
                String having = null;
                String orderBy = null;
                String limit = null;

                db.query(companiesTable.COMPANIES_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(companiesTable.TAX_SERIAL);
                int col2 = crsr.getColumnIndex(companiesTable.PRIMARY_PHONE);
                int col3 = crsr.getColumnIndex(companiesTable.SECONDARY_PHONE);
                int col4 = crsr.getColumnIndex(companiesTable.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(companiesTable.KEY_ID);



                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    String tax = crsr.getString(col1);
                    String primary = crsr.getString(col2);
                    String secondary = crsr.getString(col3);
                    String company = crsr.getString(col4);
                    String kid = crsr.getString(col5);



                    String tmp = "" + tax + ", " + primary + ", " + secondary + ", " + company + ", " + kid;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }
            //secondary phone
            if (position == 3){
                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = companiesTable.SECONDARY_PHONE;
                String limit = null;

                db.query(mealsTable.MEALS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(companiesTable.TAX_SERIAL);
                int col2 = crsr.getColumnIndex(companiesTable.PRIMARY_PHONE);
                int col3 = crsr.getColumnIndex(companiesTable.SECONDARY_PHONE);
                int col4 = crsr.getColumnIndex(companiesTable.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(companiesTable.KEY_ID);

                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    String tax = crsr.getString(col1);
                    String primary = crsr.getString(col2);
                    String secondary = crsr.getString(col3);
                    String company = crsr.getString(col4);
                    String kid = crsr.getString(col5);



                    String tmp = "" + tax + ", " + primary + ", " + secondary + ", " + company + ", " + kid;
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }

        }
        catch (Exception ex){

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void goback(View view) {
        finish();
    }
}