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
import br.com.tdso.s717.repository.negociacao.NegociacaoRepository;
import br.com.tdso.s717.service.negociacao.NegociacaoService;

@RestController
@RequestMapping("/neg")
public class NegociacaoResource {
	
	@Autowired
	private NegociacaoService negociacaoService;

	@GetMapping
	public List<Negociacao> listaNegociacao (){
		return negociacaoService.listaNegociacoes();
	}
	
	@PostMapping
	public Negociacao incluiNegociacao (
			@RequestBody Map<String, String> json) throws Exception {
				
		Negociacao negociacao = negociacaoService.buildNegociacao(json.get("codAtivo"),
				json.get("dataNeg"), json.get("valorNeg"), json.get("quantidade"),
				json.get("tipoOperacao"));
		
		return negociacaoService.incluiNegociacao(negociacao);		
	}
}
