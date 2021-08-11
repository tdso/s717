package br.com.tdso.s717.repository.negociacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;

public interface NegociacaoRepository extends JpaRepository<Negociacao, Long> {
	
	public List<Negociacao> findByAtivo(Ativo ativo);

}
