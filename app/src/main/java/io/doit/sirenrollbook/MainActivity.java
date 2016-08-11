package io.doit.sirenrollbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewUserNew();
        viewUserList();
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
        final DbHelper dbHelper = new DbHelper(getApplicationContext(), "User.db", null, 2);
        EditText userName = (EditText) findViewById(R.id.user_name);
        EditText userEmail = (EditText) findViewById(R.id.user_email);
        EditText userPassword = (EditText) findViewById(R.id.user_password);

        dbHelper.insert(userName.getText().toString(), userEmail.getText().toString(), userPassword.getText().toString());

        viewUserList();
    }

    private void viewUserList() {
        setContentView(R.layout.user_list);
//        setContentView(R.layout.demo_table);

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

    /*
        TODO :
        - 화면이 고정됨
            - 드래그가 안됨
            - 데이터가 길면 버튼이 안보임
    */
    private void showUserList() {
        final DbHelper dbHelper = new DbHelper(getApplicationContext(), "User.db", null, 2);

        List<User> userList = dbHelper.getUserList();

        TableLayout table_layout = (TableLayout) findViewById(R.id.tableLayout1);

        for (User user : userList) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            TextView userEmail = new TextView(this);
            userEmail.setText(user.getUserEmail());
            userEmail.setPadding(20, 0, 0, 0);
            row.addView(userEmail);

            TextView userName = new TextView(this);
            userName.setText(user.getUserName());
            userName.setPadding(30, 0, 0, 0);
            row.addView(userName);

            Button btnUpdate = new Button(this);
            btnUpdate.setText("U");
            row.addView(btnUpdate);

            Button btnDelete = new Button(this);
            btnDelete.setText("D");

            final int uid = user.getUid();
            btnDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    dbHelper.deleteUser(uid);
                }
            });
            row.addView(btnDelete);

            table_layout.addView(row);
        }
    }
}
