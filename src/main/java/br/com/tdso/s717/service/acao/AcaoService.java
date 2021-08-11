package br.com.tdso.s717.service.acao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;
import br.com.tdso.s717.model.Exceptions.Negociais.ValidacaoException;
import br.com.tdso.s717.model.dto.AcaoDTO;
import br.com.tdso.s717.repository.acao.AcaoRepository;
import br.com.tdso.s717.repository.ativo.AtivoRepository;
import br.com.tdso.s717.repository.negociacao.NegociacaoRepository;

@Service
public class AcaoService {
	
	@Autowired
	AcaoRepository repo;
	@Autowired
	AtivoRepository ativoRepository;
	@Autowired
	NegociacaoRepository negociacaoRepository;
	
	private final int TAMANHO_MINIMO_CODIGO_NEGOCIACAO = 5;
	
	public List<Acao> getAcoes(){	
		List<Acao> listaAcoes =repo.findAll();
		return listaAcoes; 
	}
	
	public Acao saveAcao(Acao acao){
		return repo.save(acao);
	}

	public Acao buildAcao(AcaoDTO acaodto) {
		validaNomeAcao(acaodto);
		validaCodigoNegociacao(acaodto);
		validaSeAcaoJaExiste(acaodto);
		return new Acao (acaodto.getNome(), acaodto.getCod_negociacao());
	}

	private void validaSeAcaoJaExiste(AcaoDTO acaodto) {
		Optional<Ativo> ativoOptional = this.ativoRepository.findByCodigoNegociacao(acaodto.getCod_negociacao());
		if (ativoOptional.isPresent()) {
			throw new ValidacaoException("Ação com código: " + acaodto.getCod_negociacao() + " já cadastrada !");
		}
	}

	private void validaCodigoNegociacao(AcaoDTO acaodto) {
		if (acaodto.getCod_negociacao().isBlank() || acaodto.getCod_negociacao().isEmpty()) {
			throw new ValidacaoException("Código do Ativo não informado !!");
		}
		if (acaodto.getCod_negociacao().length() < TAMANHO_MINIMO_CODIGO_NEGOCIACAO) {
			throw new ValidacaoException("Código de Ativo inválido: " +  acaodto.getCod_negociacao() + " !!");
		}
	}

	private void validaNomeAcao(AcaoDTO acaodto) {
		if (acaodto.getNome().isBlank() || acaodto.getNome().isEmpty()) {
			throw new ValidacaoException("Nome do Ativo não informado !!");
		}
	}

	public Ativo getAcao(String codigoAcao) {
		Optional<Ativo> ativoOptional = this.ativoRepository.findByCodigoNegociacao(codigoAcao);
		if (ativoOptional.isEmpty()) {
			throw new ValidacaoException("Ação " + codigoAcao + " não cadastrada !!");
		}
		return ativoOptional.get();
	}

	public void deleteAcao(String cod_neg) {
		
		Long id = Long.parseLong(cod_neg);
		Ativo ativo = ativoRepository.getOne(id);
		List<Negociacao> negs = negociacaoRepository.findByAtivo(ativo);
		if (negs.isEmpty()) {
			repo.deleteById(id);
		} else {
			throw new ValidacaoException("Ativo não pode ser excluído !!");
		}
	}

}
