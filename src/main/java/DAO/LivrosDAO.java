package DAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Livros;
import models.Usuario;

public class LivrosDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
	private EntityManager em = emf.createEntityManager();

	public void cadatrarLivro(Livros livro) {
		try {
			em.getTransaction().begin();
			em.persist(livro);
			;
			em.getTransaction().commit();
			em.close();
			System.out.println("Livro: " + livro.getTitle() + " cadastrado com sucesso");
		} catch (Exception e) {
			System.err.println("Erro ao cadastrar novo livro");
			e.printStackTrace();
		}

	}

	public List<Livros> consultarLivros() {
		try {
			List<Livros> livros = em.createQuery("FROM Livros", Livros.class).getResultList();
			return livros;

		} catch (Exception e) {
			System.out.println("ERRO ao listar livros");
			return (List<Livros>) e;
		}
	}

	public void livrosDisponiveis() {
		LivrosDAO livros = new LivrosDAO();
		try {
			List<Livros> livrosLis = livros.consultarLivros();

			for (Livros livroAll : livrosLis) {
				if (livroAll.isDisponivel()) {
					System.out.println("Titulo: " + livroAll.getTitle());
					System.out.println("Autor: " + livroAll.getAutor());
					System.out.println("Ano de publicação: " + livroAll.getAnoPublicação());
					System.out.println("Disponivel para emprestimo");
					System.out.println("Código do livro: " + livroAll.getId());
					System.out.println("--------------------------------------");
				}
			}

		} catch (Exception e) {
			System.out.println("ERRO ao listar livros");
			e.printStackTrace();
		}

	}

	public void setarIdParaEmprestimo(Long idLivro, Long idUsuario) {

		Livros livro = em.find(Livros.class, idLivro);
		Usuario usuario = em.find(Usuario.class, idUsuario);

		em.getTransaction().begin();

		if (livro != null && usuario != null) {
			livro.setDisponivel(false);
			livro.setUsuario(usuario);
			em.merge(livro);
			System.out.println("Livro" + livro.getTitle() + "emprestado com sucesso");
		} else {
			System.out.println("Livro não encontrado");
		}

		em.getTransaction().commit();
	}

	public void lisvrosEmprestados(Usuario user) {

		UsuarioDAO usuarioList = new UsuarioDAO();
		LivrosDAO livros = new LivrosDAO();

		List<Usuario> usuario = usuarioList.consultarUsuario();
		List<Livros> livro = livros.consultarLivros();

		for (Livros l : livro) {

			if (l.getUsuario() != null) {
				if (l.getUsuario().getNome().equals(user.getNome())
						&& l.getUsuario().getLogin().equals(user.getLogin())) {
					System.out.println("Titulo: " + l.getTitle());
					System.out.println("Autor: " + l.getAutor());
					System.out.println("Ano de publicação: " + l.getAnoPublicação());
					System.out.println("Código do livro: " + l.getId());
					System.out.println("--------------------------------");
					return;
				}
			} else {
				System.out.println("Você não possui livros");
			}
		}
	}

	public void setarDevolucao(int idLivro) {
		Livros livro = em.find(Livros.class, idLivro);
		em.getTransaction().begin();

		if (livro != null) {
			livro.setDisponivel(true);
			livro.setUsuario(null);
			em.merge(livro);
			System.out.println("livro devolvido com sucesso");
		} else {
			System.out.println("Livro não encontrado");
		}
		em.getTransaction().commit();

	}
	
	public void editarDadtosLivro(Long livroId, String newTitulo, String newAutor, int ano) {
		em.getTransaction().begin();
		
		Livros livros = em.find(Livros.class, livroId);
		
		if (livros != null) {
			livros.setAutor(newAutor);
			livros.setTitle(newTitulo);
			livros.setAnoPublicação(ano);
			System.out.println("Dados atualizados com sucesso");
		}else {
			System.out.println("Livro não encontrado");
		}
		
	}

}
