package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userinfo.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateTable = "CREATE TABLE "+UsersMaster.Users.TABLE_NAME+" ("+
                UsersMaster.Users._ID+" INTEGER PRIMARY KEY,"+
                UsersMaster.Users.COLUMN_NAME_USERNAME+" TEXT,"+
                UsersMaster.Users.COLUMN_NAME_PASSWORD+" TEXT);";

        db.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(String userName, String password){

        SQLiteDatabase  db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);

        long result = db.insert(UsersMaster.Users.TABLE_NAME, null, values);

        if(result > 0){
            return true;
        }else{
            return false;
        }

    }

    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection ={
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_USERNAME,
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };

        String sortOder = UsersMaster.Users.COLUMN_NAME_USERNAME+" DESC";

        Cursor cursor = db.query(UsersMaster.Users.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOder);

        List userNameList = new ArrayList();
        List passwordList = new ArrayList();


        while (cursor.moveToNext()){
           String userName = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
           String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PASSWORD));

           userNameList.add(userName);
           passwordList.add(password);

        }

        return userNameList;
    }


    public int deleteUser(String name){
        SQLiteDatabase db = getReadableDatabase();

        String select = UsersMaster.Users.COLUMN_NAME_USERNAME+" Like ? ";

        String [] selectionArgs = {name};

        int result = db.delete(UsersMaster.Users.TABLE_NAME,select,selectionArgs);

        if(result > 0)
            return 1;
        else
            return -1;
    }

    public int updateUser(String nameU, String passwordU){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,passwordU);

        String seletion = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] seletionArgs = {nameU};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                seletion,
                seletionArgs
        );

        return count;
    }








}
