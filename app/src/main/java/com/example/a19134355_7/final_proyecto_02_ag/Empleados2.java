package com.example.a19134355_7.final_proyecto_02_ag;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import static android.support.v4.os.LocaleListCompat.create;

public class Empleados2 extends ListActivity {
    private EmpleadodbAdapter dbAdapter;
    private Cursor cursor;
    private EmpleadosCursorAdapter hipotecaAdapter ;
    private ListView lista;
    public static final String C_MODO  = "modo" ;
    public static final int C_VISUALIZAR = 551 ;
    public static final int C_CREAR = 552 ;
    public static final int C_EMPLEADO1 = 553 ;
    public static final int C_EMPLEADO2 = 554 ;
    public static final int C_EMPLEADO3 = 555 ;
    public static final int C_EMPLEADO4 = 556 ;
    public static final int C_ELIMINAR = 557 ;
    private Button btnAntes;
    private Button btnDespues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados2);
        lista = (ListView) findViewById(android.R.id.list);
        dbAdapter = new EmpleadodbAdapter(this);
        btnAntes = (Button) findViewById(R.id.button3);
        btnDespues= (Button) findViewById(R.id.button4);
        dbAdapter.abrir();
        EmpleadosDbHelper dbHelper = new EmpleadosDbHelper(getBaseContext());
        consultar();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        registerForContextMenu(this.getListView());
        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
        btnAntes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Empleados.class);
                startActivityForResult(intent, 0);
            }
        });
        btnDespues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Empleados3.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    private void consultar()
    {
        cursor = dbAdapter.getCursor();
        startManagingCursor(cursor);
        hipotecaAdapter = new EmpleadosCursorAdapter(this, cursor);
        lista.setAdapter(hipotecaAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        return true;
    }

    private void visualizar(long id)
    {
        // Llamamos a la Actividad HipotecaFormulario indicando el modo visualización y el identificador del registro
        Intent i = new Intent(Empleados2.this, EmpleadosFormulario.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(EmpleadodbAdapter.C_ID,id);
        startActivityForResult(i, C_VISUALIZAR);
    }
    //@Override
   /* protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        visualizar(id);
    }*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("opciones");

        menu.add(Menu.NONE, C_EMPLEADO1, Menu.NONE, R.string.empleado1);
        menu.add(Menu.NONE, C_EMPLEADO2, Menu.NONE, R.string.empleado2);
        menu.add(Menu.NONE, C_EMPLEADO3, Menu.NONE, R.string.empleado3);
        menu.add(Menu.NONE, C_EMPLEADO4, Menu.NONE, R.string.empleado4);
    }

    /*   public View getView(int position, View convertView, ViewGroup parent) {
           // Get the Item from ListView
           View view = this.getView(position, convertView, parent);

           // Initialize a TextView for ListView each Item
           TextView tv = (TextView) view.findViewById(android.R.id.lista);

           // Set the text color of TextView (ListView Item)
           tv.setTextColor(Color.RED);

           // Generate ListView Item using TextView
           return view;
       }*/
/* private static int save = -1;

    public void onListItemClick(ListView parent, View v, int position, long id) {


        parent.getChildAt(position).setBackgroundColor(Color.BLUE);

        if (save != -1 && save != position){
            parent.getChildAt(save).setBackgroundColor(Color.BLACK);
        }

        save = position;
        lista.getChildAt(position).setBackgroundColor(Color.BLUE);
    }*/
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Intent i;
        Log.i("tag",String.valueOf(info.position));

        switch(item.getItemId())
        {

            case C_EMPLEADO1:
                int position = info.position;
                lista.getChildAt(position).setBackgroundColor(Color.BLUE);

                break;
            case C_EMPLEADO2:
                int position2 = info.position;
                lista.getChildAt(position2).setBackgroundColor(Color.RED);
                break;
            case C_EMPLEADO3:
                int position3 = info.position;
                lista.getChildAt(position3).setBackgroundColor(Color.CYAN);
                break;
            case C_EMPLEADO4:
                int position4 = info.position;
                lista.getChildAt(position4).setBackgroundColor(Color.YELLOW);
                break;
        }

        return super.onContextItemSelected(item);
    }
}