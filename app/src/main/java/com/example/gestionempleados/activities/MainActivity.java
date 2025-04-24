package com.example.gestionempleados.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionempleados.R;
import com.example.gestionempleados.adapters.MenuAdapter;
import com.example.gestionempleados.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridViewMenu;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar GridView
        gridViewMenu = findViewById(R.id.gridViewMenu);

        // Crear elementos del menú
        initMenuItems();

        // Configurar adaptador
        menuAdapter = new MenuAdapter(this, menuItems);
        gridViewMenu.setAdapter(menuAdapter);

        // Configurar evento de clic
        gridViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Manejar la selección del menú
                handleMenuSelection(position);
            }
        });
    }

    private void initMenuItems() {
        menuItems = new ArrayList<>();

        // Agregar opciones del menú
        menuItems.add(new MenuItem("Lista de Empleados", android.R.drawable.ic_menu_myplaces));
        // Puedes agregar más opciones si lo deseas
    }

    private void handleMenuSelection(int position) {
        Intent intent = null;

        switch (position) {
            case 0: // Lista de Empleados
                intent = new Intent(this, ListaEmpleadosActivity.class);
                break;
            default:
                Toast.makeText(this, "Opción no implementada aún", Toast.LENGTH_SHORT).show();
                return;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}