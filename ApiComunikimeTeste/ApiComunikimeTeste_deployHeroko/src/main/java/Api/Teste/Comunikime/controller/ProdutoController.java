package Api.Teste.Comunikime.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Api.Teste.Comunikime.model.Produto;
import Api.Teste.Comunikime.repository.ProdutoRepository;
import Api.Teste.Comunikime.service.ProdutoService;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ProdutoService servicos;

	@GetMapping
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> GetById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Produto>> GetByTitulo(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));

	}



	@PostMapping("/salvar")
	public ResponseEntity<Object> cadastrarPostagem(@Valid @RequestBody Produto novoProduto) {
		Optional<?> objetoCadastrado = servicos.cadastrarPostagem(novoProduto);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}

	

	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody Produto produtoParaAlterar) {
		Optional<Produto> objetoAlterado = servicos.alterarPostagem(produtoParaAlterar);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id") Long id) {
		Optional<Produto> objetoExistente = repository.findById(id);
		if (objetoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

}
