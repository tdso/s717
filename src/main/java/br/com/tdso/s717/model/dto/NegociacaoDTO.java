package br.com.tdso.s717.model.dto;

import br.com.tdso.s717.model.Negociacao;

public class NegociacaoDTO {
	
	private String codAtivo;
	private String dataNeg;
	private Double valorNeg; 
	private int quantidade;
	
	public NegociacaoDTO(String codAtivo, String dataNeg, Double valorNeg, int quantidade) {
		this.codAtivo = codAtivo;
		this.dataNeg = dataNeg;
		this.valorNeg = valorNeg;
		this.quantidade = quantidade;
	}

	public String getCodAtivo() {
		return codAtivo;
	}

	public String getDataNeg() {
		return dataNeg;
	}

	public Double getValorNeg() {
		return valorNeg;
	}

	public int getQuantidade() {
		return quantidade;
	}	
	
	public Negociacao of() {
		return new Negociacao(null, null, null);
	}

}
