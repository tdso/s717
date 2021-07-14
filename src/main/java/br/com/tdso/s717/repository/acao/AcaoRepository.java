package br.com.tdso.s717.repository.acao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tdso.s717.model.Acao;

public interface AcaoRepository extends JpaRepository<Acao, Long>{
	
	//public Optional<Acao> findByCod_Negociacao(String codNegociacao);

}
