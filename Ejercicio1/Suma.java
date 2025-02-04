package Ejercicio1;

public class Suma {

	public int valor_total = 0;

	public synchronized void adicionarAcumFila(int valor) {
		valor_total += valor;
	}

	public int darTotal() {
		return valor_total;
	}

}
