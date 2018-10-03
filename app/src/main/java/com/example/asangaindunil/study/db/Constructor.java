package com.example.asangaindunil.study.db;

import android.provider.BaseColumns;

public class Constructor {

    private Constructor(){}



    public static class Session implements BaseColumns{
        public static final String TABLE_NAME ="Study_Session";
        public static final String Col_1 ="Lesson_Name";
        public static final String Col_2 ="Description";
        public static final String Col_3 ="wFrom";
        public static final String Col_4 ="wTo";
        public static final String Col_5 ="Complete";
        public static final String Col_6 ="Day";

    }
}
