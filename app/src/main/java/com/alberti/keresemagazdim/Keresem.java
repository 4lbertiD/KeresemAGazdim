package com.alberti.keresemagazdim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import java.sql.*;

public class Keresem extends AppCompatActivity {

    TextView text,errorText;

    Button show;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keresem);

        text = (TextView) findViewById(R.id.textView);

        errorText = (TextView) findViewById(R.id.errorText);

        new Async().execute();

       }



    class Async extends AsyncTask<Void, Void, Void> {



        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {

            try

            {

                Class.forName("com.mysql.jdbc.Driver");

                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/elveszett", "david", "david");

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM elveszett");

                while(resultSet.next()) {

                    records += resultSet.getString(1) + " " + resultSet.getString(2) + " "+ resultSet.getString(3) + " "+ resultSet.getString(4) + " "
                            + resultSet.getString(5) + " "+ resultSet.getString(6) + " "+ resultSet.getString(7) + " "+ resultSet.getString(8) + " "
                            + resultSet.getString(9) + " "+ resultSet.getString(10) + "\n";

                }

            }

            catch(Exception e)

            {

                error = e.toString();

            }

            return null;

        }
        @Override

        protected void onPostExecute(Void aVoid) {

            text.setText(records);

            if(error != "")

                errorText.setText(error);

            super.onPostExecute(aVoid);

        }
    }

}
