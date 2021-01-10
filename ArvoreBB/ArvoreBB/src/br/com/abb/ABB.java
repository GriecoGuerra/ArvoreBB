package br.com.abb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ABB<Chave extends Comparable<Chave>, Valor> implements IArvoreBB<Chave, Valor> {

	private No<Chave, Valor> raiz;
	private int cont = 0;

	@Override
	public void insercao(Chave chave, Valor valor) {
		No<Chave, Valor> novoNo = new No<>(chave, valor);
		if (this.raiz == null) {
			this.raiz = novoNo;
			cont++;
			return;
		} else {
			No<Chave, Valor> pai = raiz;
			No<Chave, Valor> paiAtual;
			while (true) {
				paiAtual = pai;
				if (novoNo.getChave().compareTo(paiAtual.getChave()) < 0) {
					pai = paiAtual.getEsq();
					if (pai == null) {
						paiAtual.setEsq(novoNo);
						novoNo.setPai(paiAtual);
						cont++;
						return;
					}
				} else if (novoNo.getChave().compareTo(paiAtual.getChave()) > 0) {
					pai = paiAtual.getDir();
					if (pai == null) {
						paiAtual.setDir(novoNo);
						novoNo.setPai(paiAtual);
						cont++;
						return;
					}
				} else {
					return;
				}
			}
		}
	}

	@Override
	public void imprime(ABB<Chave, Valor> arvore) {
		imprimirAux(arvore.raiz);
	}

	private void imprimirAux(No<Chave, Valor> no) {
		if (no != null) {
			imprimirAux(no.getEsq());

			System.out.print(no.getValor().toString() + " ");

			imprimirAux(no.getDir());
		}
	}

	@Override
	public No<Chave, Valor> buscaIterativa(Chave chave) {
		No<Chave, Valor> no = this.raiz;
		while (!(no.getChave().equals(chave))) {
			if (chave.compareTo(no.getChave()) < 0) {
				no = no.getEsq();
			} else if (chave.compareTo(no.getChave()) > 0) {
				no = no.getDir();
			}
			if (no == null) {
				return null;
			}
		}
		no.setnAcesso(no.getnAcesso() + 1);
		return no;
	}

	@Override
	public No<Chave, Valor> buscaRecursiva(Chave chave) {
		return bRecursiveAux(this.raiz, chave);
	}

	private No<Chave, Valor> bRecursiveAux(No<Chave, Valor> no, Chave chave) {
		if (no == null) {
			return null;
		}
		if (no.getChave().equals(chave)) {
			return no;
		}
		if (chave.compareTo(no.getChave()) < 0) {
			No<Chave, Valor> esq = bRecursiveAux(no.getEsq(), chave);
			if (esq != null) {
				return esq;
			}
		} else if (chave.compareTo(no.getChave()) > 0) {
			No<Chave, Valor> dir = bRecursiveAux(no.getDir(), chave);
			if (dir != null) {
				return dir;
			}
		}
		return no;
	}

	@Override
	public boolean remove(Chave chave) {
		No<Chave, Valor> pai = raiz;
		No<Chave, Valor> atual = raiz;
		boolean paiEsq = true;

		while (atual.getChave() != chave) {
			pai = atual;
			if ((chave.compareTo(atual.getChave()) < 0)) {
				paiEsq = true;
				atual = atual.getEsq();
			} else {
				paiEsq = false;
				atual = atual.getDir();
			}
			if (atual == null) {
				return false;
			}
		}

		if (grauNo(atual) == 0) {
			if (atual == raiz) {
				raiz = null;
				cont--;
			} else if (paiEsq) {
				pai.setEsq(null);
				cont--;
			} else {
				pai.setDir(null);
				cont--;
			}
		} else if (grauNo(atual) == 1) {
			if (atual.getDir() == null) {
				if (atual == raiz) {
					raiz = atual.getEsq();
					cont--;
				} else if (paiEsq) {
					pai.setEsq(atual.getEsq());
					cont--;
				} else {
					pai.setDir(atual.getEsq());
					cont--;
				}
			} else if (atual.getEsq() == null) {
				if (atual == raiz) {
					raiz = atual.getDir();
					cont--;
				} else if (paiEsq) {
					pai.setEsq(atual.getDir());
					cont--;
				} else {
					pai.setDir(atual.getDir());
					cont--;
				}
			}
		} else if (grauNo(atual) == 2) {
			No<Chave, Valor> sucessor = getSucessor(atual);
			if (atual == raiz) {
				raiz = sucessor;
				cont--;
			} else if (paiEsq) {
				pai.setEsq(sucessor);
			} else {
				pai.setDir(sucessor);
			}
			sucessor.setEsq(atual.getEsq());
			cont--;
		}
		return true;
	}

	private No<Chave, Valor> getSucessor(No<Chave, Valor> no) {
		No<Chave, Valor> sucessor = no;
		No<Chave, Valor> sucessorPai = no;
		No<Chave, Valor> atual = no.getDir();

		while (atual != null) {
			sucessorPai = sucessor;
			sucessor = atual;
			atual = atual.getEsq();
		}
		if (sucessor != no.getDir()) {
			sucessorPai.setEsq(sucessor.getDir());
			sucessor.setDir(no.getDir());
		}
		return sucessor;
	}

	@Override
	public int grauNo(No<Chave, Valor> no) {
		int cont = 0;
		if (no == null) {
			return 0;
		} else {
			if (no.getEsq() != null) {
				cont++;
			}
			if (no.getDir() != null) {
				cont++;
			}
			return cont;
		}
	}

	@Override
	public int grauArvore(ABB<Chave, Valor> arvore) {
		return maxGrau(arvore.raiz);
	}

	private int maxGrau(No<Chave, Valor> no) {
		if (no == null) {
			return 0;
		}
		int esq = grauNo(no.getEsq());
		int dir = grauNo(no.getDir());

		int maior = Math.max(esq, dir);
		return maior;
	}

	@Override
	public int alturaArvore(ABB<Chave, Valor> arvore) {
		return alturaAux(arvore.raiz) + 1;
	}

	private int alturaAux(No<Chave, Valor> no) {
		if (no == null) {
			return -1;
		}

		int esq = alturaAux(no.getEsq());
		esq++;
		int dir = alturaAux(no.getDir());
		dir++;
		int maior = Math.max(esq, dir);

		return maior;
	}

	@Override
	public int tamanho() {
		return this.cont;
	}

	@Override
	public Iterator<No<Chave, Valor>> iterator() {
		return new IteradorABB<>(raiz);
	}

	public void attArvore(ABB<Chave, Valor> a) {
		ABB<Chave, Valor> arvoreAtt = new ABB<>();
		List<No<Chave, Valor>> nos = new ArrayList<>();
		
		for (No<Chave, Valor> n : a) {
			nos.add(n);
		}
	
		Collections.sort(nos);
		
		for (No<Chave, Valor> no : nos) {
			arvoreAtt.insercao(no.getChave(), no.getValor());
		}
		a.raiz = arvoreAtt.raiz;
	}
	
}
