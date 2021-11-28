package grupoorbitais.equipeoorbitais.controle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.query.Query;

import grupoorbitais.equipeoorbitais.modelo.Aluno;
import grupoorbitais.equipeoorbitais.modelo.Pessoa;

public class AlunoDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("default"); // Criando um objeto de EntityManagerFactory
	EntityManager entityManager = factory.createEntityManager(); // Criando um objeto de EntityManager através da fábrica

	public boolean adicionar(Aluno aluno) {
		boolean resultado;

		// Criando transações para efetuar operações com EntityManager
		try {
			entityManager.getTransaction().begin(); // inicar a operação p/ fazer a inserção
			entityManager.persist(aluno);
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
	
	public Aluno procurarMatricula(String matricula) {
		List listarAlunos;

		try {
			entityManager.getTransaction().begin();
			StringBuilder consulta = new StringBuilder(); // obj da classe q permite criar e manipular dados de Strings dinâmicamente
			
			// fazendo a consulta do bd: SELECT * FROM comum.pessoa WHERE cpf=  '103.300.023-90';
			consulta.append("SELECT * FROM graduacao.aluno ").append("WHERE matricula = '").append(matricula).append("'"); // fazendo a consulta do bd																											
			Query query = (Query) entityManager.createNativeQuery(consulta.toString(), Aluno.class); // Criando uma instância do Query para execução a consulta SQL feita em cima na consulta, que vai acessar o toString da classe Aluno  para isso
			listarAlunos = (List) query.getResultList(); // o resultado da consulta do bd vai para o ArrayList "listar  pessoa"
			entityManager.getTransaction().commit();
		} 
		finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
		if (listarAlunos.isEmpty()) { // se tiver vazio
			return null;
		}
		return (Aluno) (listarAlunos.get(0));
	}

	public boolean temMatriculaCadastrada(String matricula) {
		return procurarMatricula(matricula) != null; // se ao executar procurarMatricula for diferente de vazio
	}
	
	public ArrayList<Aluno> listarAlunos() {
		ArrayList<Aluno> listarAlunos;
		try {
			entityManager.getTransaction().begin();
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT * FROM graduacao.aluno ");
			Query query = (Query) entityManager.createNativeQuery(consulta.toString(), Aluno.class);
			listarAlunos = (ArrayList<Aluno>) query.getResultList();
			entityManager.getTransaction().commit();
		} 
		finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		}
		return listarAlunos;
	}
}
