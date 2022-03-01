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

public class viewWorkers extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    Intent gi;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    ListView lvrecords;
    ArrayList<String> tbl;
    ArrayList<String> card_int;
    ArrayAdapter adp;

    String card_id;
    String first_name;
    String final_name;
    String company_name;
    String personal_id;
    String phone_number;
    String kid;

    Spinner srt;
    String[] sort = {"","name", "company name", "personal id"};




    /**
     occurs during app creation, requests access to database and asks for data based on user's previous click
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workers);
        hlp=new HelperDB(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        srt = (Spinner) findViewById(R.id.srt);
        ArrayAdapter<String> srttt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sort);
        srt.setAdapter(srttt);
        srt.setOnItemSelectedListener(this);



        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        hlp=new HelperDB(this);
        db=hlp.getReadableDatabase();
        db.close();



        lvrecords = (ListView) findViewById(R.id.lvrecords);
        lvrecords.setOnItemClickListener(this);
        lvrecords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        db=hlp.getWritableDatabase();
        tbl = new ArrayList<>();
        card_int = new ArrayList<>();

        crsr = db.query(workersTable.WORKERS_TABLE, null, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(workersTable.CARD_ID);
        int col2 = crsr.getColumnIndex(workersTable.FIRST_NAME);
        int col3 = crsr.getColumnIndex(workersTable.FINAL_NAME);
        int col4 = crsr.getColumnIndex(workersTable.COMPANY_NAME);
        int col5 = crsr.getColumnIndex(workersTable.PERSONAL_ID);
        int col6 = crsr.getColumnIndex(workersTable.PHONE_NUMBER);
        int col7 = crsr.getColumnIndex(workersTable.KEY_ID);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            card_id = crsr.getString(col1);
            first_name = crsr.getString(col2);
            final_name = crsr.getString(col3);
            company_name = crsr.getString(col4);
            personal_id = crsr.getString(col5);
            phone_number = crsr.getString(col6);
            kid = crsr.getString(col7);



            String tmp = "" + card_id + ", " + first_name + ", " + final_name + ", " + company_name + ", " + personal_id + ", " + phone_number;
            card_int.add(kid);
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
    /**
     * user is sent to "workerss" activity
     */
    public void workerss(View view) {
        gi = new Intent(this, workerss.class);
        startActivity(gi);
    }

    /**
     * occurs at a user's press on one of the tables, and getting key_id for user pressed
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        gi = new Intent(this, edit_workers.class);
        String rlv = card_int.get(position);
        gi.putExtra("key_id", rlv);

        startActivity(gi);


       /*
        AlertDialog.Builder adb;
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(true);
        adb.setTitle("Update worker");

        final EditText et = new EditText(this);
        adb.setView(et);
        final EditText et2 = new EditText(this);
        adb.setView(et2);




        adb.setNeutralButton("make passive", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        adb.setPositiveButton("make active", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog ad = adb.create();
        ad.show();

        */

    }

    public void edit_workers(View view) {
        gi = new Intent(this, edit_workers.class);
        /*
        gi.putExtra("card_id", card_id);
        gi.putExtra("first_name", first_name);
        gi.putExtra("final_name", final_name);
        gi.putExtra("company_name", company_name);
        gi.putExtra("personal_id", personal_id);
        gi.putExtra("phone_number", phone_number);
        */
        startActivity(gi);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long rowId) {
        try{

            //name alphabetic
            if (position == 1){
                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = workersTable.FIRST_NAME;
                String limit = null;

                db.query(workersTable.WORKERS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(workersTable.FIRST_NAME);
                int col2 = crsr.getColumnIndex(workersTable.FINAL_NAME);
                int col3 = crsr.getColumnIndex(workersTable.PHONE_NUMBER);
                int col4 = crsr.getColumnIndex(workersTable.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(workersTable.PERSONAL_ID);
                int col6 = crsr.getColumnIndex(workersTable.KEY_ID);
                int col7 = crsr.getColumnIndex(workersTable.CARD_ID);


                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    first_name = crsr.getString(col1);
                    final_name = crsr.getString(col2);
                    phone_number = crsr.getString(col3);
                    company_name = crsr.getString(col4);
                    personal_id = crsr.getString(col5);
                    kid = crsr.getString(col6);
                    card_id = crsr.getString(col7);




                    String tmp = "" + first_name + ", " + final_name + ", " + phone_number + ", " + company_name + ", " + personal_id + ", " + kid + ", " + card_id;
                    card_int.add(kid);
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }//compnay name
            if (position == 2){



                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = workersTable.COMPANY_NAME;
                String limit = null;

                db.query(workersTable.WORKERS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(workersTable.FIRST_NAME);
                int col2 = crsr.getColumnIndex(workersTable.FINAL_NAME);
                int col3 = crsr.getColumnIndex(workersTable.PHONE_NUMBER);
                int col4 = crsr.getColumnIndex(workersTable.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(workersTable.PERSONAL_ID);
                int col6 = crsr.getColumnIndex(workersTable.KEY_ID);
                int col7 = crsr.getColumnIndex(workersTable.CARD_ID);


                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    first_name = crsr.getString(col1);
                    final_name = crsr.getString(col2);
                    phone_number = crsr.getString(col3);
                    company_name = crsr.getString(col4);
                    personal_id = crsr.getString(col5);
                    kid = crsr.getString(col6);
                    card_id = crsr.getString(col7);




                    String tmp = "" + first_name + ", " + final_name + ", " + phone_number + ", " + company_name + ", " + personal_id + ", " + kid + ", " + card_id;
                    card_int.add(kid);
                    tbl.add(tmp);
                    crsr.moveToNext();
                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tbl);
                adp.notifyDataSetChanged();
                lvrecords.setAdapter(adp);

            }
            // peronal id
            if (position == 3){
                db=hlp.getReadableDatabase();
                String[] columns = null;
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = workersTable.PERSONAL_ID;
                String limit = null;

                db.query(workersTable.WORKERS_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

                int col1 = crsr.getColumnIndex(workersTable.FIRST_NAME);
                int col2 = crsr.getColumnIndex(workersTable.FINAL_NAME);
                int col3 = crsr.getColumnIndex(workersTable.PHONE_NUMBER);
                int col4 = crsr.getColumnIndex(workersTable.COMPANY_NAME);
                int col5 = crsr.getColumnIndex(workersTable.PERSONAL_ID);
                int col6 = crsr.getColumnIndex(workersTable.KEY_ID);
                int col7 = crsr.getColumnIndex(workersTable.CARD_ID);


                crsr.moveToFirst();
                while (!crsr.isAfterLast()) {
                    first_name = crsr.getString(col1);
                    final_name = crsr.getString(col2);
                    phone_number = crsr.getString(col3);
                    company_name = crsr.getString(col4);
                    personal_id = crsr.getString(col5);
                    kid = crsr.getString(col6);
                    card_id = crsr.getString(col7);




                    String tmp = "" + first_name + ", " + final_name + ", " + phone_number + ", " + company_name + ", " + personal_id + ", " + kid + ", " + card_id;
                    card_int.add(kid);
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

    public void back(View view) {
        gi = new Intent(this, meal.class);
        startActivity(gi);
    }
}