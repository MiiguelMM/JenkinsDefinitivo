package org.example.Liga.liga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import org.example.Liga.PrintTexto;
import org.example.Liga.enumLiga.Continente;
import org.example.Liga.enumLiga.Posicion;
import org.example.Liga.personas.Jugador;

import com.github.javafaker.Faker;

public class Equipo 
{
    private String nombre;
    private Jugador[] jugadores;
    private int golesAfavor;
    private int golesEnContra;
    private int diferenciaGoles;
    private int mediaStats;
    private int puntos;
    private Partido[] partidos;

    public Equipo()
    {

    }
    public Equipo(String nombre, Jugador[] jugadores)
    {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.mediaStats = 0;
        this.golesAfavor = 0;
        this.golesEnContra = 0;
        this.puntos = 0;
    }

    public Equipo(String nombre, Jugador[] jugadores, int golesAfavor, int golesEnContra, int diferenciaGoles, int mediaStats, int puntos, Partido[] partidos) 
    {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.golesAfavor = golesAfavor;
        this.golesEnContra = golesEnContra;
        this.diferenciaGoles = diferenciaGoles;
        this.mediaStats = 0;
        this.puntos = puntos;
        this.partidos = partidos;
    }
    
    public void marcarGoles(int jugadorMarca)
    {
        System.out.println(PrintTexto.GREEN + this.jugadores[jugadorMarca].getNombre() + " marca un gol");
        this.jugadores[jugadorMarca].golNuevo();
    }
    public void darAsistencia (int jugadorAsiste)
    {
        System.out.println(PrintTexto.GREEN + this.jugadores[jugadorAsiste].getNombre() + " ha dado la asistencia para el gol");
        this.jugadores[jugadorAsiste].asistencia();
    }

    public String getNombre() 
    {
        return nombre;
    }
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    public Jugador[] getJugadores() 
    {
        return jugadores;
    }
    public void setJugadores(Jugador[] jugadores) 
    {
        this.jugadores = jugadores;
    }
    public int getGolesAfavor() 
    {
        return golesAfavor;
    }
    public void setGolesAfavor(int golesAfavor) 
    {
        this.golesAfavor = golesAfavor;
    }
    public int getGolesEnContra() 
    {
        return golesEnContra;
    }
    public void setGolesEnContra(int golesEnContra) 
    {
        this.golesEnContra = golesEnContra;
    }
    public int getDiferenciaGoles() 
    {
        return diferenciaGoles;
    }

    
    public void setDiferenciaGoles(int diferenciaGoles) 
    {
        this.diferenciaGoles = diferenciaGoles;

    }
    public int getMediaStats() 
    {
        return mediaStats;
    }
    public void setMediaStats(int mediaStats) 
    {
        this.mediaStats = mediaStats;
    }
    public int getPuntos() 
    {
        return puntos;
    }
    public void setPuntos(int puntos) 
    {
        this.puntos = puntos;
    }
    public Partido[] getPartidos() 
    {
        return partidos;
    }
    public void setPartidos(Partido[] partidos) 
    {
        this.partidos = partidos;
    }

    public void calcularGoles(int goleFavor, int golesContra)
    {
        this.golesAfavor = this.golesAfavor + goleFavor;
        this.golesEnContra = this.golesEnContra + golesContra;
        this.diferenciaGoles = this.golesAfavor - this.golesEnContra;
    }
    
