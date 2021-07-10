package br.com.tdso.s717.resource.ativo;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.repository.acao.AcaoRepository;

@RestController
@RequestMapping("/ativos")
public class AtivoResource {
	
	@Autowired
	private AcaoRepository repo;
	
	@GetMapping
	public ResponseEntity listaAtivos(){
		Acao acao = new Acao ("TAESA", "TAEE11", 500, BigDecimal.valueOf(30.10));
		Acao novaAcao = repo.save(acao);
		return ResponseEntity.ok().body(novaAcao);
	}

}
