package org.example.Liga.personas;

import org.example.Liga.enumLiga.Continente;

public class Entrenador extends Persona 
{
    private int experiencia;


    public Entrenador(String nombre, String apellido, Continente continente, int experiencia)
    {
        super(nombre, apellido, continente);
        this.experiencia = experiencia;

    }
}