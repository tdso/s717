package br.com.tdso.s717.resource.ativo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.model.dto.AcaoDTO;
import br.com.tdso.s717.model.service.acao.AcaoService;
import br.com.tdso.s717.repository.acao.AcaoRepository;

@RestController
@RequestMapping("/acao")
public class AtivoResource {
	
	@Autowired
	private AcaoRepository repo;
	@Autowired
	private AcaoService service;
	
	@GetMapping
	public List<Acao> listaAcao(){
		
		return service.getAcoes();
	}
	
	@PostMapping
	public Acao incluiAcao(@RequestBody AcaoDTO acaodto){
		Acao acao = service.buildAcao(acaodto);
		return service.saveAcao(acao);
	}

}
