package com.newsit;

import android.os.AsyncTask;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class notification extends Activity {
	
    ListView LIST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        LIST = findViewById(R.id.notificationlist);
        GETDATA("http://markoliverwebsite.000webhostapp.com/notification.php");
    }


	//create class to execute getdata.php
    private void GETDATA(final String urlWebService) {

        class GETCLASS extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                   try {
					//results of getdata.php
					//public void->
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder N = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String DATA;
                    while ((DATA = bufferedReader.readLine()) != null) {
                        N.append(DATA).append("\n");
                    }
                   return N.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
		//execute the GETCLASS
        GETCLASS getjson = new GETCLASS();
        getjson.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
		                                  //JSONArray variable name
        String[] NOTIFICATION = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
			//get the data on datalist using jsonArray
            NOTIFICATION[i] = obj.getString("datalist");
        }

		//listview
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, NOTIFICATION);
        LIST.setAdapter(arrayAdapter);
    }
	
}

