package org.example.Liga;

import java.util.ArrayList;

import org.example.Liga.liga.Liga;

import com.github.javafaker.Faker;

public class VariasLigas 
{
    public static String[] generarEquipos()
	{
		String[] equipos = new String[20];
		Faker faker = new Faker();

		for (int i = 0; i < 20; i++)
		{
			equipos[i] = faker.team().name();

		}
		return equipos;
	}

	public static void simularJornadas(ArrayList<Liga> ligas, int numJornada)
	{
		for(Liga liga : ligas)
		{
			liga.menuVariasLigas(numJornada);
		}
	}

	public static void verClasificaciones(ArrayList<Liga> ligas, int numJornada)
	{
		for(Liga liga : ligas)
		{
			liga.verClasificacion(numJornada);
		}
	}

	public static void opcionesVariasLigas()
	{
		System.out.println(PrintTexto.RESET + "\nBIENVENIDO AL SIMULADOR DE LA XTART LEAGUE");
        System.out.println(PrintTexto.GREEN + "\nSELECCIONA UNA OPCIÓN PARA COMENZAR TU AVENTURA Y DISFRUTAR DE NUESTRA LIGA MIXTA");
        System.out.println(PrintTexto.YELLOW + "1. Ver clasificaciones");
        System.out.println(PrintTexto.PURPLE + "2. Simular Jornadas");
        System.out.println(PrintTexto.RED + "3. Ver equipos");
		System.out.println(PrintTexto.BLUE + "4. Salir");
        System.out.print(PrintTexto.RESET + "Elige una opción: ");
	}


	public static void verMediaEquipos(ArrayList<Liga> ligas)
	{
		for(Liga liga : ligas)
		{
			liga.verEquipos(liga.getEquipos());
		}
	}

	public static void jugarConVariasLigas()
	{
		int opcion;
		int numJornada;
		ArrayList<Liga> ligas = new ArrayList<>();

		System.out.print("Con cuantas ligas quieres jugar?: ");
		opcion = GestionNumero.gestionNumero();
		numJornada = 0;
		for (int i = 0; i < opcion; i++)
		{
			ligas.add(new Liga(generarEquipos(), false));
		}

		do {
			opcionesVariasLigas();
			opcion = GestionNumero.gestionNumero();
            switch (opcion) 
            {
                case 1:
					verClasificaciones(ligas, numJornada);
                    break;
                case 2:
					simularJornadas(ligas, numJornada);
					numJornada++;
                    break;
                case 3:
					verMediaEquipos(ligas);
				case 4:
                    break ;
                default:
                    System.out.println(PrintTexto.RED + "Opción no válida, intenta de nuevo.");
                    break ;
            }
        } while (opcion != 4);
	}

}
