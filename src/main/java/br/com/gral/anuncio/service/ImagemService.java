package br.com.gral.anuncio.service;

 import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.gral.anuncio.entity.Imagem;
import br.com.gral.anuncio.exception.RecursoNaoEncontradoException;
import br.com.gral.anuncio.exception.RequisicaoComErroException;
import br.com.gral.anuncio.repository.ImagemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImagemService {

	private String pathFile = "C:/ambienteDeEstudoAds4/projetos/anuncio-api/src/imagens/";

	private final ImagemRepository repository;
	

	public List<Imagem> listarImagens() {
		log.info("Listando imagens...");
		
		List<Imagem> list = repository.findAll();

		return list;
	}

	public Imagem salvar(final Imagem imagem) {
		log.info("Salvando...");
		return repository.save(imagem);
	}

	public Imagem buscarPorId(final Long id) {

		log.info("Buscando por id...");

		Optional<Imagem> imagemOptional = repository.findById(id);

//		decoder(imagemOptional.toString(), pathFile);

		Imagem imagemBanco = imagemOptional.orElseThrow(() -> new RecursoNaoEncontradoException(id.toString()));

		return imagemBanco;

	}

	public void verificaSeExiste(final Long id) {
		log.info("Verificando existência...");

		if (ObjectUtils.isEmpty(id))
			throw new RequisicaoComErroException("Id nulo");

		Optional<Imagem> imagemOptional = repository.findById(id);

		if (!imagemOptional.isPresent())
			throw new RecursoNaoEncontradoException(id.toString());
	}

	public void atualizar(Imagem imagem) {
		log.info("Atualizando...");

		this.verificaSeExiste(imagem.getId());

		repository.save(imagem);
	}

	public void excluir(Long id) {

		Imagem imagem = this.buscarPorId(id);

		this.verificaSeExiste(imagem.getId());

		repository.delete(imagem);
	}

//	public static String encoder(String imagePath) {
//		String base64Image = "";
//		File file = new File(imagePath);
//		try (FileInputStream imageInFile = new FileInputStream(file)) {
//			// Reading a Image file from file system
//			byte imageData[] = new byte[(int) file.length()];
//			imageInFile.read(imageData);
//			base64Image = Base64.getEncoder().encodeToString(imageData);
//		} catch (FileNotFoundException e) {
//			throw new RecursoNaoEncontradoException("Caminho não encontrado " + e.getMessage());
//		} catch (IOException ioe) {
//			System.out.println("Exception while reading the Image " + ioe.getMessage());
//		}
//		return base64Image;
//	}
//
//	public static void decoder(String base64Image, String pathFile) {
//		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
//			// Converting a Base64 String into Image byte array
//			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
//			imageOutFile.write(imageByteArray);
//		} catch (FileNotFoundException e) {
//			System.out.println("Image not found" + e);
//		} catch (IOException ioe) {
//			System.out.println("Exception while reading the Image " + ioe);
//		}
//	}

}
