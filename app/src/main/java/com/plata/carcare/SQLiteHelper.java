package com.plata.carcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.Nullable;
import com.plata.carcare.model.Control;
import com.plata.carcare.model.Service;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertCar(String name, byte[] photo, String brand, String model, int productionYear, String engineType) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO CAR VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindLong(2, 0);
        statement.bindBlob(3, photo);
        statement.bindString(4, brand);
        statement.bindString(5, model);
        statement.bindLong(6, (long) productionYear);
        statement.bindString(7, engineType);
        statement.executeInsert();
    }

    public void updateCar(int id, String name, byte[] photo, String brand, String model, int productionYear, String engineType) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE CAR SET name = ?, photo = ?, brand = ?, model = ?, production_year = ?, engine_type = ? WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindBlob(2, photo);
        statement.bindString(3, brand);
        statement.bindString(4, model);
        statement.bindLong(5, productionYear);
        statement.bindString(6, engineType);
        statement.bindLong(7, id);
        statement.executeUpdateDelete();
    }

    public void updateMileage(int id, int mileage) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE CAR SET mileage = ? where id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1, mileage);
        statement.bindLong(2, id);
        statement.executeUpdateDelete();
    }

    public void deleteCar(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM CAR WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, String.valueOf(id));
        statement.executeUpdateDelete();
    }

    public void insertControl(Service.Status status, String date, String name, int mileage, String desc, String type) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO CONTROL VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, status.name());
        statement.bindString(2, date);
        statement.bindString(3, name);
        statement.bindLong(4, mileage);
        statement.bindString(5, desc);
        statement.bindString(6, type);
        statement.executeInsert();
    }

    public void updateControl(int id, Service.Status status, String date, String name, int mileage, String desc, String type) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE CONTROL SET status = ?, date = ?, name = ?, mileage = ?, desc = ?, type = ? WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, status.name());
        statement.bindString(2, date);
        statement.bindString(3, name);
        statement.bindLong(4, mileage);
        statement.bindString(5, desc);
        statement.bindString(6, type);
        statement.bindLong(7, id);
        statement.executeInsert();
    }

    public void deleteControl(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM CONTROL WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, String.valueOf(id));
        statement.executeUpdateDelete();
    }

    public void insertControlType(String type) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO CONTROL_TYPE VALUES (NULL, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, type);
        statement.executeInsert();
    }

    public void deleteControlType(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM CONTROL_TYPE WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, String.valueOf(id));
        statement.executeUpdateDelete();
    }

    public void insertSeasonChangeType(String type) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO SEASON_CHANGE_TYPE VALUES (NULL, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, type);
        statement.executeInsert();
    }

    public void deleteSeasonChangeType(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM SEASON_CHANGE_TYPE WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, String.valueOf(id));
        statement.executeUpdateDelete();
    }

    public void insertMileageChangeType(String type) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO MILEAGE_CHANGE_TYPE VALUES (NULL, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, type);
        statement.executeInsert();
    }

    public void deleteMileageChangeType(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM MILEAGE_CHANGE_TYPE WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, String.valueOf(id));
        statement.executeUpdateDelete();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
