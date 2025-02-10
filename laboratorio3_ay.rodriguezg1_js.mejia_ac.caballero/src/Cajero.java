public class Cajero implements Runnable {
    private final int id;
    private final Fila fila;
    private double factorDeCansancio = 1.0;

    public Cajero(int id, Fila fila) {
        this.id = id;
        this.fila = fila;
    }

    @Override
    public void run() {
        while (true) {
            Cliente cliente = fila.obtenerCliente();
            if (cliente == null) {
                break;
            }

            int tiempoBase = cliente.getTiempoBase();
            long tiempoCalculado = (long) (tiempoBase * factorDeCansancio);

            System.out.println("Cajero " + id + " procesando Cliente " + cliente.getId() +
                    " | Tiempo Base: " + tiempoBase + " ms | Factor Cansancio: " + factorDeCansancio +
                    " | Tiempo Total: " + tiempoCalculado + " ms");

            // Simular el tiempo de procesamiento
            try {
                Thread.sleep(tiempoCalculado);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Actualizar el factor de cansancio
            factorDeCansancio += (0.0001 * tiempoBase);

            System.out.println("Cajero " + id + " ha terminado con Cliente " + cliente.getId());
        }

        System.out.println("Cajero " + id + " ha finalizado su turno.");
    }
}
