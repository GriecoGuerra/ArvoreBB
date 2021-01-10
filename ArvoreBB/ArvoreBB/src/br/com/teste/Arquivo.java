package br.com.teste;

import java.time.LocalDate;

public class Arquivo {
	
	private String nome;
	private String tipo;
	private double tamanho;
	private LocalDate dtCriado;
	private LocalDate dtAcessado;
	
	public Arquivo() {}
	
	public Arquivo(String nome, String tipo, double tamanho, LocalDate c, LocalDate a) {
		this.nome = nome;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.dtCriado = c;
		this.dtAcessado = a;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getTamanho() {
		return tamanho;
	}

	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}
	
	public LocalDate getDtCriado() {
		return dtCriado;
	}

	public void setDtCriado(LocalDate dtCriado) {
		this.dtCriado = dtCriado;
	}

	public LocalDate getDtAcessado() {
		return dtAcessado;
	}

	public void setDtAcessado(LocalDate dtAcessado) {
		this.dtAcessado = dtAcessado;
	}

	@Override
	public String toString() {
		return "\nArquivo = {" + this.nome + ", " + this.tipo + ", " + this.tamanho + ", " + this.dtCriado + ", " + this.dtAcessado + "};";
	}
}
