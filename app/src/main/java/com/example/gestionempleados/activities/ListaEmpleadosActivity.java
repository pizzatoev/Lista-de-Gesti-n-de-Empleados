package com.example.gestionempleados.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionempleados.R;
import com.example.gestionempleados.adapters.EmpleadosAdapter;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

import java.util.ArrayList;
import java.util.List;

public class ListaEmpleadosActivity extends AppCompatActivity implements EmpleadosAdapter.OnItemActionListener {

    private RecyclerView recyclerView;
    private EmpleadosAdapter adapter;
    private List<Empleado> empleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        recyclerView = findViewById(R.id.recyclerView);

        // Inicializar la lista de empleados
        initEmpleados();

        // Configurar el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmpleadosAdapter(empleados, this);
        recyclerView.setAdapter(adapter);
    }

    private void initEmpleados() {
        empleados = new ArrayList<>();

        // Agregar empleados de ejemplo (al menos 5 objetos, uno de cada tipo)
        empleados.add(new Gerente("G001", "Ana", "García", 5000.0, "01/01/2020",
                "Recursos Humanos", 1000.0, 5));

        empleados.add(new Gerente("G002", "Carlos", "Martínez", 5500.0, "15/03/2019",
                "Ventas", 1200.0, 8));

        empleados.add(new Tecnico("T001", "Laura", "Pérez", 3000.0, "10/06/2021",
                "Informática", "Nivel 2", 10));

        empleados.add(new Tecnico("T002", "Miguel", "López", 3200.0, "22/09/2020",
                "Electrónica", "Nivel 3", 15));

        empleados.add(new TecnicoSenior("TS001", "Pablo", "Sánchez", 4000.0, "05/04/2018",
                "Desarrollo Web", "Nivel 4", 20, 12, 25));
    }

    @Override
    public void onItemClick(Empleado empleado, int position) {
        // Navegar a la actividad de detalle
        Intent intent = new Intent(this, DetalleEmpleadoActivity.class);

        // Pasar datos del empleado
        intent.putExtra("EMPLEADO_ID", empleado.getId());
        intent.putExtra("EMPLEADO_NOMBRE", empleado.getNombre());
        intent.putExtra("EMPLEADO_APELLIDO", empleado.getApellido());
        intent.putExtra("EMPLEADO_SALARIO", empleado.getSalarioBase());
        intent.putExtra("EMPLEADO_FECHA", empleado.getFechaContratacion());

        // Determinar el tipo de empleado
        if (empleado instanceof Gerente) {
            Gerente gerente = (Gerente) empleado;
            intent.putExtra("EMPLEADO_TIPO", "Gerente");
            intent.putExtra("GERENTE_DEPARTAMENTO", gerente.getDepartamento());
            intent.putExtra("GERENTE_BONO", gerente.getBonoAnual());
            intent.putExtra("GERENTE_SUBORDINADOS", gerente.getCantidadSubordinados());
        } else if (empleado instanceof TecnicoSenior) {
            TecnicoSenior tecnicoSenior = (TecnicoSenior) empleado;
            intent.putExtra("EMPLEADO_TIPO", "TecnicoSenior");
            intent.putExtra("TECNICO_ESPECIALIDAD", tecnicoSenior.getEspecialidad());
            intent.putExtra("TECNICO_NIVEL", tecnicoSenior.getNivelCertificacion());
            intent.putExtra("TECNICO_HORAS", tecnicoSenior.getHorasExtra());
            intent.putExtra("TECNICO_SENIOR_PROYECTOS", tecnicoSenior.getProyectosCompletados());
            intent.putExtra("TECNICO_SENIOR_CLIENTES", tecnicoSenior.getClientesAtendidos());
        } else if (empleado instanceof Tecnico) {
            Tecnico tecnico = (Tecnico) empleado;
            intent.putExtra("EMPLEADO_TIPO", "Tecnico");
            intent.putExtra("TECNICO_ESPECIALIDAD", tecnico.getEspecialidad());
            intent.putExtra("TECNICO_NIVEL", tecnico.getNivelCertificacion());
            intent.putExtra("TECNICO_HORAS", tecnico.getHorasExtra());
        }

        startActivity(intent);
    }

    @Override
    public void onActionButtonClick(Empleado empleado, int position) {
        // Mostrar información básica del empleado
        String mensaje = "ID: " + empleado.getId() + ", " + empleado.getNombre() + " " + empleado.getApellido();
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}