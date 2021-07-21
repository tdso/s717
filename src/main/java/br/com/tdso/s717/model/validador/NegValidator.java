package br.com.tdso.s717.model.validador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;
import br.com.tdso.s717.repository.ativo.AtivoRepository;

public class NegValidator {
	
	private Ativo ativo;
	private LocalDate dataNeg;
	private BigDecimal valorNeg;
	
	private AtivoRepository ativoRepo;
	
	public NegValidator(Ativo ativo, LocalDate dataNeg, BigDecimal valorNeg, AtivoRepository ativoRepo) {
		this.ativo = ativo;
		this.dataNeg = dataNeg;
		this.valorNeg = valorNeg;
		this.ativoRepo = ativoRepo;
	}
	
	public NegValidator (String codAtivo, String data, String valor, AtivoRepository ativoRepo) {
		ativo = validaAtivo(codAtivo);
		dataNeg = validaDataNeg(data);
		valorNeg = validaValorNeg(valor);
		this.ativoRepo = ativoRepo;
	}
	public Negociacao buildNegociacao() {
//		ativo = validaAtivo(codAtivo);
//		dataNeg = validaDataNeg(data);
//		valorNeg = validaValorNeg(valor);
//		return new Negociacao (ativo, dataNeg, valorNeg);
		return new Negociacao (ativo, dataNeg, valorNeg);
		
	}

	private LocalDate validaDataNeg(String dataNeg) {
		LocalDate data = null;
		try {
			DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = LocalDate.parse(dataNeg, formatadorData);
			if (data.isAfter(LocalDate.now())) {
				// throw new Exception("Data de negociação não pode ser futura !!");
			}
		} catch (Exception e) {
			// throw new Exception("Data de negociação inválida !!");
		}
		return data;
	}

	public Ativo validaAtivo(String codAtivo) {

		if (codAtivo.isEmpty()) {
			// throw new Exception("Codigo do ativo não informado !");
		}
		// ****** validar se o Ativo existe e recuperá-lo

		Optional<Ativo> ativoOptional = this.ativoRepo.findByCodigoNegociacao(codAtivo);
		// Optional<Acao> acaoOpcional = acaoRepo.findById(ativoOpcional.get().getId());
		return ativoOptional.get();
	}

	public int validaQuantidade(String quantidade) {
		int qtde = 0;
		try {
			qtde = Integer.parseInt(quantidade);
		} catch (Exception e) {
			// throw new Exception("Valor inválido para Quantidade !!");
		}
		if (qtde == 0) {
			// throw new Exception("Quantidade não pode ser igual a 0");
		}
		return qtde;
	}

	public BigDecimal validaValorNeg(String valorNeg) {
		BigDecimal valor = BigDecimal.ZERO;
		try {
			valor = BigDecimal.valueOf(Double.parseDouble(valorNeg));
			if (valor.equals(BigDecimal.ZERO)) {
				// throw new Exception("Valor da negociação não pode ser zero !!");
			}
		} catch (Exception e) {
			// throw new Exception("Valor de negociação inválido !!");
		}
		return valor;
	}
	
}
