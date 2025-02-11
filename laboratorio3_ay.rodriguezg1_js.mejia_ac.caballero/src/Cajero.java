public class Cajero implements Runnable {
    private int id;
    private Fila fila;
    private double factorDeCansancio = 1.0;

    public Cajero(int id, Fila fila) {
        this.id = id;
        this.fila = fila;
    }

    @Override
    public void run() {
        while (true) {
            int[] cliente = fila.retirarCliente();
            int clienteId = cliente[0];
            int tiempoBase = cliente[1];

            System.out.println("Cajero " + id + " comienza a procesar al cliente " + clienteId);

            int tiempoCalculado = (int) (tiempoBase * factorDeCansancio);
            try {
                Thread.sleep(tiempoCalculado); // Simular el tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            factorDeCansancio += 0.0001 * tiempoBase; // Aumentar el factor de cansancio

            System.out.println("Cajero " + id + " ha terminado de procesar al cliente " + clienteId +
                    " - Tiempo base: " + tiempoBase + " ms, Factor de cansancio: " + factorDeCansancio +
                    ", Tiempo calculado: " + tiempoCalculado + " ms");
        }
    }
}