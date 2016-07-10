package com.example.alink.huerto;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bd.BdSqliteHelper;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Cultivo> cultivos;
    private TextView instruccion;
    private ImageButton nuevoCultivo;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterCultivos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static SessionManager sManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sManager = new SessionManager(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        instruccion = (TextView) findViewById(R.id.txt_crear_cultivo);
        nuevoCultivo = (ImageButton)findViewById(R.id.btn_crear_cultivo);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_cultivo);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Inicializar
        cultivos = new ArrayList<>();

        traerCultivos();

        //Si no hay cultivos se muestra la instruccion mas el boton
        if(cultivos.size()==0){
            instruccion.setVisibility(View.VISIBLE);
            nuevoCultivo.setVisibility(View.VISIBLE);
        }else {
            //si hay cultivos se muestra la lista
            mAdapter = new RecyclerViewAdapterCultivos(cultivos);
            mAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), OpcionesCultivoActivity.class);
                    intent.putExtra("cultivo", cultivos.get(mRecyclerView.getChildAdapterPosition(v)));
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);

                    Toast.makeText(PrincipalActivity.this, "Ha pulsado el huerto " + cultivos.get(mRecyclerView.getChildAdapterPosition(v)).getIdCultivo(), Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void traerCultivos(){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM Cultivo", null);
        if (cursor.moveToFirst()) {
            do {
                System.out.println("NOMBRE COLUMNA: " + cursor.getColumnName(0) + " VALOR COLUMNA: " + cursor.getInt(0));
                cultivos.add(new Cultivo(cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4), cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    
    public void agregarCultivo(View view){
        Intent intent = new Intent(this, CrearCultivoActivity.class);
        startActivity(intent);
        Toast.makeText(PrincipalActivity.this, "Agregar nuevo cultivo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {

            if(!sManager.estaLogueado()) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }else{
                new CerrarSesionDialog().show(getSupportFragmentManager(), "");
            }

        } else if (id == R.id.nav_basico) {
            Intent intent = new Intent(this, AprenderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_lista) {
            Intent intent = new Intent(this, ListaDePlantasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_crear) {
            Intent intent = new Intent(this, CrearCultivoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tareas) {

        } else if (id == R.id.nav_respaldar) {

        }else if (id == R.id.nav_restaurar){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class CerrarSesionDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("La sesión está activa")
                    .setPositiveButton("Cerrar Sesion", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            sManager.cerrarSesion();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}
