package br.com.tdso.s717.resource.teste;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/send")
public class TesteResource {

	@PostMapping
	public ResponseEntity<?> gravaArquivo(String pessoaJson, MultipartFile foto) {
		System.out.println(pessoaJson);
		System.out.println(foto.getOriginalFilename());
		return ResponseEntity.ok().build();
	}

}
