package br.com.abb;

public class No<Chave extends Comparable<Chave>, Valor> implements Comparable<No<Chave, Valor>> {

	private No<Chave, Valor> pai;
	private No<Chave, Valor> esq, dir;
	private Chave chave;
	private Valor valor;
	private int nAcesso;

	public No(Chave chave, Valor valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public No<Chave, Valor> getPai() {
		return pai;
	}

	public void setPai(No<Chave, Valor> pai) {
		this.pai = pai;
	}

	public No<Chave, Valor> getEsq() {
		return esq;
	}

	public void setEsq(No<Chave, Valor> esq) {
		this.esq = esq;
	}

	public No<Chave, Valor> getDir() {
		return dir;
	}

	public void setDir(No<Chave, Valor> dir) {
		this.dir = dir;
	}

	public Chave getChave() {
		return chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public Valor getValor() {
		return valor;
	}

	public void setValor(Valor valor) {
		this.valor = valor;
	}

	public int getnAcesso() {
		return nAcesso;
	}

	public void setnAcesso(int nAcesso) {
		this.nAcesso = nAcesso;
	}

	@Override
	public int compareTo(No<Chave, Valor> o) {
		 if (this.nAcesso > o.getnAcesso()) {
	            return -1;
	        }
	        if (this.nAcesso < o.getnAcesso()) {
	            return 1;
	        }
	        return 0;
	}

}
