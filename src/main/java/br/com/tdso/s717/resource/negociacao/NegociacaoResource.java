package br.com.tdso.s717.resource.negociacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tdso.s717.model.Acao;
import br.com.tdso.s717.model.Ativo;
import br.com.tdso.s717.model.Negociacao;
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

	@GetMapping
	public List<Negociacao> listaNegociacao (){
		return repo.findAll();
	}
	
	@PostMapping
	public Negociacao incluiNegociacao (
			@RequestBody Map<String, String> json
			//@RequestBody String codAtivo,
			//@RequestBody String dataNeg,
			//@RequestBody String valorNeg, 
			//@RequestBody String quantidade) {
			) {
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println();
		System.out.println("##########");
		System.out.println("codativo = " + json.get("codAtivo") + " data = " + json.get("dataNeg"));
		System.out.println("valor = " + json.get("valorNeg") + " quantidade = " + json.get("quantidade"));
		System.out.println("##########");
		
		String codAtivo = json.get("codAtivo");
		String dataNeg = json.get("dataNeg");
		String valorNeg = json.get("valorNeg");
		String quantidade = json.get("quantidade");
		
		LocalDate data = LocalDate.parse(dataNeg, formatadorData);
		LocalDate.parse("2018-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(valorNeg));
		//Optional<Acao> ativoOpcional = acaoRepo.findById(codAtivo);
		Optional<Ativo> ativoOpcional = ativoRepo.findByCodigoNegociacao(codAtivo);
		Optional<Acao> acaoOpcional = acaoRepo.findById(ativoOpcional.get().getId());
		Negociacao negociacao = new Negociacao(acaoOpcional.get(), data, valor);
		
		return repo.save(negociacao);
		
	}

}
