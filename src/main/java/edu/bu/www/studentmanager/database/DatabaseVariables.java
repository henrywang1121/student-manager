package edu.bu.www.studentmanager.database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseVariables {

    private DatabaseVariables() {}

    public static final String AUTHORITY = "edu.bu.www.studentmanager";
    public static final String TABLE_PATH = "studentlist2";

    public static final class StudentDatabaseContent implements BaseColumns {

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_STUDENT_NAME ="NAME";
        public final static String COLUMN_SCHOOL = "SCHOOL";
        public final static String COLUMN_GRADE = "GRADE";
        public final static String COLUMN_INTEREST = "INTEREST";
        public static final int interest_humanities = 0;
        public static final int interest_sciences = 1;
        public static final int interest_engineering = 2;
        public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY+"/"+TABLE_PATH);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + TABLE_PATH;
        public final static String TABLE_NAME = "studentlist2";

    }

}

