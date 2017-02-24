package com.registration.ksusha.registration;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BirthdayActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sPref;

    final String SAVED_DATE = "saved_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday_main);

        Button doneBtn = (Button) findViewById(R.id.done_btn);
        Button cancelBtn = (Button) findViewById(R.id.cancel_btn);

        doneBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        EditText birthdayEdit = (EditText) findViewById(R.id.birthday_edit);

        Intent intent;
        switch (v.getId()) {
            case R.id.done_btn:
                intent = new Intent();
                intent.putExtra("user_birthday", birthdayEdit.getText().toString());
                String user_birthday = birthdayEdit.getText().toString();
                if (user_birthday.matches("")) {
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
        EditText birthdayEdit = (EditText) findViewById(R.id.birthday_edit);

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor date = sPref.edit();
        date.putString(SAVED_DATE, birthdayEdit.getText().toString());
        date.apply();

    }

    void loadText() {
        EditText birthdayEdit = (EditText) findViewById(R.id.birthday_edit);

        sPref = getPreferences(MODE_PRIVATE);
        String savedBirthday = sPref.getString(SAVED_DATE, "");
        birthdayEdit.setText(savedBirthday);
    }
}
