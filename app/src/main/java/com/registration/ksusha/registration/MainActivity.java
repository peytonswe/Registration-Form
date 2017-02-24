package com.registration.ksusha.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ENTER_NAME = 1;
    private static final int BIRTHDAY = 2;
    private static final int ABOUT = 3;

    private String name;
    private String last_name;
    private String birthday;
    private String about_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nameButton = (Button) findViewById(R.id.name_button);
        Button birthdayButton = (Button) findViewById(R.id.birthday_button);
        Button aboutMeButton = (Button) findViewById(R.id.about_me_button);
        Button registerButton = (Button) findViewById(R.id.register_button);

        nameButton.setOnClickListener(this);
        birthdayButton.setOnClickListener(this);
        aboutMeButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.name_button:
                intent = new Intent(this, EnterNameActivity.class);
                startActivityForResult(intent, ENTER_NAME);
                break;
            case R.id.birthday_button:
                intent = new Intent(this, BirthdayActivity.class);
                startActivityForResult(intent, BIRTHDAY);
                break;
            case R.id.about_me_button:
                intent = new Intent(this, AboutMeActivity.class);
                startActivityForResult(intent, ABOUT);
                break;
            case R.id.register_button:
                intent = new Intent(this, CompleteActivity.class);
                intent.putExtra("user_name", name);
                intent.putExtra("user_last_name", last_name);
                intent.putExtra("user_birthday", birthday);
                intent.putExtra("user_about", about_me);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case ENTER_NAME:
                    name = data.getStringExtra("user_name");
                    last_name = data.getStringExtra("user_last_name");
                    break;
                case BIRTHDAY:
                    birthday = data.getStringExtra("user_birthday");
                    break;
                case ABOUT:
                    about_me = data.getStringExtra("user_about");
                    break;

            }
        }


    }
}
