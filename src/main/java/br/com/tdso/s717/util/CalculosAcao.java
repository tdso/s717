package br.com.tdso.s717.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.tdso.s717.model.Estoque;
import br.com.tdso.s717.model.Negociacao;

public class CalculosAcao implements CalculosAtivo {

	@Override
	public BigDecimal calculaPrecoMedio(Negociacao negociacao, Estoque estoque) {
		BigDecimal precoMedioNegociacao = negociacao.getValorNegociacao()
									.divide(BigDecimal.valueOf(negociacao.getQuantidadeNegociada()));
		if (estoque.getQuantidade() == 0) {
			return precoMedioNegociacao;
		}		
		//BigDecimal precoMedioEstoque = estoque.getPrecoMedio()
		//							.multiply(BigDecimal.valueOf(estoque.getQuantidade()));
		BigDecimal qtdeTotal = BigDecimal.valueOf(negociacao.getQuantidadeNegociada())
									.add(BigDecimal.valueOf(estoque.getQuantidade()));
		BigDecimal volumeEstoque = estoque.getPrecoMedio()
											.multiply(BigDecimal.valueOf(estoque.getQuantidade()));
		//BigDecimal somaPrecosMedios = precoMedioNegociacao.add(estoque.getPrecoMedio());
		BigDecimal somaVolumes = volumeEstoque.add(negociacao.getValorNegociacao());
		
		BigDecimal calculo = somaVolumes.divide(qtdeTotal, 2, RoundingMode.HALF_UP);
		
		return calculo;
	}

}
