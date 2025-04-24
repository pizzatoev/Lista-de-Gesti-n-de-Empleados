package com.example.gestionempleados.models;

public class Gerente extends Empleado {
    private String departamento;
    private double bonoAnual;
    private int cantidadSubordinados;

    public Gerente(String id, String nombre, String apellido, double salarioBase, String fechaContratacion,
                   String departamento, double bonoAnual, int cantidadSubordinados) {
        super(id, nombre, apellido, salarioBase, fechaContratacion);
        this.departamento = departamento;
        this.bonoAnual = bonoAnual;
        this.cantidadSubordinados = cantidadSubordinados;
    }

    // Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getBonoAnual() {
        return bonoAnual;
    }

    public void setBonoAnual(double bonoAnual) {
        this.bonoAnual = bonoAnual;
    }

    public int getCantidadSubordinados() {
        return cantidadSubordinados;
    }

    public void setCantidadSubordinados(int cantidadSubordinados) {
        this.cantidadSubordinados = cantidadSubordinados;
    }
}