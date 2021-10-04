package Api.Teste.Comunikime.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Api.Teste.Comunikime.model.Categoria;
import Api.Teste.Comunikime.repository.CategoriaRepository;





@Service
public class CategoriaService {
	
	@Autowired
	private  CategoriaRepository repositorioT;
	
	public Optional<Categoria> alterarTema(Categoria categoriaParaAlterar) {
		return repositorioT.findById(categoriaParaAlterar.getId()).map(categoriaExistente -> {
			categoriaExistente.setNome(categoriaParaAlterar.getNome());
			return Optional.ofNullable(repositorioT.save(categoriaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
