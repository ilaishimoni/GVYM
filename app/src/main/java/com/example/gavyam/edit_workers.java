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

public class edit_workers extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    Intent gi;

    String FirstName;
    String FinalName;
    String PhoneNumber;
    String CompanyName;
    String PersonalId;
    String CardId;
    int Active;

    EditText firstn;
    EditText finaln;
    EditText phonen;
    EditText companyn;
    EditText personalid;
    EditText cardid;
    Switch sw;
    boolean active_bool;





    /**
     * gets data from database and presents it to the user
     * occurs at app creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workers);

        hlp = new HelperDB(this);

        Intent si = getIntent();
        String id = si.getStringExtra("key_id");



        String slc = workersTable.KEY_ID + "=?";
        String[] selectionArgs = {id};
        db = hlp.getReadableDatabase();
        crsr = db.query(workersTable.WORKERS_TABLE, null, slc, selectionArgs, null, null, null);
        int col1 = crsr.getColumnIndex(workersTable.FIRST_NAME);
        int col2 = crsr.getColumnIndex(workersTable.FINAL_NAME);
        int col3 = crsr.getColumnIndex(workersTable.PHONE_NUMBER);
        int col5 = crsr.getColumnIndex(workersTable.COMPANY_NAME);
        int col6 = crsr.getColumnIndex(workersTable.PERSONAL_ID);
        int col7 = crsr.getColumnIndex(workersTable.CARD_ID);
        int col8 = crsr.getColumnIndex(workersTable.IS_ACTIVE);

        crsr.moveToFirst();

        FirstName = crsr.getString(col1);
        FinalName = crsr.getString(col2);
        PhoneNumber = crsr.getString(col3);
        CompanyName = crsr.getString(col5);
        PersonalId = crsr.getString(col6);
        CardId = crsr.getString(col7);
        Active = crsr.getInt(col8);



        firstn = (EditText) findViewById(R.id.first_m);
        firstn.setText(FirstName);
        finaln = (EditText) findViewById(R.id.primarym);
        finaln.setText(FinalName);
        phonen = (EditText) findViewById(R.id.addon);
        phonen.setText(PhoneNumber);
        companyn = (EditText) findViewById(R.id.dessert);
        companyn.setText(CompanyName);
        personalid = (EditText) findViewById(R.id.drink);
        personalid.setText(PersonalId);
        cardid = (EditText) findViewById(R.id.Card_id);
        cardid.setText(CardId);
        sw = (Switch) findViewById(R.id.sw);
        if (Active == 1){
            active_bool = true;
        }
        else{
            active_bool = false;

        }
        sw.setChecked(active_bool);
    }

    public void update(View view) {
        String card_check = cardid.getText().toString();
        String first_check = firstn.getText().toString();
        String finalName_check = finaln.getText().toString();
        String company_check = companyn.getText().toString();
        String id_check = personalid.getText().toString();
        String phone_check = phonen.getText().toString();
        boolean checked = sw.isChecked();
        int checked_data;
        if(checked == true){
            checked_data = 1;
        }
        else{
            checked_data = 0;
        }



        if (id_check.length() != 9 ||card_check.isEmpty() || first_check.isEmpty() || finalName_check.isEmpty() || company_check.isEmpty() || id_check.isEmpty() || phone_check.isEmpty()) {
            Toast.makeText(this, "Some data is incorrect, please try again", Toast.LENGTH_SHORT).show();
        } else {
            boolean firstHalf = false;
            boolean secHalf = false;
            for (int i = 0; i < 5; ++i) {//Check first half
                if ((id_check.charAt(i) > 47 && id_check.charAt(i) < 58)) {//Checks ascii vals to see if valid ID
                    firstHalf = true;
                }
            }

            for (int i = 5; i < personalid.length(); ++i) {//Check second half
                if ((id_check.charAt(i) > 47 && id_check.charAt(i) < 58)) {//Checks ascii vals to see if valid ID
                    secHalf = true;
                }
            }
            if (firstHalf == true && secHalf == true && personalid.length() == 9) {
                ContentValues cv = new ContentValues();

                cv.put(workersTable.FIRST_NAME, first_check);
                cv.put(workersTable.FINAL_NAME, finalName_check);
                cv.put(workersTable.PHONE_NUMBER, phone_check);
                cv.put(workersTable.COMPANY_NAME, company_check);
                cv.put(workersTable.PERSONAL_ID, id_check);
                cv.put(workersTable.CARD_ID, card_check);
                cv.put(workersTable.IS_ACTIVE, checked_data);

                db = hlp.getWritableDatabase();
                db.update(workersTable.WORKERS_TABLE, cv, workersTable.FIRST_NAME+"=?", new String[]{FirstName});
                db.update(workersTable.WORKERS_TABLE, cv, workersTable.FINAL_NAME+"=?", new String[]{FinalName});
                db.update(workersTable.WORKERS_TABLE, cv, workersTable.COMPANY_NAME+"=?", new String[]{CompanyName});
                db.update(workersTable.WORKERS_TABLE, cv, workersTable.PERSONAL_ID+"=?", new String[]{PersonalId});
                db.update(workersTable.WORKERS_TABLE, cv, workersTable.CARD_ID+"=?", new String[]{CardId});


                db.close();

                Toast.makeText(this, "Data updated successfully ", Toast.LENGTH_SHORT).show();
                finish();


            }


            }
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
}




