public class Cliente {
    private final int id;
    private final int tiempoBase;

    public Cliente(int id, int tiempoBase) {
        this.id = id;
        this.tiempoBase = tiempoBase;
    }

    public int getId() {
        return id;
    }

    public int getTiempoBase() {
        return tiempoBase;
    }
}
