package com.example.a2b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2b.Network.ConnectURI;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            Toast.makeText(view.getContext() , "Connecting..", Toast.LENGTH_LONG)
                    .show();
            new getResponse().execute();
        });
    }
    public class getResponse extends AsyncTask<String,String,String> {
        protected String doInBackground(String...string){
            URL address = ConnectURI.buildURL(
                    "https://harber.mimoapps.xyz/api/getaccess.php");
            try{
                String ItemListResponse = ConnectURI.getResponseFromHttpUrl(address);
                System.out.println(ItemListResponse);
                return ItemListResponse;
            } catch (IOException e) {
                e. printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(String s){
            if(s.length()!=0){
                System.out.println(s);
            }else{
                System.out.println("nothing");
            }
            EditText txtResponse = (EditText) findViewById(R.id.txtResponse);
            txtResponse.setText(s);
        }

    }
}