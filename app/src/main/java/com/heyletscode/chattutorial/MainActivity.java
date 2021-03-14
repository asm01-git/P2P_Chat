package com.heyletscode.chattutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    PopupWindow popupWindow;

    /*static{
        System.loadLibrary("native-lib");
        System.loadLibrary("node");
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("P2P Chat");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);


        EditText editText_username = findViewById(R.id.editText_username);
        EditText editText_room_name = findViewById(R.id.editText_room_name);
        EditText editText_room_pwd = findViewById(R.id.editText_room_password);

        findViewById(R.id.enterBtn)
                .setOnClickListener(v -> {

                    Intent intent = new Intent(this, ChatActivity.class);
                    intent.putExtra("name", editText_username.getText().toString());
                    intent.putExtra("room", editText_room_name.getText().toString());
                    intent.putExtra("pwd", editText_room_pwd.getText().toString());
                    startActivity(intent);

                });
        findViewById(R.id.createRoomBtn)
                .setOnClickListener(v -> {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupWindow=new PopupWindow(inflater.inflate(R.layout.create_room_popup,this.findViewById(R.id.main_container),false),
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,true);

                    View mainContainer=this.findViewById(R.id.main_container);
                    popupWindow.showAtLocation(mainContainer,Gravity.CENTER,0,0);
                    mainContainer.setAlpha(0.1f);

                    View popupView = popupWindow.getContentView();
                    CheckBox password_checkbox = (CheckBox) popupView.findViewById(R.id.password_checkbox);
                    EditText password_edittext = (EditText) popupView.findViewById(R.id.editText_set_password);

                    //Setting functionality of password checkbox
                    password_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked)
                            password_edittext.setEnabled(true);
                        else
                            password_edittext.setEnabled(false);
                    });

                    //Setting functionality of button
                    popupView.findViewById(R.id.createRoomBtn2)
                            .setOnClickListener(v1 -> {

                            });
                });
    }
}
