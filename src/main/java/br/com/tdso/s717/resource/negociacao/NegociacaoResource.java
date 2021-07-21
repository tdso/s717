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
import br.com.tdso.s717.model.validador.NegValidator;
import br.com.tdso.s717.model.validador.NegociacaoService;
import br.com.tdso.s717.model.validador.NegociacaoValidador;
import br.com.tdso.s717.repository.acao.AcaoRepository;
import br.com.tdso.s717.repository.ativo.AtivoRepository;
import br.com.tdso.s717.repository.negociacao.NegociacaoRepository;

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
//	public Negociacao incluiNegociacao (
//			@RequestBody Map<String, String> json) throws Exception {
	public Negociacao incluiNegociacao (
			@RequestBody NegociacaoDTO json) throws Exception {

//		System.out.println();
		System.out.println("##########");
//		System.out.println("codativo = " + json.get("codAtivo") + " data = " + json.get("dataNeg"));
//		System.out.println("valor = " + json.get("valorNeg") + " quantidade = " + json.get("quantidade"));
//		System.out.println("##########");
		
//		String codAtivo = json.get("codAtivo");
//		String dataNeg = json.get("dataNeg");
//		String valorNeg = json.get("valorNeg");
//		String quantidade = json.get("quantidade");
		//NegociacaoDTO negociacaoDTO = new NegociacaoDTO(codAtivo, dataNeg, valorNeg, quantidade, ativoRepo);
		NegociacaoDTO negociacaoDTO = new NegociacaoDTO(json.getCodAtivo(),
				json.getData(),json.getValor(), json.getQtde(),
				ativoRepo);
		Negociacao negociacao = negociacaoDTO.toNegociacao();
		System.out.println("Negociacao: " + negociacao);
		//Negociacao negociacao = negociacaoService.buildNegociacao(codAtivo, dataNeg, valorNeg, quantidade);
		return repo.save(negociacao);
		
	}

}
