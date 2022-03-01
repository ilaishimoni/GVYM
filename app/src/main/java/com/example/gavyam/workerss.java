package com.example.gavyam;

import static com.example.gavyam.workersTable.WORKERS_TABLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class workerss extends AppCompatActivity {
    Intent gi;
    EditText card;
    EditText first;
    EditText finalName;
    EditText company;
    EditText Id;
    EditText phone;

    SQLiteDatabase db;
    HelperDB hlp;


    /**
    occurs during app creation, opens access to the database and connects java and xml elements
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerss);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();


        card = (EditText) findViewById(R.id.First);
        first = (EditText) findViewById(R.id.Primary);
        finalName = (EditText) findViewById(R.id.finalName);
        company = (EditText) findViewById(R.id.company);
        Id = (EditText) findViewById(R.id.id);
        phone = (EditText) findViewById(R.id.phone);


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
     * user is sent to "viewWorkers" activity
     */
    public void viewWorkers(View view) {
        gi = new Intent(this, viewWorkers.class);
        startActivity(gi);
    }

    /**
     *
     * @param "submit button is pressed"
     * method check and validates data and transfer data to database
     */

    public void check_insert(View view) {

        String card_check = card.getText().toString();
        String first_check = first.getText().toString();
        String finalName_check = finalName.getText().toString();
        String company_check = company.getText().toString();
        String id_check = Id.getText().toString();
        String phone_check = phone.getText().toString();


        if (card_check.isEmpty() || first_check.isEmpty() || finalName_check.isEmpty() || company_check.isEmpty() || id_check.isEmpty() || phone_check.isEmpty()) {
            Toast.makeText(this, "Some data is incorrect, please try again", Toast.LENGTH_SHORT).show();
        } else {
            boolean firstHalf = false;
            boolean secHalf = false;
            for (int i = 0; i < 5; ++i) {//Check first half
                if ((id_check.charAt(i) > 47 && id_check.charAt(i) < 58)) {//Checks ascii vals to see if valid ID
                    firstHalf = true;
                }
            }

            for (int i = 5; i < Id.length(); ++i) {//Check second half
                if ((id_check.charAt(i) > 47 && id_check.charAt(i) < 58)) {//Checks ascii vals to see if valid ID
                    secHalf = true;
                }
            }

            //If all values are valid, returns true.
            if (firstHalf == true && secHalf == true && Id.length() == 9) {
                ContentValues cv = new ContentValues();
                cv.put(workersTable.CARD_ID, card_check);
                cv.put(workersTable.FIRST_NAME, first_check);
                cv.put(workersTable.FINAL_NAME, finalName_check);
                cv.put(workersTable.COMPANY_NAME, company_check);
                cv.put(workersTable.PERSONAL_ID, id_check);
                cv.put(workersTable.PHONE_NUMBER, phone_check);
                cv.put(workersTable.IS_ACTIVE, 1);


                db = hlp.getWritableDatabase();
                db.insert(WORKERS_TABLE, null, cv);
                db.close();
                Toast.makeText(this, "Your information has been received", Toast.LENGTH_SHORT).show();
                card.setText("");
                first.setText("");
                finalName.setText("");
                company.setText("");
                Id.setText("");
                phone.setText("");
            }

            else {
                Toast.makeText(this, "Some data is incorrect, please try again", Toast.LENGTH_SHORT).show();

            }

        }
    }

}






        /*int num=0, sum=0;
        if (Id.length() != 9 || card_check.isEmpty() || first_check.isEmpty() || finalName_check.isEmpty() || company_check.isEmpty() || id_check.isEmpty()|| phone_check.isEmpty()){
            Toast.makeText(this, "Some data is incorrect, please try again", Toast.LENGTH_SHORT).show();
        }
        else {
            for (int i = 0; i<9; i++){
                if (i % 2 == 0){
                    num = id_check.charAt(i) * 1;
                }
                else{
                    num = id_check.charAt(i) * 2;
                }
                if(num>9){
                    num = num/10 + num%10;
                }
                sum = sum+num;
                if(sum%10 == 0){
                    //legal id
                    ContentValues cv = new ContentValues();
                    cv.put(workersTable.CARD_ID, card_check);
                    cv.put(workersTable.FIRST_NAME, first_check);
                    cv.put(workersTable.FINAL_NAME, finalName_check);
                    cv.put(workersTable.COMPANY_NAME, company_check);
                    cv.put(workersTable.PERSONAL_ID, id_check);
                    cv.put(workersTable.PHONE_NUMBER, phone_check);

                    db = hlp.getWritableDatabase();
                    db.insert(WORKERS_TABLE, null, cv);
                    db.close();
                    Toast.makeText(this, "Your information has been received", Toast.LENGTH_SHORT).show();
                */






