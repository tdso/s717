package br.com.tdso.s717.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;
import br.com.tdso.s717.model.Exceptions.Negociais.ValidacaoException;
import br.com.tdso.s717.model.enums.TipoOperacao;
import br.com.tdso.s717.repository.ativo.AtivoRepository;

public class NegociacaoDTO {

	private String codAtivo;
	private String data;
	private String valor;
	private String qtde;
	
	private Ativo ativo;
	private LocalDate dataNeg;
	private BigDecimal valorNeg;
	private int quantidade;
	private TipoOperacao tipoOperacao;
	//@Autowired
	private AtivoRepository ativoRepository;

	public NegociacaoDTO(String codAtivo, String data, String valor,
			String qtde, String tipoOp, AtivoRepository repo) {
		this.codAtivo = codAtivo;
		this.data = data;
		this.valor = valor;
		this.qtde = qtde;
		this.ativoRepository = repo;
		this.ativo = validaAtivo(codAtivo);
		this.dataNeg = validaDataNeg(data);
		this.valorNeg = validaValorNeg(valor);
		this.quantidade = validaQuantidade(qtde);
		this.tipoOperacao = validaTipoOperacao(tipoOp);
	}

	private LocalDate validaDataNeg(String dataNeg) throws ValidacaoException {
		LocalDate data = null;
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		data = LocalDate.parse(dataNeg, formatadorData);
		if (data.isAfter(LocalDate.now())) {
			throw new ValidacaoException("Data de negociação não pode ser futura !!");
		}
		return data;
	}

	private TipoOperacao validaTipoOperacao(String tipoOp) {
		int tipo = 0;
		try {
			tipo = Integer.parseInt(tipoOp);
		} catch (NumberFormatException e) {
			throw new ValidacaoException("Tipo de Operação informado inexistente [1 - compra ou 2 - venda ou 3 - aluguel] !!");			
		}
		
		if (tipo <= 0) {
			throw new ValidacaoException("Tipo de Operação informado inexistente [1 - compra ou 2 - venda ou 3 - aluguel] !!");
		}
		for (TipoOperacao operacao : TipoOperacao.values()) {
			if (operacao.getCodigoTipoOperacao() == tipo) {
				return operacao;
			}
		}
		throw new ValidacaoException("Tipo de Operação informado inexistente [1 - compra ou 2 - venda ou 3 - aluguel] !!");
	}

	private Ativo validaAtivo(String codAtivo) {

		if (codAtivo.isEmpty()) {
			throw new ValidacaoException("Código do Ativo não informado !!");
		}

		Optional<Ativo> ativoOptional = this.ativoRepository.findByCodigoNegociacao(codAtivo);
		if (ativoOptional.isEmpty()) {
			throw new ValidacaoException("Código do Ativo não cadastrado no sistema !!");
		}
		return ativoOptional.get();
	}

	private int validaQuantidade(String quantidade) {
		
		int qtde = 0;
		
		try {
			qtde = Integer.parseInt(quantidade);
		} catch (NumberFormatException e) {
			throw new ValidacaoException("Valor inválido informando para quantidade: " + quantidade + " !!");			
		}
		
		if (qtde <= 0) {
			throw new ValidacaoException("Quantidade deve ser maior que zero !!");
		}
		return qtde;
	}

	private BigDecimal validaValorNeg(String valorNeg) {
		BigDecimal valor = BigDecimal.ZERO;
		try {
			valor = BigDecimal.valueOf(Double.parseDouble(valorNeg));
		} catch (NumberFormatException e) {
			throw new ValidacaoException("Valor informado (" + valorNeg + ") da negociação inválido !!");
		}
		if (!(valor.compareTo(BigDecimal.ZERO) == 1)) {
			throw new ValidacaoException("Valor da negociação deve ser maior do que zero !!");
		}
		return valor;
	}

	public String getCodAtivo() {
		return codAtivo;
	}

	public String getData() {
		return data;
	}

	public String getValor() {
		return valor;
	}

	public String getQtde() {
		return qtde;
	}

	public Negociacao toNegociacao() {
		return new Negociacao(ativo, dataNeg, valorNeg, quantidade, tipoOperacao);
	}

}
