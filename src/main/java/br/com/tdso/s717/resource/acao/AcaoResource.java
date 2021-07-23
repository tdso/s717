package br.com.tdso.s717.resource.acao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.model.dto.AcaoDTO;
import br.com.tdso.s717.service.acao.AcaoService;

@RestController
@RequestMapping("/acao")
public class AcaoResource {
	
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
