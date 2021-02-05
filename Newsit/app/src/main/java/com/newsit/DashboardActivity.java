package com.newsit;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.zip.Inflater;

public class DashboardActivity extends Activity {

    
    TextView Email;
    String EmailHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Email = (TextView)findViewById(R.id.email);
        Intent intent = getIntent();
		
		//string on login.java
        EmailHolder = intent.getStringExtra(Login.GETEMAIL);
        Email.setText(EmailHolder);


        
	//Dashboard Menu

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater Inflater =getMenuInflater();
		Inflater.inflate(R.menu.menu,menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()){
			

			case R.id.developer:

				Intent Intent2=new Intent(this,Developer.class);
				this.startActivity(Intent2);
				return true;



			case R.id.logout:

				Intent Intent3=new Intent(this,Login.class);
				this.startActivity(Intent3);
				Toast.makeText(DashboardActivity.this, "Log out Successfully", Toast.LENGTH_LONG).show();
				return true;


			case R.id.notification:

				Intent Intent4=new Intent(this,notification.class);
				this.startActivity(Intent4);
				return true;
				

		}


		return super.onOptionsItemSelected(item);


	}


}

	
			
			
			
