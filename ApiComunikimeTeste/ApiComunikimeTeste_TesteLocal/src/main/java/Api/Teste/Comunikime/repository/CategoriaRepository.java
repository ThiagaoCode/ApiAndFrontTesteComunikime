package Api.Teste.Comunikime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Api.Teste.Comunikime.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria> findAllByNomeContainingIgnoreCase(String nome);

}
