package Api.Teste.Comunikime.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Api.Teste.Comunikime.model.Produto;
import Api.Teste.Comunikime.model.Categoria;
import Api.Teste.Comunikime.repository.ProdutoRepository;
import Api.Teste.Comunikime.repository.CategoriaRepository;
import Api.Teste.Comunikime.repository.UsuarioRepository;




@Service
public class ProdutoService {
	
	@Autowired
	private  ProdutoRepository repositorioP;
	
	@Autowired
	private UsuarioRepository repositorioU;
	
	@Autowired
	private CategoriaRepository repositorioT;
	
	public Optional<?> cadastrarPostagem(Produto novoProduto) {
		Optional<Categoria> objetoExistente = repositorioT.findById(novoProduto.getCategoria().getId());
		return repositorioU.findById(novoProduto.getUsuario().getId()).map(usuarioExistente -> {
			if (objetoExistente.isPresent()) {
				novoProduto.setUsuario(usuarioExistente);
				novoProduto.setCategoria(objetoExistente.get());
				return Optional.ofNullable(repositorioP.save(novoProduto));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
	public Optional<Produto> alterarPostagem(Produto ProdutoParaAlterar) {
		return repositorioP.findById(ProdutoParaAlterar.getId()).map(postagemExistente -> {
			postagemExistente.setNome(ProdutoParaAlterar.getNome());
			postagemExistente.setDescricao(ProdutoParaAlterar.getDescricao());
			return Optional.ofNullable(repositorioP.save(postagemExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
