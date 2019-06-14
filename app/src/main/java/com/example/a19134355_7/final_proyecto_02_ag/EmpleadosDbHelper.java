package com.example.a19134355_7.final_proyecto_02_ag;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EmpleadosDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "HipotecaDb" ;
    private static CursorFactory factory = null;

    public EmpleadosDbHelper (Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE Turnos(" +
                "_id INTEGER PRIMARY KEY," +
                "_nombre TEXT NOT NULL, " +
                "_jornada TEXT NOT NULL )" );

        db.execSQL( "CREATE UNIQUE INDEX hip_nombre ON Turnos(_nombre ASC)" );

        Log.i(this.getClass().toString(), "Tabla HIPOTECA creada");

        /*
         * Insertamos datos iniciales
         */
        db.execSQL("INSERT INTO Turnos(_id,_nombre,_jornada) VALUES(1,'Turno 1','Mañana1')");
        db.execSQL("INSERT INTO Turnos(_id,_nombre,_jornada)VALUES(2,'Turno 2','Mañana2')");
        db.execSQL("INSERT INTO Turnos(_id,_nombre,_jornada) VALUES(3,'Turno 3','Tarde 1')");
        db.execSQL("INSERT INTO Turnos(_id,_nombre,_jornada) VALUES(4,'Turno 4','Tarde 2')");



        Log.i(this.getClass().toString(), "Base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (newVersion == 2)
        {
            db.execSQL("UPDATE Turnos SET _nombre = 'Julián Gómez Martínez'," +
                    "              _jornada = 'jgmartinez@gmail.com'" +
                    " WHERE _id = 1");
        }
    }

}
