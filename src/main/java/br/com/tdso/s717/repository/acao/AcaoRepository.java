package br.com.tdso.s717.repository.acao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tdso.s717.model.Acao;

public interface AcaoRepository extends JpaRepository<Acao, Long>{
	
	//public Optional<Acao> findByCod_Negociacao(String codNegociacao);
	public Optional<Acao> findByPrecoMedio(BigDecimal preco);
	public Optional<Acao> findByQuantidade(int qtde);

}
