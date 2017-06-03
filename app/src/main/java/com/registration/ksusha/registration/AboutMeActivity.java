package com.registration.ksusha.registration;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AboutMeActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sPref;

    private static final String SAVED_ME = "saved_me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me_main);

        Button doneBtn = (Button) findViewById(R.id.done_btn);
        Button cancelBtn = (Button) findViewById(R.id.cancel_btn);

        doneBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        EditText aboutMeEdit = (EditText) findViewById(R.id.about_me_edit);

        Intent intent;
        switch (v.getId()) {
            case R.id.done_btn:
                intent = new Intent();
                intent.putExtra("user_about", aboutMeEdit.getText().toString());
                String user_about = aboutMeEdit.getText().toString();
                if (user_about.matches("")) {
                    Toast.makeText(this, "You did not enter all data", Toast.LENGTH_SHORT).show();
                    return;
                }
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.cancel_btn:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        saveText();
        super.onDestroy();
    }

    void saveText() {
        EditText aboutMeEdit = (EditText) findViewById(R.id.about_me_edit);

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor me = sPref.edit();
        me.putString(SAVED_ME, aboutMeEdit.getText().toString());
        me.apply();

    }

    void loadText() {
        EditText aboutMeEdit = (EditText) findViewById(R.id.about_me_edit);

        sPref = getPreferences(MODE_PRIVATE);
        String savedMe = sPref.getString(SAVED_ME, "");
        aboutMeEdit.setText(savedMe);
    }
}
