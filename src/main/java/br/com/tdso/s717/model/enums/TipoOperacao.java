package br.com.tdso.s717.model.enums;

import br.com.tdso.s717.model.Exceptions.ModelExceptions;
import br.com.tdso.s717.model.Exceptions.Negociais.ValidacaoException;

public enum TipoOperacao {
	
	COMPRA(1),
	VENDA(2),
	ALUGUEL(3);

	private int codigo;
	private TipoOperacao(int codigoOperacao) {
		this.codigo = codigoOperacao;
	}
	
	public int getCodigoTipoOperacao () {
		return this.codigo;
	}
	
	public TipoOperacao getCodigoTipoOperacao (int codigo) {
		for (TipoOperacao tipo : TipoOperacao.values()) {
			if (tipo.getCodigoTipoOperacao() == codigo) {
				return tipo;
			}
		}
		throw new ValidacaoException("Tipo de Operação informado inexistente !!");
	}
}
