public class Main {
    public static void main(String[] args) {

        Fila fila = new Fila(); // Monitor compartido
        GeneradorClientes generador = new GeneradorClientes(fila, 20); // Generador de clientes

        // Crear e iniciar los cajeros
        Thread[] cajeros = new Thread[5];
        for (int i = 0; i < 5; i++) {
            cajeros[i] = new Thread(new Cajero(i + 1, fila));
            cajeros[i].start();
        }

        // Iniciar el generador de clientes
        Thread generadorThread = new Thread(generador);
        generadorThread.start();

        // Esperar a que terminen los hilos
        try {
            generadorThread.join();
            for (Thread cajero : cajeros) {
                cajero.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulación finalizada.");
    }
}
