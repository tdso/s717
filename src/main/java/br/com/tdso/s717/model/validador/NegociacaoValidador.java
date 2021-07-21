package br.com.tdso.s717.model.validador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;
import br.com.tdso.s717.repository.ativo.AtivoRepository;

public class NegociacaoValidador {
	
//	private String codAtivo;
//	private LocalDate dataNeg;
//	private BigDecimal valorNeg;
//	private int quantidade;
	
//	private NegociacaoValidador(String codAtivo, LocalDate dataNeg, BigDecimal valorNeg, int quantidade) {
//		this.codAtivo = codAtivo;
//		this.dataNeg = dataNeg;
//		this.valorNeg = valorNeg;
//		this.quantidade = quantidade;
//	}
	
	private NegociacaoValidador() {}
	
	public class Build {
		
		private String codAtivo;
		private Ativo ativo;
		private LocalDate dataNeg;
		private BigDecimal valorNeg;
		private int quantidade;
		
		@Autowired
		private AtivoRepository ativoRepo;
		
		public Build(String codAtivo, String dataNeg, String valorNeg, String quantidade  ){
			
			if (codAtivo.isEmpty()) {
				//throw new Exception("Codigo do ativo não informado !");
			}
			//****** validar se o Ativo existe e recuperá-lo
			
			Optional<Ativo> ativoOptional = ativoRepo.findByCodigoNegociacao(codAtivo);
			//Optional<Acao> acaoOpcional = acaoRepo.findById(ativoOpcional.get().getId());
			
			int qtde = Integer.parseInt(quantidade);
			if (qtde == 0) {
				//throw new Exception("Quantidade não pode ser igual a 0");
			}
			
			LocalDate data = null;
			try {
				DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				data = LocalDate.parse(dataNeg, formatadorData);
				if (data.isAfter(LocalDate.now())) {
					//throw new Exception("Data de negociação não pode ser futura !!");
				}
			} catch (Exception e) {
				//throw new Exception("Data de negociação inválida !!");
			}
			
			BigDecimal valor = BigDecimal.ZERO;
			try {
				valor = BigDecimal.valueOf(Double.parseDouble(valorNeg));
				if (valor.equals(valor.ZERO)) {
					//throw new Exception("Valor da negociação não pode ser zero !!");
				}
			} catch (Exception e) {
				//throw new Exception("Valor de negociação inválido !!");
			}
			this.codAtivo = codAtivo;
			this.valorNeg = valor;
			this.dataNeg = data;
			this.quantidade = qtde;
			this.ativo = ativoOptional.get();
		}
		
		public Negociacao builder () {
			return new Negociacao(ativo, dataNeg, valorNeg);
		}
		
	}

}
