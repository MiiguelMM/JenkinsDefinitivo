package org.example.Liga;

import java.util.Scanner;

public class GestionNumero 
{
    public static int gestionNumero()
    {
        int eleccion = 0;
        String opcion;
        Boolean correcto = false;
        Scanner teclado;

        teclado = new Scanner(System.in);
        do
        {
            opcion = teclado.next();
            try {
                eleccion = Integer.parseInt(opcion);
                correcto = true;
            } 
            catch (NumberFormatException nfe) 
            {
                System.out.println("Elección incorrecta. Por favor escriba un numero");
                correcto = false;
            }
        }
        while (!correcto);
        
        return (eleccion);
    }
}
