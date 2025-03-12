import DAO.LivrosDAO;
import DAO.UsuarioDAO;
import models.Livros;
import models.Usuario;

public class Main {

	public static void main(String[] args) {
		
		UsuarioDAO userDAO = new UsuarioDAO();
		LivrosDAO livrosDAO = new LivrosDAO();
		
		Usuario user1 = new Usuario("Fabricio lima", "fabricio@email.com");
		Livros livro1 = new Livros("O menino que descobriu o vento", "Jorge bem Jour", 1998);
		Usuario user2 = new Usuario("Ampelia Passos", "amelia@email.com");
		Livros livro2 = new Livros("O vento levou", "Amauri Dumbo", 2010);
		
		Livros livro3 = new Livros("Donquerrot", "Machado de assim", 1500);
		Livros livro4 = new Livros("Visconde de sabugosa", "Mauricio de Souza", 1997);
		Livros livro5 = new Livros("Cruz", "Marcelo Rossi", 2008);
		
	
		
		
		
		
	}

}
