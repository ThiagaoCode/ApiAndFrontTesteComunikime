package Api.Teste.Comunikime.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // == tabela  notacao quer quer dizer que vai ser uma classe do jpa referente
@Table(name = "produto")

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nome;

	@NotNull
	@Size(min = 10, max = 1000)
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne              
	@JsonIgnoreProperties("Produto")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "meusProdutos" })
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	

}
