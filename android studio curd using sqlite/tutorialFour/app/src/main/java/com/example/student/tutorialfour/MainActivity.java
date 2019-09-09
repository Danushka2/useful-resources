package com.example.student.tutorialfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Database.DBHelper;

public class MainActivity extends AppCompatActivity {


    EditText txt_userName, txt_password;
    String userNameUI,passwordUI,names;
    DBHelper db;
    Button sellectAll,deleteValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_userName = findViewById(R.id.txtUserName);
        txt_password = findViewById(R.id.txtPassword);

        sellectAll = findViewById(R.id.btn);
        deleteValue = findViewById(R.id.btnDelete);

        db = new DBHelper(this);


        deleteValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameUI = txt_userName.getText().toString();


                int result = db.deleteUser(userNameUI);

                if(result==1){
                    Toast.makeText(getApplicationContext(),"Deleting Success",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }

            }
        });


        sellectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List names = new ArrayList();
                names = db.readAllInfo();


                for(int i = 0; i< names.size(); i++) {
                    Log.i("all users", "user"+i+":"+names.get(i).toString());
                }
            }
        });

    }

    public void addInfo(View view){
        userNameUI = txt_userName.getText().toString();
        passwordUI = txt_password.getText().toString();

        boolean result = db.addUser(userNameUI,passwordUI);

        if(result){
            Toast.makeText(getApplicationContext(),"Adding Success",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }

    }

    public void updateInfo(View view){
        userNameUI = txt_userName.getText().toString();
        passwordUI = txt_password.getText().toString();

        int result = db.updateUser(userNameUI,passwordUI);

        if(result > 0){
            Toast.makeText(getApplicationContext(),"Updating Success",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        }

    }



}
