package Api.Teste.Comunikime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import Api.Teste.Comunikime.model.Categoria;
import Api.Teste.Comunikime.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> GetByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	

}
