package DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.Livros;
import models.Usuario;

public class UsuarioDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("meuPU");
	private EntityManager em = emf.createEntityManager();
	
	public void cadastrarNovoUsuario(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			em.close();
			System.out.println("Usuario: " + usuario.getNome() + " cadastrado com sucesso");
		} catch (Exception e) {
			System.out.println("ERRO ao criar novo usuario");
			e.printStackTrace();
		}
	}
	
	public List<Usuario> consultarUsuario() {
		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}
	

	
}
