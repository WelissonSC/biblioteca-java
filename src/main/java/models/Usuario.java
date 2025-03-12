package models;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

import DAO.LivrosDAO;
import DAO.UsuarioDAO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String login;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<Livros> livros = new ArrayList<>();

	public Usuario() {
	};

	public Usuario(String nome, String login) {
		this.nome = nome;
		this.login = login;
	}

	public Long getid() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Livros> getLivros() {
		return livros;
	}

	public void setLivros(List<Livros> livros) {
		this.livros = livros;
	}

	public void pegarEmprestado(Usuario user) {
		Scanner sc = new Scanner(System.in);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		LivrosDAO livros = new LivrosDAO();
		livros.livrosDisponiveis();

		System.out.println("Digite o código do livro que deseja pegar emprestado ou 0 para voltar");
		Long idLivro = null;

		if (sc.hasNextLine()) {
			idLivro = sc.nextLong();
		} else {
			System.out.println("Digite um número válido");
			return;
		}

		List<Usuario> userData = usuarioDAO.consultarUsuario();

		for (Usuario u : userData) {
			if (u.getNome().equals(user.getNome()) && u.getLogin().equals(user.login)) {
				livros.setarIdParaEmprestimo(idLivro, u.id);
			}
		}	
	}

	public void devolverLivro(Usuario user) {
		Scanner sc = new Scanner(System.in);
		LivrosDAO livros = new LivrosDAO();
		UsuarioDAO userData = new UsuarioDAO();
		int idLivro = 0;

		livros.lisvrosEmprestados(user);

		System.out.println("Digite o código do livro que você quer devolver");

		if (sc.hasNext()) {
			idLivro = sc.nextInt();
		} else {
			System.out.println("Digite código válido");
		}

		List<Usuario> userAll = userData.consultarUsuario();

		for (Usuario u : userAll) {
			if (u.getNome().equals(user.getNome()) && u.getLogin().equals(user.login)) {
				livros.setarDevolucao(idLivro);
			}
		}

	}

}
