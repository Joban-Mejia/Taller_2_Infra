package Ejercicio2;

import java.util.Random;

class Estacionamiento {
    private int espaciosDisponibles;
    private final int capacidad;

    public Estacionamiento(int capacidad) {
        this.capacidad = capacidad;
        this.espaciosDisponibles = capacidad;
    }

    public synchronized boolean entrar() {
        if (espaciosDisponibles > 0) {
            espaciosDisponibles--;
            return true;
        }
        return false;
    }

    public synchronized void salir() {
        if (espaciosDisponibles < capacidad) {
            espaciosDisponibles++;
        }
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }
}

class Auto extends Thread {
    private Estacionamiento estacionamiento;
    private int id;

    public Auto(int id, Estacionamiento estacionamiento) {
        this.id = id;
        this.estacionamiento = estacionamiento;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Auto " + id + " llega al estacionamiento.");


        // Espera activa hasta que pueda entrar
        while (!estacionamiento.entrar()) {
            System.out.println("Auto " + id + " intenta estacionar pero el estacionamiento está lleno. Reintentando...");
            try {
                Thread.sleep(10000); // Espera semi-activa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Auto " + id + " ha estacionado exitosamente. Espacios disponibles: " + estacionamiento.getEspaciosDisponibles());

        // Simula el tiempo que el auto está estacionado
        try {
            Thread.sleep(random.nextInt(5000) + 1000); // Entre 1 y 6 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // El auto sale del estacionamiento
        estacionamiento.salir();
        System.out.println("Auto " + id + " ha salido del estacionamiento. Espacios disponibles: " + estacionamiento.getEspaciosDisponibles());
    }
}

public class Main {
    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento(10);
        int cantidadAutos = 40;

        for (int i = 1; i <= cantidadAutos; i++) {
            Auto auto = new Auto(i, estacionamiento);
            auto.start();
        }
    }
}