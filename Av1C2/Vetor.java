package Av1C2;

public interface Vetor {
	
	public int getTamanho();
	public Conta getConta(int pos);
	public boolean insere(Conta conta);
	public void quickSort();
	public void shellSort();
}
