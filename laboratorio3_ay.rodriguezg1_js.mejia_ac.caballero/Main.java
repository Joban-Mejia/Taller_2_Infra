public class Main {
    public static void main(String[] args) {
        Fila fila = new Fila();
        GeneradorClientes generador = new GeneradorClientes(fila);
        Cajero[] cajeros = new Cajero[5];

        // Crear e iniciar los cajeros
        for (int i = 0; i < cajeros.length; i++) {
            cajeros[i] = new Cajero(i + 1, fila);
            new Thread(cajeros[i]).start();
        }

        // Iniciar el generador de clientes
        Thread generadorThread = new Thread(generador);
        generadorThread.start();

        // Esperar a que el generador termine
        try {
            generadorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulación terminada.");
    }
}