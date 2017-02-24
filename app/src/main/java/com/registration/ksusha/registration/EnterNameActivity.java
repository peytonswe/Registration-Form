package com.registration.ksusha.registration;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterNameActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sPref;

    final String SAVED_NAME = "saved_name";
    final String SAVED_LAST_NAME = "saved_last_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_name_main);

        Button doneBtn = (Button) findViewById(R.id.done_btn);
        Button cancelBtn = (Button) findViewById(R.id.cancel_btn);

        doneBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        EditText nameEdit = (EditText) findViewById(R.id.name_edit);
        EditText lastNameEdit = (EditText) findViewById(R.id.last_name_edit);

        Intent intent;
        switch (v.getId()) {
            case R.id.done_btn:
                intent = new Intent();
                intent.putExtra("user_name", nameEdit.getText().toString());
                String user_name = nameEdit.getText().toString();
                if (user_name.matches("")) {
                    Toast.makeText(this, "You did not enter all data", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("user_last_name", lastNameEdit.getText().toString());
                String last_name = lastNameEdit.getText().toString();
                if (last_name.matches("")) {
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
        EditText nameEdit = (EditText) findViewById(R.id.name_edit);
        EditText lastNameEdit = (EditText) findViewById(R.id.last_name_edit);

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor name = sPref.edit();
        SharedPreferences.Editor last = sPref.edit();
        name.putString(SAVED_NAME, nameEdit.getText().toString());
        name.apply();
        last.putString(SAVED_LAST_NAME, lastNameEdit.getText().toString());
        last.apply();
    }

    void loadText() {
        EditText nameEdit = (EditText) findViewById(R.id.name_edit);
        EditText lastNameEdit = (EditText) findViewById(R.id.last_name_edit);

        sPref = getPreferences(MODE_PRIVATE);
        String savedName = sPref.getString(SAVED_NAME, "");
        String savedLastName = sPref.getString(SAVED_LAST_NAME, "");
        nameEdit.setText(savedName);
        lastNameEdit.setText(savedLastName);

    }

}
