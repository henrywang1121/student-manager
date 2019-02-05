package edu.bu.www.studentmanager.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import edu.bu.www.studentmanager.database.DatabaseVariables.StudentDatabaseContent;


public class StudentContentProvider extends ContentProvider {

    private StudentDbHelper studentDbHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int STUDENTS = 1;
    private static final int STUDENT_ID = 2;

    public StudentContentProvider(){

    }

    @Override
    public boolean onCreate() {
        uriMatcher.addURI(DatabaseVariables.AUTHORITY, DatabaseVariables.TABLE_PATH, STUDENTS);
        uriMatcher.addURI(DatabaseVariables.AUTHORITY, DatabaseVariables.TABLE_PATH + "/#", STUDENT_ID);
        studentDbHelper = new StudentDbHelper(getContext());
        return true;
    }

    private Uri insertStudent(Uri uri, ContentValues values) {

        SQLiteDatabase sqliteDatabase = studentDbHelper.getWritableDatabase();
        long id = sqliteDatabase.insert(StudentDatabaseContent.TABLE_NAME, null, values);
        return ContentUris.withAppendedId(uri, id);
    }

    private int updateStudent(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase sqliteDatabase = studentDbHelper.getWritableDatabase();
        return sqliteDatabase.update(StudentDatabaseContent.TABLE_NAME, values, selection, selectionArgs);
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case STUDENTS:
                return insertStudent(uri, contentValues);
            default:
                throw new IllegalArgumentException("insert Error: " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case STUDENTS:
                return updateStudent(uri, contentValues, selection, selectionArgs);
            case STUDENT_ID:
                selection = StudentDatabaseContent._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateStudent(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("update Error" + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteDatabase sqliteDatabase = studentDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = uriMatcher.match(uri);
        switch (match) {
            case STUDENTS:
                cursor = sqliteDatabase.query(StudentDatabaseContent.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case STUDENT_ID:
                selection = StudentDatabaseContent._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = sqliteDatabase.query(StudentDatabaseContent.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("query Error: " + uri);

        }
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase sqliteDatabase = studentDbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        switch (match) {
            case STUDENTS:
                return sqliteDatabase.delete(StudentDatabaseContent.TABLE_NAME, selection, selectionArgs);
            case STUDENT_ID:
                selection = StudentDatabaseContent._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return sqliteDatabase.delete(StudentDatabaseContent.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("delete Error" + uri);

        }
    }


    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case STUDENTS:
                return StudentDatabaseContent.CONTENT_LIST_TYPE;
            case STUDENT_ID:
                return StudentDatabaseContent.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("getType Error");

        }
    }

}
