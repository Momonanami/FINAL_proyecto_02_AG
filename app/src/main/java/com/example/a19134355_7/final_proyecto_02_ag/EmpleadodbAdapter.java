package com.example.a19134355_7.final_proyecto_02_ag;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class EmpleadodbAdapter extends Object
{
    public static final String C_TABLA = "Turnos" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_ID  = "_id";
    public static final String C_NOMBRE = "_nombre";
    public static final String C_JORNADA = "_jornada";


    private Context contexto;
    private EmpleadosDbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_ID, C_NOMBRE, C_JORNADA} ;

    public EmpleadodbAdapter (Context context)
    {
        this.contexto = context;
    }

    public EmpleadodbAdapter abrir() throws SQLException
    {
        dbHelper = new EmpleadosDbHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        dbHelper.close();
    }

    /**
     * Devuelve cursor con todos las columnas de la tabla
     */
    public Cursor getCursor() throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);

        return c;
    }
    public Cursor getRegistro(long id) throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, C_ID + "=" + id, null, null, null, null, null);

        //Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}

