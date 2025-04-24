package com.example.gestionempleados.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gestionempleados.R;

public class DetalleEmpleadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);

        // Obtener datos del intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Datos generales del empleado
            String id = extras.getString("EMPLEADO_ID", "");
            String nombre = extras.getString("EMPLEADO_NOMBRE", "");
            String apellido = extras.getString("EMPLEADO_APELLIDO", "");
            double salario = extras.getDouble("EMPLEADO_SALARIO", 0.0);
            String fecha = extras.getString("EMPLEADO_FECHA", "");
            String tipo = extras.getString("EMPLEADO_TIPO", "");

            // Actualizar la UI con los datos generales
            TextView tvNombreCompleto = findViewById(R.id.tvNombreCompleto);
            TextView tvTipoEmpleado = findViewById(R.id.tvTipoEmpleado);
            TextView tvId = findViewById(R.id.tvId);
            TextView tvFechaContratacion = findViewById(R.id.tvFechaContratacion);
            TextView tvSalarioBase = findViewById(R.id.tvSalarioBase);

            tvNombreCompleto.setText(nombre + " " + apellido);
            tvTipoEmpleado.setText(tipo);
            tvId.setText(id);
            tvFechaContratacion.setText(fecha);
            tvSalarioBase.setText("$" + String.format("%.2f", salario));

            // Contenedor para datos específicos
            LinearLayout contenedorEspecifico = findViewById(R.id.contenedorEspecifico);

            // Según el tipo de empleado, mostrar información específica
            if (tipo.equals("Gerente")) {
                String departamento = extras.getString("GERENTE_DEPARTAMENTO", "");
                double bonoAnual = extras.getDouble("GERENTE_BONO", 0.0);
                int subordinados = extras.getInt("GERENTE_SUBORDINADOS", 0);

                // Inflar vista de gerente
                View gerenteView = LayoutInflater.from(this).inflate(R.layout.detalle_gerente, contenedorEspecifico, false);

                // Actualizar datos específicos
                TextView tvDepartamento = gerenteView.findViewById(R.id.tvDepartamento);
                TextView tvBonoAnual = gerenteView.findViewById(R.id.tvBonoAnual);
                TextView tvSubordinados = gerenteView.findViewById(R.id.tvSubordinados);

                tvDepartamento.setText(departamento);
                tvBonoAnual.setText("$" + String.format("%.2f", bonoAnual));
                tvSubordinados.setText(String.valueOf(subordinados));

                contenedorEspecifico.addView(gerenteView);

            } else if (tipo.equals("Tecnico") || tipo.equals("TecnicoSenior")) {
                String especialidad = extras.getString("TECNICO_ESPECIALIDAD", "");
                String nivel = extras.getString("TECNICO_NIVEL", "");
                int horas = extras.getInt("TECNICO_HORAS", 0);

                // Inflar vista de técnico
                View tecnicoView = LayoutInflater.from(this).inflate(R.layout.detalle_tecnico, contenedorEspecifico, false);

                // Actualizar datos específicos
                TextView tvEspecialidad = tecnicoView.findViewById(R.id.tvEspecialidad);
                TextView tvNivel = tecnicoView.findViewById(R.id.tvNivel);
                TextView tvHoras = tecnicoView.findViewById(R.id.tvHoras);

                tvEspecialidad.setText(especialidad);
                tvNivel.setText(nivel);
                tvHoras.setText(String.valueOf(horas));

                contenedorEspecifico.addView(tecnicoView);

                // Si es técnico senior, agregar información adicional
                if (tipo.equals("TecnicoSenior")) {
                    int proyectos = extras.getInt("TECNICO_SENIOR_PROYECTOS", 0);
                    int clientes = extras.getInt("TECNICO_SENIOR_CLIENTES", 0);

                    // Inflar vista adicional de técnico senior
                    View seniorView = LayoutInflater.from(this).inflate(R.layout.detalle_tecnico_senior, contenedorEspecifico, false);

                    // Actualizar datos específicos
                    TextView tvProyectos = seniorView.findViewById(R.id.tvProyectos);
                    TextView tvClientes = seniorView.findViewById(R.id.tvClientes);

                    tvProyectos.setText(String.valueOf(proyectos));
                    tvClientes.setText(String.valueOf(clientes));

                    contenedorEspecifico.addView(seniorView);
                }
            }
        }
    }
}