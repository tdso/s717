package br.com.tdso.s717.repository.estoque;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.tdso.s717.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	//public Optional<Estoque> findByCod_Negociacao(String codNegociacao);

}
