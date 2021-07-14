package br.com.tdso.s717.repository.ativo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tdso.s717.model.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {
	
	public Optional<Ativo> findByCodigoNegociacao(String codNegociacao);

}
