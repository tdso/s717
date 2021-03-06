package br.com.tdso.s717.resource.teste;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.tdso.s717.model.Selic;
import br.com.tdso.s717.repository.selic.SelicRepository;

@RestController
@RequestMapping("/send")
public class TesteResource {
	
	@Autowired
	SelicRepository repo;

	@PostMapping
	public ResponseEntity<?> gravaArquivo(String pessoaJson, MultipartFile foto) throws IOException, SerialException, SQLException {
		SerialBlob sb = new SerialBlob (foto.getBytes()); 
		Selic selic = new Selic(sb);
		repo.save(selic);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getSelic(@PathVariable Long id) throws SQLException, IOException {
		Selic selic = repo.getById(id);
		InputStream is = selic.getTexto().getBinaryStream();
		return is.readAllBytes();
		
		//return selic.getTexto().getBytes(0, selic.getTexto().length());
	}
	private void gravaArquivo(MultipartFile foto) throws IOException {
		File file = new File("/home/tdso/Downloads/input.txt");
		FileOutputStream input = new FileOutputStream(file);
		IOUtils.copyLarge(foto.getInputStream(), input);	
	}

}
