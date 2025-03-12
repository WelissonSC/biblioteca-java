package models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String autor;
	
	@Column(nullable = false)
	private int anoPublicação;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
	private Boolean disponivel = true;
	
	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Livros() {}
	
	public Livros(String titulo, String autor, int ano) {
		this.title = titulo;
		this.autor = autor;
		this.anoPublicação = ano;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnoPublicação() {
		return anoPublicação;
	}

	public void setAnoPublicação(int anoPublicação) {
		this.anoPublicação = anoPublicação;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
