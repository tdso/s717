package br.com.tdso.s717.service.negociacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;
import br.com.tdso.s717.model.Exceptions.Negociais.ValidacaoException;
import br.com.tdso.s717.model.enums.TipoOperacao;
import br.com.tdso.s717.repository.ativo.AtivoRepository;
import br.com.tdso.s717.repository.negociacao.NegociacaoRepository;

@Service
public class NegociacaoService {
	
	@Autowired
	private AtivoRepository ativoRepository;
	
	@Autowired
	private NegociacaoRepository repo;

	public Negociacao incluiNegociacao (Negociacao negociacao) {
		return repo.save(negociacao);
	}
	
	public List<Negociacao> listaNegociacoes (){
		return repo.findAll();
	}
	
	public Negociacao buildNegociacao(String codAtivo, String data,
			String valor, String qtde, String tipoOp) throws Exception{
		Ativo ativo = validaAtivo(codAtivo);
		LocalDate dataNeg = validaDataNeg(data);
		BigDecimal valorNeg = validaValorNeg(valor);
		int quantidade = validaQuantidade(qtde);
		TipoOperacao tipoOperacao = validaTipoOperacao(tipoOp);
		return new Negociacao (ativo, dataNeg, valorNeg, quantidade, tipoOperacao);
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

	private LocalDate validaDataNeg(String dataNeg) throws ValidacaoException {
		LocalDate data = null;
			DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(dataNeg, formatadorData);
		if (data.isAfter(LocalDate.now())) {
			throw new ValidacaoException("Data de negociação não pode ser futura !!");
		}
		return data;
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

}
