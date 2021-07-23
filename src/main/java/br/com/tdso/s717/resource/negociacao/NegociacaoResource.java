package br.com.tdso.s717.resource.negociacao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdso.s717.model.Negociacao;
import br.com.tdso.s717.model.dto.NegociacaoDTO;
import br.com.tdso.s717.repository.acao.AcaoRepository;
import br.com.tdso.s717.repository.ativo.AtivoRepository;
import br.com.tdso.s717.repository.negociacao.NegociacaoRepository;
import br.com.tdso.s717.service.negociacao.NegociacaoService;

@RestController
@RequestMapping("/neg")
public class NegociacaoResource {
	@Autowired
	private NegociacaoRepository repo;
	@Autowired
	private AcaoRepository acaoRepo;
	@Autowired
	private AtivoRepository ativoRepo;
	@Autowired
	private NegociacaoService negociacaoService;

	@GetMapping
	public List<Negociacao> listaNegociacao (){
		return repo.findAll();
	}
	
	@PostMapping
	public Negociacao incluiNegociacao (
			@RequestBody Map<String, String> json) throws Exception {
		
		NegociacaoDTO negociacaoDTO = new NegociacaoDTO(json.get("codAtivo"),
				json.get("dataNeg"), json.get("valorNeg"), json.get("quantidade"),
				ativoRepo);
		
		Negociacao negociacao = negociacaoDTO.toNegociacao();
		
		return repo.save(negociacao);		
	}
}
