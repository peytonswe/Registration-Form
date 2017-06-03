package com.registration.ksusha.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CompleteActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USER_NAME = "user_name";
    private static final String USER_LAST_NAME = "user_last_name";
    private static final String USER_BIRTHDAY = "user_birthday";
    private static final String USER_ABOUT = "user_about";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_main);

        String userName = getIntent().getExtras().getString(USER_NAME);
        String userLastName = getIntent().getExtras().getString(USER_LAST_NAME);
        String userBirthday = getIntent().getExtras().getString(USER_BIRTHDAY);
        String userAbout = getIntent().getExtras().getString(USER_ABOUT);

        TextView textName = (TextView) findViewById(R.id.text_name);
        TextView textLastName = (TextView) findViewById(R.id.text_last_name);
        TextView textBirthday = (TextView) findViewById(R.id.text_birthday);
        TextView textAbout = (TextView) findViewById(R.id.text_about_me);

        textName.setText(userName);
        textLastName.setText(userLastName);
        textBirthday.setText(userBirthday);
        textAbout.setText(userAbout);

        Button closeApp = (Button) findViewById(R.id.close_app);
        closeApp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }
}
