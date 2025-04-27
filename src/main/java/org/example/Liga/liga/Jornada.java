package org.example.Liga.liga;

import java.util.ArrayList;

import org.example.Liga.PrintTexto;

public class Jornada 
{
    private int numJornada;
    private ArrayList<Partido> partidos = new ArrayList<>();


    public Jornada(int numJornada)
    {
        this.numJornada = numJornada;
    }

    public Jornada(int numJornada, ArrayList<Partido> partidos)
    {
        this.numJornada = numJornada;
        this.partidos = partidos;
    }

    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public void aniadirPartido(Partido partido)
    {
        this.partidos.add(partido);
    }

    public void simularJornada()
    {
        System.out.println(PrintTexto.RED + "-----------------------------------------");
        System.out.println(PrintTexto.BLUE + "-----------------------------------------");
        System.out.println(PrintTexto.GREEN + "Bienvenidos a la Jornada " + (this.numJornada));
        System.out.println(PrintTexto.CYAN + "-----------------------------------------");
        System.out.println(PrintTexto.PURPLE + "-----------------------------------------");
        for (Partido partido : this.partidos)
        {
            partido.simularPartido();
        }
    }
}
