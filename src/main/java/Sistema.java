import java.util.List;
import java.util.Scanner;

import DAO.LivrosDAO;
import DAO.UsuarioDAO;
import models.Livros;
import models.Usuario;

public class Sistema {

	public static void main(String[] args) {

		Usuario usuarios = new Usuario();
		UsuarioDAO useroptions = new UsuarioDAO();
		Livros livros = new Livros();
		LivrosDAO livrooptions = new LivrosDAO();

		Scanner sc = new Scanner(System.in);

		System.out.println("Bem vindo ao system Library!");
		System.out.println("Para entrar no sistema digite seu login");

		String login = sc.next();

		int opcao = 1000;

		List<Usuario> users = useroptions.consultarUsuario();
		

		while (opcao != 0) {
		for (Usuario u : users) {
			
				if (login.equals(u.getLogin())) {
					System.out.println(
							"Olá " + u.getNome() + ". Digite a opção desejada ou digite 0 para sair do sistema");
					System.out.println("Digite 1 para consultar livros disponíveis");
					System.out.println("Digite 2 para pegar um livro emprestado");
					System.out.println("Digite 3 para devolver um livro");
					System.out.println("ou digite 0 para sair do sistema");
					opcao = sc.nextInt();
				}
				if (opcao == 1) {
					List<Livros> livrosList = livrooptions.consultarLivros();
					for (Livros l : livrosList) {
						System.out.println("Titulo: " + l.getTitle());
						System.out.println("Autor: " + l.getAutor());
						System.out.println("Ano de publicação: " + l.getAnoPublicação());
						if (l.isDisponivel()) {
							System.out.println("Disponivel para emprestimo");
						} else {
							System.out.println("Não disponivel para emprestimo");
						}
						System.out.println("Código do livro: " + l.getId());
						System.out.println("--------------------------------------");
					}
				}
				
				if(opcao == 2) {
					u.pegarEmprestado(u);
				}
				
				if (opcao == 3) {
					u.devolverLivro(u);
				}
			}
		}
		
		System.out.println("Obrigado e volte sempre");
	}

}
