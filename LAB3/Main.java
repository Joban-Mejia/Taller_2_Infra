package LAB3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Fila fila = new Fila();
        GeneradorClientes generadorClientes = new GeneradorClientes(fila, 20);
        ArrayList<Cajero> cajeros = new ArrayList<Cajero>();

        for (int i = 1; i < 6; i++) {
        	Cajero cajero = new Cajero(i, fila);
            cajeros.add(cajero);
            cajero.start();
        }

        generadorClientes.start();

        try {
        	generadorClientes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (Cajero cajero: cajeros) {
        	try {
				cajero.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }

        System.out.println("Simulación terminada.");
    }
}