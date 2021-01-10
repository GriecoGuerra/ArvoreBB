package com.abb;

import java.util.Iterator;
import java.util.Stack;

public class IteradorABB<Chave extends Comparable<Chave>, Valor> implements Iterator<No<Chave, Valor>> {

	Stack<No<Chave, Valor>> pilha;

	public IteradorABB(No<Chave, Valor> raiz) {
		pilha = new Stack<No<Chave, Valor>>();
		while (raiz != null) {
			pilha.push(raiz);
			raiz = raiz.getEsq();
		}
	}
	@Override
	public boolean hasNext() {
		return !pilha.isEmpty();
	}

	@Override
	public No<Chave, Valor> next() {
		No<Chave, Valor> no = pilha.pop();
		No<Chave, Valor> result = no;
		if (no.getDir() != null) {
			no = no.getDir();
			while (no != null) {
				pilha.push(no);
				no = no.getEsq();
			}
		}
		return result;
	}
}