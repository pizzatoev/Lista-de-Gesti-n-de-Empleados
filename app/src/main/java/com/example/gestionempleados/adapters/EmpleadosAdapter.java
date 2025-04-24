package com.example.gestionempleados.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionempleados.R;
import com.example.gestionempleados.models.Empleado;
import com.example.gestionempleados.models.Gerente;
import com.example.gestionempleados.models.Tecnico;
import com.example.gestionempleados.models.TecnicoSenior;

import java.util.List;

public class EmpleadosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_GERENTE = 1;
    private static final int VIEW_TYPE_TECNICO = 2;
    private static final int VIEW_TYPE_TECNICO_SENIOR = 3;

    private List<Empleado> empleados;
    private OnItemActionListener listener;

    public interface OnItemActionListener {
        void onItemClick(Empleado empleado, int position);
        void onActionButtonClick(Empleado empleado, int position);
    }

    public EmpleadosAdapter(List<Empleado> empleados, OnItemActionListener listener) {
        this.empleados = empleados;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Empleado empleado = empleados.get(position);
        if (empleado instanceof TecnicoSenior) {
            return VIEW_TYPE_TECNICO_SENIOR;
        } else if (empleado instanceof Tecnico) {
            return VIEW_TYPE_TECNICO;
        } else if (empleado instanceof Gerente) {
            return VIEW_TYPE_GERENTE;
        }
        return -1; // Nunca debería llegar aquí
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_GERENTE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gerente, parent, false);
            return new GerenteViewHolder(view);
        } else if (viewType == VIEW_TYPE_TECNICO) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_tecnico, parent, false);
            return new TecnicoViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_tecnico_senior, parent, false);
            return new TecnicoSeniorViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Empleado empleado = empleados.get(position);

        if (holder instanceof GerenteViewHolder) {
            bindGerenteViewHolder((GerenteViewHolder) holder, (Gerente) empleado, position);
        } else if (holder instanceof TecnicoViewHolder) {
            if (holder instanceof TecnicoSeniorViewHolder) {
                bindTecnicoSeniorViewHolder((TecnicoSeniorViewHolder) holder, (TecnicoSenior) empleado, position);
            } else {
                bindTecnicoViewHolder((TecnicoViewHolder) holder, (Tecnico) empleado, position);
            }
        }
    }

    private void bindGerenteViewHolder(GerenteViewHolder holder, Gerente gerente, int position) {
        holder.tvNombre.setText(gerente.getNombre() + " " + gerente.getApellido());
        holder.tvDepartamento.setText("Departamento: " + gerente.getDepartamento());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(gerente, position);
            }
        });

        holder.btnAction.setOnClickListener(v -> {
            if (listener != null) {
                listener.onActionButtonClick(gerente, position);
            }
        });
    }

    private void bindTecnicoViewHolder(TecnicoViewHolder holder, Tecnico tecnico, int position) {
        holder.tvNombre.setText(tecnico.getNombre() + " " + tecnico.getApellido());
        holder.tvEspecialidad.setText("Especialidad: " + tecnico.getEspecialidad());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(tecnico, position);
            }
        });

        holder.btnAction.setOnClickListener(v -> {
            if (listener != null) {
                listener.onActionButtonClick(tecnico, position);
            }
        });
    }

    private void bindTecnicoSeniorViewHolder(TecnicoSeniorViewHolder holder, TecnicoSenior tecnicoSenior, int position) {
        holder.tvNombre.setText(tecnicoSenior.getNombre() + " " + tecnicoSenior.getApellido());
        holder.tvEspecialidad.setText("Especialidad: " + tecnicoSenior.getEspecialidad());
        holder.tvProyectos.setText("Proyectos: " + tecnicoSenior.getProyectosCompletados());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(tecnicoSenior, position);
            }
        });

        holder.btnAction.setOnClickListener(v -> {
            if (listener != null) {
                listener.onActionButtonClick(tecnicoSenior, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }

    // ViewHolders
    public static class GerenteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDepartamento;
        ImageView ivImage;
        ImageButton btnAction;

        public GerenteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            ivImage = itemView.findViewById(R.id.ivImage);
            btnAction = itemView.findViewById(R.id.btnAction);
        }
    }

    public static class TecnicoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvEspecialidad;
        ImageView ivImage;
        ImageButton btnAction;

        public TecnicoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEspecialidad = itemView.findViewById(R.id.tvEspecialidad);
            ivImage = itemView.findViewById(R.id.ivImage);
            btnAction = itemView.findViewById(R.id.btnAction);
        }
    }

    public static class TecnicoSeniorViewHolder extends TecnicoViewHolder {
        TextView tvProyectos;

        public TecnicoSeniorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProyectos = itemView.findViewById(R.id.tvProyectos);
        }
    }
}