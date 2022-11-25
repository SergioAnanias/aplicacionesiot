package com.example.detecciondegas;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "detecciondegas";
    private static final int DB_VERSION = 1;
    private static final String TABLE_USERS = "USUARIOS";
    private static final String TABLE_LOCALES = "LOCALES";
    private static final String ID_USERS = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String RESTAURANT = "restaurant";
    private static final String RUT_ENCARGADO = "rut_encargado";
    private static final String DIRECCION = "direccion_local";
    private static final String NOMBRE_ADMIN = "nombre_admin";
    private static final String GERENTE = "gerente";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_USERS + " ("
                + ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT, "
                + PASSWORD + " TEXT, "
                + RESTAURANT + " TEXT)";
        String query_locales = "CREATE TABLE " + TABLE_LOCALES + " ("
                + RUT_ENCARGADO + " TEXT PRIMARY KEY, "
                + DIRECCION + " TEXT, "
                + NOMBRE_ADMIN + " TEXT, "
                + "UsuarioID REFERENCES " + TABLE_USERS + "(id))";
        db.execSQL(query);
        db.execSQL(query_locales);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean addNewUser(String username, String password, String restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username = ? ", new String[]{username});
        if (cursorUser.moveToFirst()) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PASSWORD, password);
        values.put(RESTAURANT, restaurant);
        db.insert(TABLE_USERS, null, values);
        return true;

    }

    public boolean addNewLocal(String rut_encargado, String direccion, String nombre_admin, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_LOCALES + " WHERE RUT_ENCARGADO = ? ", new String[]{rut_encargado});
        if (cursorUser.moveToFirst()) {
            return false;
        }
        values.put(RUT_ENCARGADO, rut_encargado);
        values.put(DIRECCION, direccion);
        values.put(NOMBRE_ADMIN, nombre_admin);
        values.put("UsuarioID", id);
        db.insert(TABLE_LOCALES, null, values);
        db.close();
        return true;
    }

    public ArrayList<UserModel> getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username = ? limit 1", new String[]{username});
        ArrayList<UserModel> userModel = new ArrayList<>();
        if (cursorUser.moveToFirst()) {
            do {
                if (password.equals(cursorUser.getString(2))) {
                    userModel.add(new UserModel(cursorUser.getInt(0),
                            cursorUser.getString(3),
                            cursorUser.getString(2)));
                }
            } while (cursorUser.moveToNext());
            cursorUser.close();
            return userModel;
        }
        cursorUser.close();
        return userModel;
    }
    public ArrayList<localModel> getLocal(String rut) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_LOCALES + " WHERE rut_encargado = ? limit 1", new String[]{rut});
        ArrayList<localModel> localModel = new ArrayList<>();
        if (cursorUser.moveToFirst()) {
            do {

                localModel.add(new localModel(cursorUser.getString(0),
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getInt(3)));

            } while (cursorUser.moveToNext());
            cursorUser.close();
            return localModel;
        }
        cursorUser.close();
        return localModel;
    }
    public ArrayList<localModel> getLocalDueno(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorLocal = db.rawQuery("SELECT * FROM " + TABLE_LOCALES + " WHERE UsuarioID = " + id, null);
        ArrayList<localModel> localModel = new ArrayList<>();
        if (cursorLocal.moveToFirst()) {
            do {
                localModel.add(new localModel(cursorLocal.getString(0),
                        cursorLocal.getString(1),
                        cursorLocal.getString(2),
                        cursorLocal.getInt(3)));
            } while (cursorLocal.moveToNext());
            cursorLocal.close();
            return localModel;
        }
        cursorLocal.close();
        return localModel;
    }

    public void updateLocal(String direccion, String nombre, String rut){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(DIRECCION, direccion);
        values.put(NOMBRE_ADMIN, nombre);
        db.update(TABLE_LOCALES, values, RUT_ENCARGADO + " = ? ",
                new String[]{String.valueOf(rut)});
        db.close();
    }
    public void deleteLocal(String rut){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_LOCALES, RUT_ENCARGADO +" = ?",
                    new String[]{String.valueOf(rut)});
            db.close();

    }
}
