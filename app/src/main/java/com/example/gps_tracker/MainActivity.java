package com.example.gps_tracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.auth.api.Auth;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    private static int SIGN_IN_CODE = 1;
    private ConstraintLayout MainConstraint;
    private FirebaseListAdapter<Message> adapter;
    private Button sendButton;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_CODE){
            if(resultCode == RESULT_OK) {
                Snackbar.make(MainConstraint, "Вы авторизованы", Snackbar.LENGTH_SHORT).show();
            }else{
                Snackbar.make(MainConstraint, "Вы не авторизованы", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainConstraint =findViewById(R.id.MainConstraint);
        sendButton =  findViewById(R.id.send);

        /*sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new Message(
                                FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                "Gps-noVPN")
                        );

            }
        });*/

        //проверка решисрации пользователя
        if(FirebaseAuth.getInstance().getCurrentUser() == null )
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE);
            else {
            Snackbar.make(MainConstraint, "Вы авторизованы", Snackbar.LENGTH_SHORT).show();
        }
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
    }
    public void service_on(View view)
    {
        startService(new Intent(MainActivity.this,MyService.class));
    }
    public void service_off(View view)
    {
        stopService(new Intent(MainActivity.this,MyService.class));
    }
}
