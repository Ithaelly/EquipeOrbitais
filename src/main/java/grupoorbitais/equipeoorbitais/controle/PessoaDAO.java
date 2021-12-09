package grupoorbitais.equipeoorbitais.controle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.query.Query;

import grupoorbitais.equipeoorbitais.modelo.Pessoa;

public class PessoaDAO {
	/*
	 * Explicando o conceito e utilização do Entity Managers: Os Entity Managers são configurados para serem capazes
	 *  de persistir ou gerenciar tipos específicos de objetos, ler e escreve-los numa base de dados e ser implementado por um
	 * Provedor de Persistência (persistence provider) como Hibernate. Estes provedores são responsáveis por implementar
	 *  a especificação da Java Persistence API, desde o Entity Manager até geração de SQL e outras funcionalidades.*/

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("default"); // Criando um objeto de EntityManagerFactory
	EntityManager entityManager = factory.createEntityManager(); // Criando um objeto de EntityManager através da fábrica

	public boolean adicionar(Pessoa pessoa) {
		boolean resultado;

		// Criando transações para efetuar operações com EntityManager
		try {
			EntityManager entityManager = factory.createEntityManager(); 
			System.out.println(entityManager.isOpen());
			entityManager.getTransaction().begin(); // inicar a operação p/ fazer a inserção
			entityManager.persist(pessoa);
			entityManager.getTransaction().commit(); // comitando a transação
			resultado = true;
		} finally {
			if (entityManager.getTransaction().isActive()) { // "se a transação está ativa"
				entityManager.getTransaction().rollback(); // reverter
				resultado = false;
			}
		}
		
		return resultado;
	}

	public Pessoa procurarCPF(String cpf) {
		List listarPessoas;

		try {
			EntityManager entityManager = factory.createEntityManager(); 
			System.out.println(entityManager.isOpen());
			entityManager.getTransaction().begin();
			StringBuilder consulta = new StringBuilder(); // obj da classe q permite criar e manipular dados de Strings dinâmicamente
			
			// fazendo a consulta do bd: SELECT * FROM comum.pessoa WHERE cpf=  '103.300.023-90';
			consulta.append("SELECT * FROM comum.pessoa ").append("WHERE cpf = '").append(cpf).append("'"); // fazendo a consulta do bd																											
			Query query = (Query) entityManager.createNativeQuery(consulta.toString(), Pessoa.class); // Criando uma instância do Query para execução a consulta SQL feita em cima na consulta, que vai acessar o toString da classe Pessoa  para isso
			listarPessoas = (List) query.getResultList(); // o resultado da consulta do bd vai para o ArrayList "listar  pessoa"
			entityManager.getTransaction().commit();
		} 
		finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
		if (listarPessoas.isEmpty()) { // se tiver vazio
			return null;
		}
		Pessoa pessoa = (Pessoa) listarPessoas.get(0);
		entityManager.detach(pessoa);
		return pessoa;
	}

	public boolean temCPFCadastrado(String cpf) {
		return procurarCPF(cpf) != null; // se ao executar procurarCPF for diferente de vazio
	}

	public ArrayList<Pessoa> listarPessoas() {
		ArrayList<Pessoa> listarPessoas;
		try {
			EntityManager entityManager = factory.createEntityManager(); 
			System.out.println(entityManager.isOpen());
			entityManager.getTransaction().begin();
			StringBuilder consulta = new StringBuilder(); //criando um objeto do construtor String
			consulta.append("SELECT * FROM comum.pessoa ");
			Query query = (Query) entityManager.createNativeQuery(consulta.toString(), Pessoa.class);
			listarPessoas = (ArrayList<Pessoa>) query.getResultList();
			entityManager.getTransaction().commit();
		} 
		finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
		return listarPessoas;
	}

	public boolean alterar(Pessoa pessoa) {
		boolean resultado;

		try {
			EntityManager entityManager = factory.createEntityManager(); 
			System.out.println(entityManager.isOpen());
			entityManager.getTransaction().begin(); // inicar a operação p/ fazer a alteração
			entityManager.merge(pessoa);
			entityManager.getTransaction().commit();
			resultado = true;
		} 
		finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
				resultado = false;
			}
		}
		return resultado;
	}

	public boolean remover(Pessoa pessoa) {
		boolean resultado;
		
		try {
			EntityManager entityManager = factory.createEntityManager(); 
			System.out.println(entityManager.isOpen());
			entityManager.getTransaction().begin();
			pessoa = entityManager.merge(pessoa);
			entityManager.remove(pessoa);
			entityManager.getTransaction().commit();
			resultado = true;
		} 
		finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
				resultado = false;
			}
		}
		return resultado;
	}

	public void fecharEntidade() {
		entityManager.close();
		factory.close();
	}

	public void detach(Pessoa pessoaAluno) {
		// TODO Auto-generated method stub
		
	}
}
