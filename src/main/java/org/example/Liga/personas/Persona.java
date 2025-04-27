package org.example.Liga.personas;

import org.example.Liga.enumLiga.Continente;

public class Persona 
{
    private String nombre;
    private String apellido;
    private Continente continente;

    public Persona(String nombre, String apellido, Continente continente)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.continente = continente;
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

    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", continente=" + continente + "]";
    }



    

}
