package principal;

import java.util.List;

import jakarta.persistence.*;
import principal.model.Pessoa;

public class Programa {

	static EntityManager em;
	static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		
		//Configuração do JPA
		emf = Persistence.createEntityManagerFactory("ex_mysql");
		em = emf.createEntityManager();
		
		
		//Criação do objeto Pessoa
		Pessoa p = new Pessoa("Ramon", "111111");
		
//		salvar(p);
		
		System.out.println(listar());
		System.out.println();
//		System.out.println(buscarporID(5));
		
		//Atualizando os dados de uma pessoa
		p.setNome("Zé");
		alterar(p);
		apagar(5);
		
		//Encerrar a conexão
		em.close();
		emf.close();
		
	}
	
	public static Pessoa buscarporID(Integer id) {
		Pessoa pessoa = em.find(Pessoa.class, id);
		
		return pessoa;
	}
	
	public static List<Pessoa> listar(){
		
		List<Pessoa> lista = em.createQuery("from Pessoa" , Pessoa.class).getResultList();
		
		return lista;
	}
	
	public static Integer salvar(Pessoa pessoa) {
		
		//Gravação da Pessoa no banco de dados
		em.getTransaction().begin();
		em.persist(pessoa);
		em.getTransaction().commit();
		
		return pessoa.getId();
	}
	
	public static Integer alterar(Pessoa pessoa) {
		
		//Atualização da Pessoa no banco de dados
		em.getTransaction().begin();
		em.merge(pessoa);
		em.getTransaction().commit();
		return pessoa.getId();
	}

	public static void apagar(Integer id) {
		Pessoa pessoa = em.find(Pessoa.class, id);
		em.getTransaction().begin();
		em.remove(pessoa);
		em.getTransaction().commit();
	}
}
