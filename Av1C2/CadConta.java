package Av1C2;

import java.util.ArrayList;

public class CadConta implements Vetor {
	private ArrayList<Conta> contas = new ArrayList<>();

	public CadConta() {
	}

	public int getTamanho() {
		return contas.size();
	}

	public Conta getConta(int pos) {
		return contas.get(pos);
	}

	public boolean insere(Conta conta) {
		if(contas.contains(conta)) {
			return false;
		}
		contas.add(conta);
		return true;
	}

	@Override
	public void quickSort (){
		ordena (0, this.contas.size()-1);}

	private void ordena (int esq, int dir){
		int i = esq, j = dir;
		Conta pivo;
		Conta temp;

		pivo = contas.get((esq+dir)/2);
		do {
			while (this.contas.get(i).compareTo(pivo) < 0)
				i++;
			while (this.contas.get(j).compareTo(pivo) > 0)
				j--;

			if (i <= j) {
				temp = this.contas.get(i);
				this.contas.set(i, this.contas.get(j));
				this.contas.set(j, temp);
				i++;
				j--;
			}
		} while (i <= j);
		if (esq < j)
			ordena (esq, j);
		if (dir > i)
			ordena (i, dir);
	}

	@Override
	public void shellSort (){
		int i, j, h;
		Conta temp;

		h = 1;
		do{
			h = 3*h+1;
		}while (h < this.contas.size());
		do{
			h = h/3;
			for (i=h; i < this.contas.size(); i++){
				temp = this.contas.get(i);
				j = i;
				while (this.contas.get(j-h).compareTo(temp) > 0){
					this.contas.set(j, contas.get(j-h));
					j -= h;
					if (j < h)
						break;
				}
				this.contas.set(j, temp);;
			}
		}while (h != 1);
	}

}
