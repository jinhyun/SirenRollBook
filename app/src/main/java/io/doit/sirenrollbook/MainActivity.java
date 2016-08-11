package io.doit.sirenrollbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewUserNew();
    }

    private void viewUserNew() {
        setContentView(R.layout.user_new);

        bindBtnUserNew();
    }

    private void bindBtnUserNew() {
        Button btnUserList = (Button) findViewById(R.id.button_user_list);
        btnUserList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                viewUserList();
            }
        });

        Button btnSignUp = (Button) findViewById(R.id.button_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });

    }

    private void saveUser() {
        final DbHelper dbHelper = new DbHelper(getApplicationContext(), "Attend.db", null, 1);
        EditText userName = (EditText) findViewById(R.id.user_name);
        EditText userEmail = (EditText) findViewById(R.id.user_email);
        EditText userPassword = (EditText) findViewById(R.id.user_password);

        dbHelper.insert(userName.getText().toString(), userEmail.getText().toString(), userPassword.getText().toString());

        viewUserList();
    }

    private void viewUserList() {
        setContentView(R.layout.user_list);

        bindBtnUserList();
        showUserList();
    }

    private void bindBtnUserList() {
        Button btnUserNew = (Button) findViewById(R.id.button_user_new);
        btnUserNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                viewUserNew();
            }
        });
    }

    private void showUserList() {
        final DbHelper dbHelper = new DbHelper(getApplicationContext(), "Attend.db", null, 1);

        TextView result = (TextView) findViewById(R.id.button_user_list);
        result.setText(dbHelper.getResult());
    }
}
