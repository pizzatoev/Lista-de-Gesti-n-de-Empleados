package com.example.gestionempleados.models;

public class TecnicoSenior extends Tecnico {
    private int proyectosCompletados;
    private int clientesAtendidos;

    public TecnicoSenior(String id, String nombre, String apellido, double salarioBase, String fechaContratacion,
                         String especialidad, String nivelCertificacion, int horasExtra,
                         int proyectosCompletados, int clientesAtendidos) {
        super(id, nombre, apellido, salarioBase, fechaContratacion, especialidad, nivelCertificacion, horasExtra);
        this.proyectosCompletados = proyectosCompletados;
        this.clientesAtendidos = clientesAtendidos;
    }

    // Getters y Setters
    public int getProyectosCompletados() {
        return proyectosCompletados;
    }

    public void setProyectosCompletados(int proyectosCompletados) {
        this.proyectosCompletados = proyectosCompletados;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(int clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }
}