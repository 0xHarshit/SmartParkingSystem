package com.example.parkingmanagement;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class AddParking extends AppCompatActivity {

    EditText etCity, etBuilding, etRows, etColumns;

    Button btnConfirmAddParking;

    ProgressBar progressBarAddParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);

        etCity = findViewById(R.id.etCity);
        etBuilding = findViewById(R.id.etBuilding);
        etRows = findViewById(R.id.etRows);
        etColumns = findViewById(R.id.etColumns);

        btnConfirmAddParking = findViewById(R.id.btnConfirmAddParking);
        progressBarAddParking = findViewById(R.id.progressBarAddParking);

        btnConfirmAddParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AddParkingAsync().execute();

            }
        });

    }

    class AddParkingAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBarAddParking.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://humaraserver.mysql.database.azure.com:3306/parking?useSSL=true", "harshit", "Parking@123");
                Statement statementLogin = connection.createStatement();

                Random rand = new Random();

                int parkingId = rand.nextInt(100000)+1;
                String cityName = etCity.getText().toString();
                String buildingName = etBuilding.getText().toString();
                String rows = etRows.getText().toString();
                String columns = etColumns.getText().toString();

                String queryAddParking = String.format("INSERT INTO `parking`.`parkingLot` (`parkingId`, `cityName`, `buildingName`, `numberOfRows`, `numberOfColumns`) VALUES ('%s', '%s', '%s', '%s', '%s');", parkingId, cityName, buildingName, rows, columns);
                statementLogin.executeUpdate(queryAddParking);


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBarAddParking.setVisibility(View.GONE);
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast tostUserFound = Toast.makeText(getApplicationContext(), "Parking Added!", Toast.LENGTH_SHORT);
                    tostUserFound.show();
                }
            });

        }
    }
}