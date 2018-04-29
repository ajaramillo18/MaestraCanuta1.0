package com.jama.maestracanuta10.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jama.maestracanuta10.model.Event;
import com.jama.maestracanuta10.model.Misconduct;
import com.jama.maestracanuta10.model.Student;
import com.jama.maestracanuta10.util.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajaramillo on 09/11/2017.
 */

public class
DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {

        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " TEXT PRIMARY KEY,"
                + Constants.KEY_NAME + " TEXT,"
                + Constants.KEY_TUTOR_NAME + " TEXT,"
                + Constants.KEY_TUTOR_PHONE+ " TEXT,"
                + Constants.KEY_TUTOR_MAIL + " TEXT);";

        db.execSQL(CREATE_STUDENT_TABLE);


        String CREATE_EVENT_TABLE = "CREATE TABLE IF NOT EXISTS " + Constants.EVENT_TABLE_NAME + " ("
                + Constants.EVENT_ID + " INTEGER PRIMARY KEY,"
                + Constants.EVENT_ID_STUDENT + " TEXT,"
                + Constants.EVENT_ID_MISCONDUCT + " TEXT,"
                + Constants.EVENT_DATE+ " INTEGER,"
                + Constants.EVENT_COMMENTS+ " TEXT,"
                + Constants.EVENT_ID_STATUS + " TEXT);";

        db.execSQL(CREATE_EVENT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //TODO make upgrade script  so no data is missed between upgrades
       // db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
       // db.execSQL("DROP TABLE IF EXISTS " + Constants.EVENT_TABLE_NAME);

        onCreate(db);
    }


    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods for students
     */

    public void addStudent(Student student){

        SQLiteDatabase db = null;

        try {
           db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Constants.KEY_ID, student.getId());
            values.put(Constants.KEY_NAME, student.getName());
            values.put(Constants.KEY_TUTOR_NAME, student.getTutorName());
            values.put(Constants.KEY_TUTOR_PHONE, student.getTutorPhoneNumber());
            values.put(Constants.KEY_TUTOR_MAIL, student.getTutorEmail());

            db.insert(Constants.TABLE_NAME,null, values);
        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error saving student"+ e.getMessage());
        }

        Log.d("Student Saved", "Saved to DB:" + student.getId());

    }


    //TODO implement an status field in the student table so the records are marked as "DELETED" and in this way not delete them for good
    public void deleteStudent(String id){

        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();

            db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",
                    new String[] {id});
        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error deleting student"+ e.getMessage());
        }

        Log.d("Student Deleted", "Deleted from DB:" + id);

    }

    public int updateStudent(Student student){

        SQLiteDatabase db = null;
        int result = 0;

        try {
            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Constants.KEY_ID, student.getId());
            values.put(Constants.KEY_NAME, student.getName());
            values.put(Constants.KEY_TUTOR_NAME, student.getTutorName());
            values.put(Constants.KEY_TUTOR_PHONE, student.getTutorPhoneNumber());
            values.put(Constants.KEY_TUTOR_MAIL, student.getTutorEmail());

            result =  db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[] {student.getId()} );
        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error updating student"+ e.getMessage());
        }

        Log.d("Student Updated", "Updated to DB: "+ student.getId());
        return result;
    }


    public Student getStudent(String id){

        SQLiteDatabase db = null;
        Student student = new Student();

        try {
            db = this.getReadableDatabase();

            Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID,
                            Constants.KEY_NAME, Constants.KEY_TUTOR_NAME, Constants.KEY_TUTOR_PHONE, Constants.KEY_TUTOR_MAIL},
                    Constants.KEY_ID + "=?",
                    new String[] {id}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            student.setId(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID)));
            student.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
            student.setTutorName(cursor.getString(cursor.getColumnIndex(Constants.KEY_TUTOR_NAME)));
            student.setTutorPhoneNumber(cursor.getString(cursor.getColumnIndex(Constants.KEY_TUTOR_PHONE)));
            student.setTutorEmail(cursor.getString(cursor.getColumnIndex(Constants.KEY_TUTOR_MAIL)));

        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error retrieving student " + student.getId()+ e.getMessage());
        }

        Log.d("Student Retrieved", "Retrieved from DB: "+ student.getId());
        return student;
    }


    public List<Student> getAllStudents(){

        SQLiteDatabase db = null;
        List<Student> studentList = new ArrayList<>();

        try {
            db = this.getReadableDatabase();

            Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID,
                            Constants.KEY_NAME, Constants.KEY_TUTOR_NAME, Constants.KEY_TUTOR_PHONE, Constants.KEY_TUTOR_MAIL},
                            null, null, null, null, Constants.KEY_NAME + " ASC");

            if (cursor.moveToFirst())
            {
                do {

                    Student student = new Student();
                    student.setId(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID)));
                    student.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                    student.setTutorName(cursor.getString(cursor.getColumnIndex(Constants.KEY_TUTOR_NAME)));
                    student.setTutorPhoneNumber(cursor.getString(cursor.getColumnIndex(Constants.KEY_TUTOR_PHONE)));
                    student.setTutorEmail(cursor.getString(cursor.getColumnIndex(Constants.KEY_TUTOR_MAIL)));

                    studentList.add(student);

                }while (cursor.moveToNext());
            }

        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error retrieving all students " +  e.getMessage());
        }

        Log.d(" Student Retrieved", "Retrieved all from DB");
        return studentList;
    }



    //Get count
    public int getStudentsCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount() ;
    }




    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods for events
     */

    public void addEvent(Event event){

        SQLiteDatabase db = null;



        try {

            //get current time in seconds as an Integer value
            long ldate = TimeUnit.SECONDS.convert(System.currentTimeMillis(),TimeUnit.MILLISECONDS);
            Integer iDate = Integer.valueOf (Long.valueOf(ldate).intValue());
            event.setDate(iDate);

            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
           // Autoincremented, no needed
            // values.put(Constants.EVENT_ID, event.getId());
            values.put(Constants.EVENT_ID_STUDENT, event.getIdStudent());
            values.put(Constants.EVENT_ID_MISCONDUCT, event.getIdMisconduct());
            values.put(Constants.EVENT_DATE, event.getDate());
            values.put(Constants.EVENT_COMMENTS, event.getComments());
            values.put(Constants.EVENT_ID_STATUS, event.getIdStatus());


            db.insert(Constants.EVENT_TABLE_NAME,null, values);
        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error saving event"+ e.getMessage());
        }

        Log.d("Event Saved", "Event Saved to DB:" + event.getId());

    }

    public List<Event> getAllEvents() {

        SQLiteDatabase db = null;
        List<Event> eventList = new ArrayList<>();

        try {
            db = this.getReadableDatabase();

            Cursor cursor = db.query(Constants.EVENT_TABLE_NAME, new String[] {Constants.EVENT_ID,
                            Constants.EVENT_DATE, Constants.EVENT_COMMENTS, Constants.EVENT_ID_MISCONDUCT, Constants.EVENT_ID_STATUS, Constants.EVENT_ID_STUDENT},
                    null, null, null, null, Constants.EVENT_ID + " ASC");

            Log.d("test","Number of events retrieved:"+ cursor.getCount());
            if (cursor.moveToFirst())
            {
                do {

                    Event event = new Event();
                    event.setId(cursor.getString(cursor.getColumnIndex(Constants.EVENT_ID)));
                    event.setDate(cursor.getInt(cursor.getColumnIndex(Constants.EVENT_DATE)));
                    event.setComments(cursor.getString(cursor.getColumnIndex(Constants.EVENT_COMMENTS)));
                    event.setIdMisconduct(cursor.getString(cursor.getColumnIndex(Constants.EVENT_ID_MISCONDUCT)));
                    event.setIdStatus(cursor.getString(cursor.getColumnIndex(Constants.EVENT_ID_STATUS)));
                    event.setIdStudent(cursor.getString(cursor.getColumnIndex(Constants.EVENT_ID_STUDENT)));

                    eventList.add(event);

                }while (cursor.moveToNext());
            }

        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error retrieving all students " +  e.getMessage());
        }

        Log.d("Events Retrieved", "Retrieved all from DB");
        return eventList;
    }


    /**
     *  CRUD OPERATIONS: Create, Read, Update, Delete Methods for misconducts
     */

    public void addMisconduct(Misconduct misconduct){

        SQLiteDatabase db = null;

        try {
            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Constants.MISCONDUCT_ID, misconduct.getId());
            values.put(Constants.MISCONDUCT_NAME, misconduct.getName());
            values.put(Constants.MISCONDUCT_TYPE, misconduct.getType());


            db.insert(Constants.MISCONDUCT_TABLE_NAME,null, values);
        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error saving Misconduct"+ e.getMessage());
        }

        Log.d("Misconduct Saved", "Saved to DB:" + misconduct.getId());

    }


    public Misconduct getMisconduct(String id){

        SQLiteDatabase db = null;
        Misconduct misconduct = new Misconduct();

        try {
            db = this.getReadableDatabase();

            Cursor cursor = db.query(Constants.MISCONDUCT_TABLE_NAME, new String[] {Constants.MISCONDUCT_ID,
                            Constants.MISCONDUCT_NAME, Constants.MISCONDUCT_TYPE},
                    Constants.MISCONDUCT_ID + "=?",
                    new String[] {id}, null, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            misconduct.setId(cursor.getString(cursor.getColumnIndex(Constants.MISCONDUCT_ID)));
            misconduct.setName(cursor.getString(cursor.getColumnIndex(Constants.MISCONDUCT_NAME)));
            misconduct.setType(cursor.getString(cursor.getColumnIndex(Constants.MISCONDUCT_TYPE)));


        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error retrieving Misconduct " + misconduct.getId()+ e.getMessage());
        }

        Log.d("Misconduct Retrieved", "Retrieved from DB: "+ misconduct.getId());
        return misconduct;
    }


    public List<Misconduct> getAllMisconducts(){

        SQLiteDatabase db = null;
        List<Misconduct> misconductList = new ArrayList<>();

        try {
            db = this.getReadableDatabase();

            Cursor cursor = db.query(Constants.MISCONDUCT_TABLE_NAME, new String[] {Constants.MISCONDUCT_ID,
                            Constants.MISCONDUCT_NAME, Constants.MISCONDUCT_TYPE},
                    null, null, null, null, Constants.MISCONDUCT_NAME + " ASC");

            if (cursor.moveToFirst())
            {
                do {

                    Misconduct misconduct = new Misconduct();
                    misconduct.setId(cursor.getString(cursor.getColumnIndex(Constants.MISCONDUCT_ID)));
                    misconduct.setName(cursor.getString(cursor.getColumnIndex(Constants.MISCONDUCT_NAME)));
                    misconduct.setType(cursor.getString(cursor.getColumnIndex(Constants.MISCONDUCT_TYPE)));


                    misconductList.add(misconduct);

                }while (cursor.moveToNext());
            }

        }
        catch (Exception e) {
            Log.e(this.getClass().toString(),"error retrieving all Misconducts " +  e.getMessage());
        }

        Log.d("Misconduct Retrieved", "Retrieved all from DB");
        return misconductList;
    }




}
