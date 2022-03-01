package com.example.gavyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class edit_companies extends AppCompatActivity {
    Intent gi;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    String companyName;
    String primary;
    String secondary;
    String tax;

    EditText tax_et;
    EditText companyName_et;
    EditText primary_et;
    EditText secondary_et;
    int Active;
    boolean Active_bool;

    Switch sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_companies);

        hlp = new HelperDB(this);

        Intent si = getIntent();
        String id = si.getStringExtra("key_id");


        String slc = companiesTable.KEY_ID + "=?";
        String[] selectionArgs = {id};
        db = hlp.getReadableDatabase();
        crsr = db.query(companiesTable.COMPANIES_TABLE, null, slc, selectionArgs, null, null, null);
        int col1 = crsr.getColumnIndex(companiesTable.COMPANY_NAME);
        int col2 = crsr.getColumnIndex(companiesTable.PRIMARY_PHONE);
        int col3 = crsr.getColumnIndex(companiesTable.SECONDARY_PHONE);
        int col4 = crsr.getColumnIndex(companiesTable.TAX_SERIAL);
        int col5 = crsr.getColumnIndex(companiesTable.IS_ACTIVE);


        crsr.moveToFirst();

        companyName = crsr.getString(col1);
        primary = crsr.getString(col2);
        secondary = crsr.getString(col3);
        tax = crsr.getString(col4);
        Active = crsr.getInt(col5);

        tax_et = (EditText) findViewById(R.id.first_m);
        tax_et.setText(tax);
        companyName_et = (EditText) findViewById(R.id.drink);
        companyName_et.setText(companyName);
        primary_et = (EditText) findViewById(R.id.addon);
        primary_et.setText(primary);
        secondary_et = (EditText) findViewById(R.id.dessert);
        secondary_et.setText(secondary);
        sw = (Switch) findViewById(R.id.sw);
        if (Active == 1){
            Active_bool = true;
        }
        else{
            Active_bool = false;

        }
        sw.setChecked(Active_bool);
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

    public void update(View view) {

        String tax_check = tax_et.getText().toString();
        String company_check = companyName_et.getText().toString();
        String primary_check = primary_et.getText().toString();
        String secondary_check = secondary_et.getText().toString();
        boolean checked = sw.isChecked();
        int checked_data;
        if(checked == true){
            checked_data = 1;
        }
        else{
            checked_data = 0;
        }

        ContentValues cv = new ContentValues();

        cv.put(companiesTable.TAX_SERIAL, tax_check);
        cv.put(companiesTable.COMPANY_NAME, company_check);
        cv.put(companiesTable.PRIMARY_PHONE, primary_check);
        cv.put(companiesTable.SECONDARY_PHONE, secondary_check);



        db = hlp.getWritableDatabase();
        db.update(companiesTable.COMPANIES_TABLE, cv, companiesTable.TAX_SERIAL+"=?", new String[]{tax});
        db.update(companiesTable.COMPANIES_TABLE, cv, companiesTable.COMPANY_NAME+"=?", new String[]{companyName});
        db.update(companiesTable.COMPANIES_TABLE, cv, companiesTable.PRIMARY_PHONE+"=?", new String[]{primary});
        db.update(companiesTable.COMPANIES_TABLE, cv, companiesTable.SECONDARY_PHONE+"=?", new String[]{secondary});

        db.close();

        Toast.makeText(this, "Data updated successfully ", Toast.LENGTH_SHORT).show();
        finish();
    }
}