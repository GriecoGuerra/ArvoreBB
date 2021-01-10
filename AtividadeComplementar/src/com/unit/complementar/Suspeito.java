package com.unit.complementar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.abb.ABB;
import com.abb.No;

public class Suspeito implements Comparable<Suspeito> {

	private String nome;
	private ABB<String, Suspeito> cumplices;
	private ABB<String, Crime> crimes;

	public Suspeito() {
	}

	public Suspeito(String nome) {
		this.nome = nome;
		this.cumplices = new ABB<>();
		this.crimes = new ABB<>();
	}

	public void addCumplice(Suspeito suspeito, Suspeito cumplice) {
		suspeito.getCumplices().insercao(cumplice.getNome(), cumplice);
		cumplice.getCumplices().insercao(suspeito.getNome(), suspeito);
	}

	public void listarCumplices(Suspeito suspeito) {
		ABB<String, Suspeito> listaCumplices = new ABB<>();
		List<Suspeito> lCumplices = new ArrayList<>();

		for (No<String, Suspeito> s : suspeito.getCumplices()) {
			lCumplices.add(s.getValor());
		}

		Collections.sort(lCumplices);

		for (Suspeito s2 : lCumplices) {
			listaCumplices.insercao(s2.getNome(), s2);
		}

		listaCumplices.imprime(listaCumplices, "e");
	}

	public void listarCrimes(Suspeito suspeito) {
		suspeito.getCrimes().imprime(suspeito.getCrimes(), "e");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ABB<String, Suspeito> getCumplices() {
		return cumplices;
	}

	public void setCumplices(ABB<String, Suspeito> cumplices) {
		this.cumplices = cumplices;
	}

	public ABB<String, Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(ABB<String, Crime> crimes) {
		this.crimes = crimes;
	}

	@Override
	public int compareTo(Suspeito s) {
		int comp = s.getNome().compareTo(this.nome);
		if (comp > 0) {
			return -1;
		}
		if (comp < 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
