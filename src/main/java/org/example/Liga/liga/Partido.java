package org.example.Liga.liga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.example.Liga.PrintTexto;
import org.example.Liga.personas.Jugador;

public class Partido
{
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int[] resultado;
    private int numeroGoles;
    private int paradasPorteroLocal;
    private int paradasPorteroVisitante;

    public Partido(Equipo equipoLocal, Equipo equipoVisitante)
    {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }
    
    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int[] resultado, int numeroGoles) 
    {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.resultado = resultado;
        this.numeroGoles = numeroGoles;
        this.paradasPorteroLocal = 0;
        this.paradasPorteroVisitante = 0;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }
    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }
    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }
    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }
    public int[] getResultado() 
    {
        return resultado;
    }
    public void setResultado(int[] resultado) 
    {
        this.resultado = resultado;
    }
    public int getNumeroGoles() 
    {
        return numeroGoles;
    }
    public void setNumeroGoles(int numeroGoles) 
    {
        this.numeroGoles = numeroGoles;
    }

    public void simularPartido() 
    {
        Random ran = new Random();
        int golesEquipo1 = 0;
        int golesEquipo2 = 0;
        int minutoActual = 0;
        List<String> eventos = new ArrayList<>();
        
        int[] tarjetasAmarillasLocal = new int[11];
        int[] tarjetasRojasLocal = new int[11];
        int[] tarjetasAmarillasVisitante = new int[11];
        int[] tarjetasRojasVisitante = new int[11];

        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");
        System.out.println("BIENVENIDOS AL PARTIDO ENTRE " + this.equipoLocal.getNombre() + " vs " + this.equipoVisitante.getNombre());
        System.out.println("---------------------------------------------");
        System.out.println("---------------------------------------------");
        
        for (int i = 0; i < 5; i++) 
        {
            if (this.equipoLocal.getMediaStats() > this.equipoVisitante.getMediaStats()) 
            {
                golesEquipo1 = simularGolMayorMedia(minutoActual, golesEquipo1, this.equipoLocal, eventos, ran, true);
                golesEquipo2 = simularGolMenorMedia(minutoActual, golesEquipo2, this.equipoVisitante, eventos, ran, false);
            } 
            else 
            {
                golesEquipo2 = simularGolMayorMedia(minutoActual, golesEquipo2, this.equipoLocal, eventos, ran, false);
                golesEquipo1 = simularGolMenorMedia(minutoActual, golesEquipo1, this.equipoVisitante, eventos, ran, true);
            }
            simularTarjetas(minutoActual, tarjetasAmarillasLocal, tarjetasRojasLocal, tarjetasAmarillasVisitante, tarjetasRojasVisitante, eventos, ran);
        }
        
        mostrarEventos(eventos);
        mostrarMarcador(golesEquipo1, golesEquipo2);
        mostrarParadasPorteros();
        mostrarAmonestados(tarjetasAmarillasLocal, tarjetasRojasLocal, tarjetasAmarillasVisitante, tarjetasRojasVisitante);
        actualizarPuntos(golesEquipo1, golesEquipo2);
        
    }

    public int simularPenaltis(int index1, int index2)
    {
        int goles1 = 0;
        int dado;
        int goles2 = 0;
        Random ran = new Random();
        System.out.println("------------------");
        System.out.println("EMPIEZAN LOS PENALTIS");
        while (goles1 < 5 && goles2 < 5)
        {
            dado = ran.nextInt(10);
            if (dado <=5)
            {
                System.out.println("------------------");
                System.out.println("GOOOOOOOOL DE " + this.equipoLocal.getNombre());
                System.out.println("------------------");

                goles1++;
            }
            else
            {
                System.out.println("----------------");
                System.out.println("FALLO DE " + this.equipoLocal.getNombre());
                System.out.println("------------------");

            }
            System.out.println("---------------------");
            System.out.println(this.equipoLocal.getNombre() + " " + goles1 + " - " + goles2 + " " + this.equipoVisitante.getNombre());
            dado = ran.nextInt(10);
            if (goles1 !=5)
            {
                if (dado <=5)
                {
                    System.out.println("------------------");
                    System.out.println("GOOOOOOOOL DE " + this.equipoVisitante.getNombre());
                    System.out.println("------------------");

                    goles2++;
                }
                else
                {
                    System.out.println("----------------");
                    System.out.println("FALLO DE " + this.equipoVisitante.getNombre());
                    System.out.println("------------------");

                }
                System.out.println(this.equipoLocal.getNombre() + " " + goles1 + " - " + goles2 + " " + this.equipoVisitante.getNombre());
            }
        }
        System.out.println("-------------------------");
        if (goles1 == 5)
        {
            System.out.println("EL GANADOR ES: " + this.equipoLocal.getNombre());
            return (index1);

        }
        else
        {
            System.out.println("EL GANADOR ES: " + this.equipoVisitante.getNombre());
            return (index2);
        }
        
    }

    public int simularGolMayorMedia(int minutoActual, int golesEquipo, Equipo equipo, List<String> eventos, Random ran, boolean esLocal) 
    {
        int dado = ran.nextInt(10);
        if (dado <= 7 && minutoActual < 90) 
        {
            minutoActual += ran.nextInt(1, 24);
            if (minutoActual > 90) return minutoActual;
            if (porteroAtaja(ran, esLocal)) 
            {
                return golesEquipo;
            }
            
            golesEquipo++;
            int marcarGol = ran.nextInt(1, 11);
            Jugador goleador = equipo.getJugadores()[marcarGol - 1]; 
            equipo.getJugadores()[marcarGol-1].golNuevo();
            eventos.add(minutoActual + ": Gol del " + equipo.getNombre() + " (" + goleador.getNombre() + " " + goleador.getApellido() + ")");
            
            if (ran.nextInt(10) <= 7) 
            {
                int darAsistencia;
                do 
                {
                    darAsistencia = ran.nextInt(1, 11);
                } 
                while (darAsistencia == marcarGol);            
                Jugador asistente = equipo.getJugadores()[darAsistencia - 1]; 
                equipo.getJugadores()[darAsistencia-1].asistencia(); 
                eventos.add(minutoActual + ": Asistencia del " + equipo.getNombre() + " (" + asistente.getNombre() + " " + asistente.getApellido() + ")");
            }
        }
        return golesEquipo;
    }

    public int simularGolMenorMedia(int minutoActual, int golesEquipo, Equipo equipo, List<String> eventos, Random ran, boolean esLocal) 
    {
        int dado = ran.nextInt(10);
        if (dado <= 4 && minutoActual < 90) 
        {
            minutoActual += ran.nextInt(1, 90);
            if (minutoActual > 90) return minutoActual;
            if (porteroAtaja(ran, esLocal)) 
            {
                return golesEquipo;
            }
            
            golesEquipo++;
            int marcarGol = ran.nextInt(1, 11);
            Jugador goleador = equipo.getJugadores()[marcarGol - 1]; 
            equipo.getJugadores()[marcarGol-1].golNuevo();
            eventos.add(minutoActual + ": Gol del " + equipo.getNombre() + " (" + goleador.getNombre() + " " + goleador.getApellido() + ")");
            
            if (ran.nextInt(10) <= 7) 
            {
                int darAsistencia;
                do 
                {
                    darAsistencia = ran.nextInt(1, 11);
                } 
                while (darAsistencia == marcarGol);
                Jugador asistente = equipo.getJugadores()[darAsistencia - 1];
                equipo.getJugadores()[darAsistencia-1].asistencia(); 
                eventos.add(minutoActual + ": Asistencia del " + equipo.getNombre() + " (" + asistente.getNombre() + " " + asistente.getApellido() + ")");
            }
        }
        return golesEquipo;
    }

    public void simularTarjetas(int minutoActual, int[] tarjetasAmarillasLocal, int[] tarjetasRojasLocal,int[] tarjetasAmarillasVisitante, int[] tarjetasRojasVisitante, List<String> eventos, Random ran) 
    {
        if (ran.nextInt(10) < 3 && minutoActual < 90) 
        {
            int jugador = ran.nextInt(0, 11);
            boolean esLocal = ran.nextBoolean();
            int minutoTarjeta = Math.min(minutoActual + ran.nextInt(1, 5), 90);
            
            if (esLocal) 
            {
                gestionarTarjeta(jugador, this.equipoLocal, tarjetasAmarillasLocal, tarjetasRojasLocal, minutoTarjeta, eventos);
            } 
            else 
            {
                gestionarTarjeta(jugador, this.equipoVisitante, tarjetasAmarillasVisitante, tarjetasRojasVisitante, minutoTarjeta, eventos);
            }
        }
    }

    public void gestionarTarjeta(int jugador, Equipo equipo, int[] tarjetasAmarillas, int[] tarjetasRojas, int minutoTarjeta, List<String> eventos) 
    {
        if (tarjetasRojas[jugador] == 0) 
        {
            tarjetasAmarillas[jugador]++;
            Jugador jugadorTarjeta = equipo.getJugadores()[jugador];
            equipo.getJugadores()[jugador].amarilla();  
            eventos.add(minutoTarjeta + ": Tarjeta amarilla para " + equipo.getNombre() + " (" + jugadorTarjeta.getNombre() + " " + jugadorTarjeta.getApellido() + ")");

            
            if (tarjetasAmarillas[jugador] == 2) 
            {
                tarjetasRojas[jugador] = 1;
                equipo.getJugadores()[jugador].roja();  
                eventos.add(minutoTarjeta + ": ¡Expulsión! Tarjeta roja para " + equipo.getNombre() + " (" + jugadorTarjeta.getNombre() + " " + jugadorTarjeta.getApellido() + ")");

            }
        }
    }
    
    public boolean porteroAtaja(Random ran, boolean esLocal) {
        int dadoPortero = ran.nextInt(10);
        if (dadoPortero <= 4) {
            if (esLocal) {
                this.equipoLocal.getJugadores()[0].parada();
                this.paradasPorteroLocal++;
            } else {
                this.equipoVisitante.getJugadores()[0].parada();
                this.paradasPorteroVisitante++;
            }
            return true;
        }
        return false;
    }

    public void mostrarEventos(List<String> eventos) 
    {
        eventos.sort(Comparator.comparingInt(e -> Integer.parseInt(e.split(":")[0])));
        eventos.forEach(System.out::println);
    }

    public void mostrarMarcador(int golesEquipo1, int golesEquipo2) 
    {
        System.out.println("Marcador: " + this.equipoLocal.getNombre() + " " + golesEquipo1 + " - " + golesEquipo2 + " " + this.equipoVisitante.getNombre());
    }

    public void mostrarAmonestados(int[] tarjetasAmarillasLocal, int[] tarjetasRojasLocal,int[] tarjetasAmarillasVisitante, int[] tarjetasRojasVisitante) 
    {
        System.out.println(PrintTexto.RESET + "Jugadores amonestados:");
        for (int i = 0; i < 11; i++) 
        {
            if (tarjetasAmarillasLocal[i] > 0 || tarjetasRojasLocal[i] > 0) 
            {
                Jugador jugadorLocal = this.equipoLocal.getJugadores()[i]; 
                System.out.println(jugadorLocal.getNombre() + " " + jugadorLocal.getApellido() + " del " + this.equipoLocal.getNombre() + " - Amarillas: " + tarjetasAmarillasLocal[i] + " | Rojas: " + tarjetasRojasLocal[i]);

            }
            if (tarjetasAmarillasVisitante[i] > 0 || tarjetasRojasVisitante[i] > 0) 
            {
                Jugador jugadorVisitante = this.equipoVisitante.getJugadores()[i];
                System.out.println(jugadorVisitante.getNombre() + " " + jugadorVisitante.getApellido() + " del " + this.equipoVisitante.getNombre() + " - Amarillas: " + tarjetasAmarillasVisitante[i] + " | Rojas: " + tarjetasRojasVisitante[i]);

            }
        }
    }
    public void mostrarParadasPorteros() 
    {
        System.out.println(PrintTexto.GREEN + "Resumen de porteros:");
        System.out.println(PrintTexto.RED + "Portero de " + equipoLocal.getNombre() + " realizó " + paradasPorteroLocal + " paradas.");
        System.out.println(PrintTexto.BLUE + "Portero de " + equipoVisitante.getNombre() + " realizó " + paradasPorteroVisitante + " paradas.");
    }

    public void actualizarPuntos(int golesEquipo1, int golesEquipo2) 
    {
        if (golesEquipo1 > golesEquipo2) 
        {
            this.equipoLocal.setPuntos(this.equipoLocal.getPuntos() + 3);
        } 
        else if (golesEquipo2 > golesEquipo1) 
        {
            this.equipoVisitante.setPuntos(this.equipoVisitante.getPuntos() + 3);
        } 
        else 
        {
            this.equipoLocal.setPuntos(this.equipoLocal.getPuntos() + 1);
            this.equipoVisitante.setPuntos(this.equipoVisitante.getPuntos() + 1);
        }
        this.equipoLocal.calcularGoles(golesEquipo1, golesEquipo2);
        this.equipoVisitante.calcularGoles(golesEquipo2, golesEquipo1);
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Partido other = (Partido) obj;
        
        return Objects.equals(equipoLocal, other.equipoLocal) &&
               Objects.equals(equipoVisitante, other.equipoVisitante) &&
               Arrays.equals(resultado, other.resultado) &&
               numeroGoles == other.numeroGoles;
    }
}
