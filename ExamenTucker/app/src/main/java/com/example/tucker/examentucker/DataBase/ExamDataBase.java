package com.example.tucker.examentucker.DataBase;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by John on 3/2/2018.
 */
@Database(name = ExamDataBase.NOMBRE, version = ExamDataBase.VERSION)
public class ExamDataBase {

    public static final String NOMBRE = "todoExam01";
    public static final int VERSION = 1;
}
