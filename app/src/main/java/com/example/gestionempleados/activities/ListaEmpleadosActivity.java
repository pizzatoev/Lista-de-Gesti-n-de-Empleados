package com.example.gestionempleados.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestionempleados.R;
import com.example.gestionempleados.adapters.EmpleadosAdapter;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaEmpleadosActivity extends AppCompatActivity implements EmpleadosAdapter.OnItemActionListener {

    private RecyclerView recyclerView;
    private EmpleadosAdapter adapter;
    private List<Empleado> empleados;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
    private static final String API_URL = "https://raw.githubusercontent.com/adancondori/TareaAPI/refs/heads/main/api/empleados.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        // Inicializar Volley Request Queue
        requestQueue = Volley.newRequestQueue(this);

        // Inicializar la lista de empleados
        empleados = new ArrayList<>();

        // Configurar el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmpleadosAdapter(empleados, this);
        recyclerView.setAdapter(adapter);

        // Cargar datos desde la API
        cargarEmpleadosDesdeAPI();
    }

    private void cargarEmpleadosDesdeAPI() {
        // Mostrar ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        // Limpiar lista actual si es necesario
        empleados.clear();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> {
                    try {
                        // Verificar que la respuesta sea exitosa
                        String status = response.getString("status");
                        if (status.equals("ok")) {
                            // Obtener array de empleados
                            JSONArray empleadosArray = response.getJSONArray("empleados");

                            // Procesar cada empleado
                            for (int i = 0; i < empleadosArray.length(); i++) {
                                JSONObject empleadoJson = empleadosArray.getJSONObject(i);
                                procesarEmpleado(empleadoJson);
                            }

                            // Notificar al adaptador sobre los cambios
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(this, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                        }

                        // Ocultar ProgressBar
                        progressBar.setVisibility(View.GONE);

                    } catch (JSONException e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Error al procesar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Error de red: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );

        // Añadir solicitud a la cola
        requestQueue.add(request);
    }

    private void procesarEmpleado(JSONObject empleadoJson) throws JSONException {
        int id = empleadoJson.getInt("id");
        String nombre = empleadoJson.getString("nombre");
        String apellido = empleadoJson.getString("apellido");
        double salarioBase = empleadoJson.getDouble("salarioBase");
        String fechaContratacion = empleadoJson.getString("fechaContratacion");
        String tipo = empleadoJson.getString("tipo");

        // Crear empleado según su tipo
        switch (tipo) {
            case "Gerente":
                String departamento = empleadoJson.getString("departamento");
                double bonoAnual = empleadoJson.getDouble("bonoAnual");
                int cantidadSubordinados = empleadoJson.getInt("cantidadSubordinados");

                empleados.add(new Gerente(
                        String.valueOf(id),
                        nombre,
                        apellido,
                        salarioBase,
                        fechaContratacion,
                        departamento,
                        bonoAnual,
                        cantidadSubordinados
                ));
                break;

            case "Tecnico":
                String especialidadTecnico = empleadoJson.getString("especialidad");
                String nivelCertificacionTecnico = empleadoJson.getString("nivelCertificacion");
                int horasExtraTecnico = empleadoJson.getInt("horasExtra");

                empleados.add(new Tecnico(
                        String.valueOf(id),
                        nombre,
                        apellido,
                        salarioBase,
                        fechaContratacion,
                        especialidadTecnico,
                        nivelCertificacionTecnico,
                        horasExtraTecnico
                ));
                break;

            case "TecnicoSenior":
                String especialidadSenior = empleadoJson.getString("especialidad");
                String nivelCertificacionSenior = empleadoJson.getString("nivelCertificacion");
                int horasExtraSenior = empleadoJson.getInt("horasExtra");
                int proyectosCompletados = empleadoJson.getInt("proyectosCompletados");
                int clientesAtendidos = empleadoJson.getInt("clientesAtendidos");

                empleados.add(new TecnicoSenior(
                        String.valueOf(id),
                        nombre,
                        apellido,
                        salarioBase,
                        fechaContratacion,
                        especialidadSenior,
                        nivelCertificacionSenior,
                        horasExtraSenior,
                        proyectosCompletados,
                        clientesAtendidos
                ));
                break;
        }
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