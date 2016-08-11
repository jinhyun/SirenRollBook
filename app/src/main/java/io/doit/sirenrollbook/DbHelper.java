package io.doit.sirenrollbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER " +
                "(uid INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String userName, String userEmail, String userPassword) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO USER VALUES(null, '" + userName + "', '" + userEmail + "', '" + userPassword + "');");
        db.close();
    }

    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " : "
                    + cursor.getString(2)
                    + " : "
                    + cursor.getString(3)
                    + "\n";
        }

        return result;
    }

    public List<User> getUserList() {
        SQLiteDatabase db = getReadableDatabase();
        List<User> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setUid(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserEmail(cursor.getString(2));
            user.setUserPassword(cursor.getString(3));

            userList.add(user);
        }

        return userList;
    }

    public void deleteUser(int uid) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM USER WHERE uid='" + uid + "';");
        db.close();
    }
}
