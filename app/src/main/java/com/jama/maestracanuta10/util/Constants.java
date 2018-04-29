package com.jama.maestracanuta10.util;

/**
 * Created by casa on 09/11/2017.
 */

public class Constants {

    public static final int DB_VERSION = 3;
    public static final String DB_NAME = "maestraCanutaDB";
    public static final String TABLE_NAME = "student";

    //Student Table columns
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TUTOR_NAME = "tutor_name";
    public static final String KEY_TUTOR_PHONE = "tutor_phone";
    public static final String KEY_TUTOR_MAIL = "tutor_mail";

    //Event Table columns
    public static final String EVENT_TABLE_NAME = "event";
    public static final String EVENT_ID = "id";
    public static final String EVENT_ID_STUDENT = "id_student";
    public static final String EVENT_ID_MISCONDUCT = "id_misconduct";
    public static final String EVENT_DATE = "date";
    public static final String EVENT_COMMENTS = "comments";
    public static final String EVENT_ID_STATUS = "id_status";

    //Misconduct Table columns
    public static final String MISCONDUCT_TABLE_NAME = "misconduct";
    public static final String MISCONDUCT_ID = "id";
    public static final String MISCONDUCT_NAME = "name";
    public static final String MISCONDUCT_TYPE = "type";

}
