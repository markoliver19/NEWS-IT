package com.newsit;

	import android.app.Activity;
	import android.app.ProgressDialog;
	import android.content.Intent;
	import android.os.AsyncTask;
	import android.os.Bundle;
	import android.text.TextUtils;
	import android.text.method.LinkMovementMethod;
	import android.view.Menu;
	import android.view.MenuInflater;
	import android.view.MenuItem;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.TextView;
	import android.widget.Toast;
	import com.newsit.registration;
	import java.util.HashMap;
	import java.util.zip.Inflater;


	public class Login extends Activity {


		EditText Email, Password;
		Button LogIn ;
		String PasswordHolder, EmailHolder;
		String finalResult ;
		String HttpURL = "http://markoliverwebsite.000webhostapp.com/Login.php";
		Boolean CheckEditText ;
		ProgressDialog PROGRESS;
		HashMap<String,String> hashMap = new HashMap<>();
		HttpParse httpParse = new HttpParse();
		public static final String GETEMAIL = "";

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);

			TextView register = (TextView)findViewById(R.id.lnkRegister);
			register.setMovementMethod(LinkMovementMethod.getInstance());
			register.setOnClickListener(new View.OnClickListener() {
					
				
				@Override
					public void onClick(View view) {
						Intent intent = new Intent(Login.this, registration.class);
						startActivity(intent);

					}
				});
				
				
			Email = (EditText)findViewById(R.id.email);
			Password = (EditText)findViewById(R.id.password);
			LogIn = (Button)findViewById(R.id.login);

			LogIn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						
						//public void->
						EMPTYORNOT();

						//bollen variable
						if(CheckEditText){

							//public void->
							POST(EmailHolder, PasswordHolder);

						}
						else {

							Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

						}

					}
				});


		}
		
		


		public void EMPTYORNOT(){
			
			//String Variable_name
			EmailHolder = Email.getText().toString();
			PasswordHolder = Password.getText().toString();

			if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
			{
				CheckEditText = false;
			}
			else {

				CheckEditText = true ;
			}
		}
		
		
		
	    //Variables of Login.php
		public void POST(final String email, final String password){

			class POSTclass extends AsyncTask<String,Void,String> {

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					
					//ProgressDialog
					PROGRESS = ProgressDialog.show(Login.this,"","Loading Please Wait");
				}

				@Override
				protected void onPostExecute(String httpResponseMsg) {

					super.onPostExecute(httpResponseMsg);

					//ProgressDialog
					PROGRESS.dismiss();

					if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

						finish();

						Intent intent = new Intent(Login.this, DashboardActivity.class);
						//String GETEMAIL
						intent.putExtra(GETEMAIL,email);
						startActivity(intent);

					}
					else{
						
						//HttpParse message
						Toast.makeText(Login.this,httpResponseMsg,Toast.LENGTH_LONG).show();
					}

				}

				@Override
				protected String doInBackground(String... params) {

					//fieldname of Login.php
					hashMap.put("email",params[0]);
					hashMap.put("password",params[1]);
					finalResult = httpParse.postRequest(hashMap, HttpURL);
					
					return finalResult;
				}
			}

			POSTclass data = new POSTclass();
			//execute the Login.php
			data.execute(email,password);
		}

	}

