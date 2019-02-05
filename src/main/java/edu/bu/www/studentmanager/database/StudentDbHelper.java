package edu.bu.www.studentmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.bu.www.studentmanager.database.DatabaseVariables.StudentDatabaseContent;


public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + StudentDatabaseContent.TABLE_NAME + " (" +
                StudentDatabaseContent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentDatabaseContent.COLUMN_STUDENT_NAME + " TEXT, " +
                StudentDatabaseContent.COLUMN_SCHOOL + " TEXT, " +
                StudentDatabaseContent.COLUMN_INTEREST + " INTEGER, " +
                StudentDatabaseContent.COLUMN_GRADE + " INTEGER);";
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}