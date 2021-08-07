package br.com.tdso.s717.util;

import java.math.BigDecimal;

import br.com.tdso.s717.model.Estoque;
import br.com.tdso.s717.model.Negociacao;

public interface CalculosAtivo {
	
	public BigDecimal calculaPrecoMedio (Negociacao negociacao, Estoque estoque);

}
