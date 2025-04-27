package org.example.Liga;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import org.example.Liga.liga.Equipo;
import org.example.Liga.liga.Liga;
import com.github.javafaker.Faker;

public class Main {
    public static void opcionesLiga() 
    {
        System.out.println(PrintTexto.RESET + "\nBIENVENIDO AL SIMULADOR DE LA XTART LEAGUE");
        System.out.println(PrintTexto.GREEN + "\nSELECCIONA UNA OPCIÓN PARA COMENZAR TU AVENTURA Y DISFRUTAR DE NUESTRA LIGA MIXTA");
        System.out.println(PrintTexto.YELLOW + "1. Jugar con una liga");
        System.out.println(PrintTexto.BLUE + "2. Jugar con varias ligas");
        System.out.println(PrintTexto.RED + "3. Jugar mundial");
        System.out.println(PrintTexto.PURPLE + "4. Salir");
        System.out.print(PrintTexto.RESET + "Elige una opción: ");
    }

    public static void main(String[] args) 
    {
        String[] equipos1 = {
            "Barcelona", "Real Madrid", "Atletico de Madrid", "Sevilla", "Valencia", 
            "Villarreal", "Real Sociedad", "Betis", "Athletic Bilbao", "Getafe", 
            "Espanyol", "Osasuna", "Alavés", "Levante", "Celta de Vigo", 
            "Granada", "Cadiz", "Elche", "Mallorca", "Rayo Vallecano"};
        String[] equiposMundial = {
            "EUROPA", "ASIA", "NORTEAMERICA", "SUDAMERICA" ,"AFRICA", "OCEANIA"};
        int opcion;

        do {
            opcionesLiga();
            opcion = GestionNumero.gestionNumero();
            switch (opcion) {
                case 1:
                    System.out.println(PrintTexto.GREEN + "Iniciando la liga..." + PrintTexto.BLUE);
                    Liga liga = new Liga(equipos1, false);
                    liga.menuLiga();
                    break;
                case 2:
                    System.out.println(PrintTexto.GREEN + "Iniciando varias ligas..." + PrintTexto.BLUE);
                    VariasLigas.jugarConVariasLigas();
                    break;
                case 3:
                    System.out.println("Jugando Mundial...");
                    Liga ligaMundial = new Liga(equiposMundial, true);
                    ligaMundial.menuLiga();
                    ligaMundial.eliminatorias();
                    break ;
                case 4:
                    System.out.println(PrintTexto.RED + "Saliendo del simulador. ¡Hasta la próxima!" + PrintTexto.YELLOW);
                    break;
                default:
                    System.out.println(PrintTexto.RED + "Opción no válida, intenta de nuevo." + PrintTexto.CYAN);
                    break;
            }
        } while (opcion != 4);
    }
}
