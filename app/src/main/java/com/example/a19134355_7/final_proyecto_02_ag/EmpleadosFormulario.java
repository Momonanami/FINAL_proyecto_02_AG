package com.example.a19134355_7.final_proyecto_02_ag;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
public class EmpleadosFormulario extends Activity
{
    private EmpleadodbAdapter dbAdapter;
    private Cursor cursor;

    //
    // Modo del formulario
    //
    private int modo ;

    //
    // Identificador del registro que se edita cuando la opción es MODIFICAR
    //
    private long id ;

    //
    // Elementos de la vista
    //
    private EditText nombre;
    private EditText condiciones;
    private EditText contacto;
    private EditText telefono;
    private EditText email;
    private EditText observaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados_formulario);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if (extra == null) return;


        nombre = (EditText) findViewById(R.id.nombre);


        dbAdapter = new EmpleadodbAdapter(this);
        dbAdapter.abrir();

        //
        // Obtenemos el identificador del registro si viene indicado
        //
        if (extra.containsKey(EmpleadodbAdapter.C_ID))
        {
            id = extra.getLong(EmpleadodbAdapter.C_ID);
            consultar(id);
        }

        //
        // Establecemos el modo del formulario
        //
        establecerModo(extra.getInt(Empleados.C_MODO));

    }

    private void establecerModo(int m)
    {
        this.modo = m ;

        if (modo == Empleados.C_VISUALIZAR)
        {
            this.setTitle(nombre.getText().toString());
            this.setEdicion(false);
        }
    }

    private void consultar(long id)
    {
        //
        // Consultamos el centro por el identificador
        //
        cursor = dbAdapter.getRegistro(id);

        nombre.setText(cursor.getString(cursor.getColumnIndex(EmpleadodbAdapter.C_NOMBRE)));
    }

    private void setEdicion(boolean opcion)
    {
        nombre.setEnabled(opcion);

    }

}


