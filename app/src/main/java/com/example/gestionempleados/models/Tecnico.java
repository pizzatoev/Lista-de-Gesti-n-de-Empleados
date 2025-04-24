package com.example.gestionempleados.models;


public class Tecnico extends Empleado {
    private String especialidad;
    private String nivelCertificacion;
    private int horasExtra;

    public Tecnico(String id, String nombre, String apellido, double salarioBase, String fechaContratacion,
                   String especialidad, String nivelCertificacion, int horasExtra) {
        super(id, nombre, apellido, salarioBase, fechaContratacion);
        this.especialidad = especialidad;
        this.nivelCertificacion = nivelCertificacion;
        this.horasExtra = horasExtra;
    }

    // Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNivelCertificacion() {
        return nivelCertificacion;
    }

    public void setNivelCertificacion(String nivelCertificacion) {
        this.nivelCertificacion = nivelCertificacion;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }
}
