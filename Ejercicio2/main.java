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

        /* Espera activa hasta que pueda entrar -- Implementación 1
        while (!estacionamiento.entrar()) {
            System.out
                    .println("Auto " + id + " intenta estacionar pero el estacionamiento está lleno. Reintentando...");
            try {
                Thread.sleep(10000); // Espera semi-activa
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } */

        // Espera semi-activa usando yield() -- Implementación 2
        while (!estacionamiento.entrar()) {
            System.out
                    .println("Auto " + id + " intenta estacionar pero el estacionamiento está lleno. Reintentando...");
            Thread.yield();
        }

        System.out.println("Auto " + id + " ha estacionado exitosamente. Espacios disponibles: "
                + estacionamiento.getEspaciosDisponibles());

        // Simula el tiempo que el auto está estacionado
        try {
            Thread.sleep(random.nextInt(5000) + 1000); // Entre 1 y 6 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // El auto sale del estacionamiento
        estacionamiento.salir();
        System.out.println("Auto " + id + " ha salido del estacionamiento. Espacios disponibles: "
                + estacionamiento.getEspaciosDisponibles());
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

    /*
        Observaciones sobre las diferencias en la salida:

        Implementación 1 (espera activa con sleep(10000)):
        Los autos que encuentran el estacionamiento lleno esperan 10 segundos antes de intentar entrar de nuevo.
        Entonces, hay periodos donde no se registran intentos de estacionamiento, por este motivo tardan más en estacionar ya que el reintento es fijo.

        - Implementación 2 (espera semi-activa con yield):
        Los autos reintentan estacionar constantemente, ya que los Threads no estan bloqueados y no tienen un tiempo de espera definido, lo que ocaciona mensajes más frecuentes.
        de "Auto X intenta estacionar..." en la consola, mostrando intentos más frecuentes.
        Los autos aprovechan los espacios disponibles más rápidamente, reduciendo el tiempo de espera.
        En conclusión, la utilización de `yield()` resulta más eficiente en el uso de los espacios del estacionamiento.
     */
    }
}