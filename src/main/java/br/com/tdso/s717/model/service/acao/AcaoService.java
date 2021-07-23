package br.com.tdso.s717.model.service.acao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.model.Exceptions.Negociais.ValidacaoException;
import br.com.tdso.s717.model.dto.AcaoDTO;
import br.com.tdso.s717.repository.acao.AcaoRepository;

@Service
public class AcaoService {
	
	@Autowired
	AcaoRepository repo;
	
	public List<Acao> getAcoes(){	
		List<Acao> listaAcoes =repo.findAll();
		return listaAcoes; 
	}
	
	public Acao saveAcao(Acao acao){
		return repo.save(acao);
	}

	public Acao buildAcao(AcaoDTO acaodto) {
		Optional<Acao> acao = repo.findByQuantidade(100);
		
//		Optional<Acao> acao = repo.findByCod_Negociacao(acaodto.getCod_negociacao());
//		//Optional<Acao> acao = repo.findById(1L);
		if (acao.isPresent()) {
			throw new ValidacaoException("Ação com código: " + acaodto.getCod_negociacao() + " já cadastrada !");
		}
		return new Acao (acaodto.getNome(), acaodto.getCod_negociacao());
	}

}