    public int calcularMediaEquipo()
    {
        Random ran = new Random();
        int media = 0;
        if (this.nombre.equals("Barcelona") || this.nombre.equals("Atletico de Madrid") || this.nombre.equals("Real Madrid"))
        {
            int dado = ran.nextInt(80, 90);
            this.mediaStats = dado;
        }
        else
        {
            for(Jugador jugadorprueba : this.jugadores)
            {
                media = media + jugadorprueba.getStats();
            }
            media = media/this.jugadores.length;
            this.mediaStats = media;
        }
        return media;
    }

    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", jugadores=" + Arrays.toString(jugadores) + ", golesAfavor=" + golesAfavor
                + ", golesEnContra=" + golesEnContra + ", diferenciaGoles=" + diferenciaGoles + ", mediaStats="
                + mediaStats + ", puntos=" + puntos + ", partidos=" + Arrays.toString(partidos) + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipo other = (Equipo) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (!Arrays.equals(jugadores, other.jugadores))
            return false;
        if (golesAfavor != other.golesAfavor)
            return false;
        if (golesEnContra != other.golesEnContra)
            return false;
        if (diferenciaGoles != other.diferenciaGoles)
            return false;
        if (mediaStats != other.mediaStats)
            return false;
        if (puntos != other.puntos)
            return false;
        if (!Arrays.equals(partidos, other.partidos))
            return false;
        return true;
    }

	public Jugador crearJugador(Posicion posicion, int numero)
	{
		Random ran = new Random();
		String firstName;
		String lastName;
		int media;
		Faker fakerEs = new Faker(new Locale("es")); //REDUCIR ESTOS 6
		Faker fakerEn = new Faker(Locale.ENGLISH);
		Faker fakerAs = new Faker(Locale.JAPAN);
		Faker fakerAF = new Faker(new Locale("en", "NG"));
		Faker fakerCN = new Faker(Locale.CANADA_FRENCH);
		Faker fakerBR = new Faker(new Locale("pt-BR"));
		int dado1;
		Jugador jugador;
		Continente continente;
        if (numero == 0)
        {
		    dado1 = ran.nextInt(6);
        }
        else
        {
            dado1 = numero;
        }
		switch (dado1) {
			case 1:
				firstName = fakerEs.name().firstName();
				lastName = fakerEs.name().lastName();
				dado1 = ran.nextInt(70,90);
				continente = Continente.EUROPA;
				break;
			case 2:
				firstName = fakerAs.name().firstName();
				lastName = fakerAs.name().lastName();
				dado1 = ran.nextInt(70,90);
				continente = Continente.ASIA;
				break;			
			case 3:
				firstName = fakerCN.name().firstName();
				lastName = fakerCN.name().lastName();
				dado1 = ran.nextInt(70,90);
				continente = Continente.NORTEAMERICA;
				break;
			case 4:
				firstName = fakerBR.name().firstName();
				lastName = fakerBR.name().lastName();
				dado1 = ran.nextInt(70,90);
				continente = Continente.SUDAMERICA;
				break;
			case 5:
				firstName = fakerAF.name().firstName();
				lastName = fakerAF.name().lastName();
				dado1 = ran.nextInt(70,90);
				continente = Continente.AFRICA;
				break;
			default:
				firstName = fakerEn.name().firstName();
				lastName = fakerEn.name().lastName();
				dado1 = ran.nextInt(70,90);
				continente = Continente.OCEANIA;
				break;
		}
		jugador = new Jugador(firstName, lastName, continente, dado1, posicion);
		return jugador;
	}

    public Equipo crearEquipo(String nombre, int numero)
	{
		int i;
		Equipo equipoPrueba;
		Jugador[] jugadores = new Jugador[11];

		i = 0;
		jugadores[i] = crearJugador(Posicion.PORTERO, numero);
		i++;
		while (i <= 4)
		{
			jugadores[i] = crearJugador(Posicion.DEFENSA, numero);
			i++;
		}
		while (i <= 7)
		{
			jugadores[i] = crearJugador(Posicion.MEDIOCAMPISTA, numero);
			i++;
		}
		while (i <= 10)
		{
			jugadores[i] = crearJugador(Posicion.DELANTERO, numero);
			i++;
		}
		equipoPrueba = new Equipo(nombre, jugadores);
		equipoPrueba.calcularMediaEquipo();

		return (equipoPrueba);
	}

}
