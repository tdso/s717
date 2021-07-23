package br.com.tdso.s717.model.dto;

import java.math.BigDecimal;

import br.com.tdso.s717.model.Acao;

public class AcaoDTO {
	
	private String nome;
	private String cod_negociacao;
	
	public AcaoDTO(String nome, String codigo) {
		this.nome = nome;
		this.cod_negociacao = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCod_negociacao() {
		return cod_negociacao;
	}

}
