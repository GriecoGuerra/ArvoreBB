package com.unit.complementar;

public class Crime {
	
	private String descricao;
	
	public Crime(String desc) {
		this.descricao = desc;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
}
