package com.example.gestionempleados.models;

import java.util.List;
import java.util.ArrayList;

public class Empleado {
    protected String id;
    protected String nombre;
    protected String apellido;
    protected double salarioBase;
    protected String fechaContratacion;
    // Información adicional
    protected int esperanzaVida;
    protected List<String> nombresDatos;
    protected List<String> valoresDatos;

    public Empleado(String id, String nombre, String apellido, double salarioBase, String fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salarioBase = salarioBase;
        this.fechaContratacion = fechaContratacion;
        this.nombresDatos = new ArrayList<>();
        this.valoresDatos = new ArrayList<>();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public int getEsperanzaVida() {
        return esperanzaVida;
    }

    public void setEsperanzaVida(int esperanzaVida) {
        this.esperanzaVida = esperanzaVida;
    }

    public List<String> getNombresDatos() {
        return nombresDatos;
    }

    public void setNombresDatos(List<String> nombresDatos) {
        this.nombresDatos = nombresDatos;
    }

    public void addDato(String nombre, String valor) {
        this.nombresDatos.add(nombre);
        this.valoresDatos.add(valor);
    }

    public List<String> getValoresDatos() {
        return valoresDatos;
    }

    public void setValoresDatos(List<String> valoresDatos) {
        this.valoresDatos = valoresDatos;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}