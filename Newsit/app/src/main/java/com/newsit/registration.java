package com.newsit;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import android.widget.TextView;


public class registration extends Activity {
	
	
	EditText FIRSTNAME, LASTNAME, EMAIL, PASSWORD;
	Button Register;
	TextView Login;
    String FNameHolder, LNameHolder, EmailHolder, PasswordHolder;
    String finalResult ;
    String HttpURL = "http://markoliverwebsite.000webhostapp.com/Registration.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);



        Login = (TextView)findViewById(R.id.lnkLogin);
        Login.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(registration.this, Login.class);
					startActivity(intent);
					}
			});
			
			
			
			
		//assign edittext id
        FIRSTNAME = (EditText)findViewById(R.id.fname);
        LASTNAME = (EditText)findViewById(R.id.lname);
        EMAIL = (EditText)findViewById(R.id.email);
        PASSWORD = (EditText)findViewById(R.id.password);
        Register = (Button)findViewById(R.id.register);
        

        //Register the Data
        Register.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					//EditText Empty or Not
					//public void->
					EMPTYORNOT();
					
					
					 // If EditText is not empty 
					//Bollen variables_name
					if(CheckEditText){
						
						
						
						 //if the tha data will be executed
						//public void->
						POST(FNameHolder,LNameHolder, EmailHolder, PasswordHolder);

					}
					else {
						Toast.makeText(registration.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

					}

				}
			});
			
			
			
			
	    //Textview login intent
        Login.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					Intent intent = new Intent(registration.this,Login.class);
					startActivity(intent);
					//Edittext variable
					FIRSTNAME.setText("");
					LASTNAME.setText("");
					EMAIL.setText("");
					PASSWORD.setText("");

				}
			});
    }
	
	
	

    public void EMPTYORNOT(){
//String variables  //Edittext variable
        FNameHolder = FIRSTNAME.getText().toString();
        LNameHolder = LASTNAME.getText().toString();
        EmailHolder = EMAIL.getText().toString();
        PasswordHolder = PASSWORD.getText().toString();


        if(TextUtils.isEmpty(FNameHolder) || TextUtils.isEmpty(LNameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
			//Bollen variable
            CheckEditText = false;

        }
        else {
			
			//Bollen variable
            CheckEditText = true ;
        }

    }
	
	
	 //Variables of Registration.php
    public void POST(final String F_Name, final String L_Name, final String email, final String password){

		//connection to execute the class
        class POSTclass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(registration.this,"","Loading Please Wait");
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

				Toast.makeText(registration.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }
			
			
			
			
			
            @Override
            protected String doInBackground(String... params) {

				
				//fieldname of Registration.php
				
				//put standard for K value or V value
                hashMap.put("fname",params[0]);

                hashMap.put("lname",params[1]);

                hashMap.put("email",params[2]);

                hashMap.put("password",params[3]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }
		

        POSTclass data= new POSTclass();
	   //execute the Registration.php
       data.execute(F_Name,L_Name,email,password);
    }

}
