package grupoorbitais.equipeoorbitais.controle;

import java.util.ArrayList;
import java.util.Scanner;

import grupoorbitais.equipeoorbitais.modelo.Aluno;
import grupoorbitais.equipeoorbitais.modelo.Pessoa;

/*Crie um código para inserção, alteração, remoção e listagem de alunos da base de dados
 * abaixo tem as variaveis de cada classe
 * public Aluno(int id, String matricula, int anoEntrada, int idPessoa);
 * public Pessoa(int id, String nome, String cpf) ;
  */

public class MainEquipeOrbitais{
	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		
		//objetos das classes de controle
        PessoaDAO pessoaDAO = new PessoaDAO();
       // AlunoDAO alunoDAO = new AlunoDAO();
        
      //objetos das classes de modelo
        Pessoa pessoa = new Pessoa();
        //Aluno aluno = new Aluno();
        
		//variavéis e listas das classes de modelo
		String nome, cpf, matricula;
		//int anoEntrada;
		ArrayList<Pessoa> listarPessoas;
        //ArrayList<Aluno> listarAlunos;

		System.out.println("####  SEJA BEM VINDO!!!  ####");
				
		int escolha=4;
		while(escolha !=3) {
			System.out.println("\n-----------------------------");
			System.out.println("       MENU PRINCIPAL      ");
			System.out.println("-----------------------------");
			System.out.println("Digite a opção desejada:");
	        System.out.println("1 - Pessoa");
	        System.out.println("2 - Aluno");
	        System.out.println("3 - Sair");
	        escolha = leia.nextInt();
			
	        switch (escolha){
	        case 1:
	        	int escolha1=6;
	    		while(escolha1 !=5) {
			        	System.out.println("\n-----------------------------");
						System.out.println("         MENU PESSOA        ");
						System.out.println("-----------------------------");
			    		System.out.println("Digite a opção desejada:");
			            System.out.println("1- Inserir");
			            System.out.println("2- Alterar");
			            System.out.println("3- Remover");
			            System.out.println("4- Listar");
			            System.out.println("5- Voltar");
			            escolha1 = leia.nextInt();
			            
			            switch (escolha1){
			            	//Inserir pessoa	
				            case 1: 
				            	System.out.println("Entrou na opção 1- Inserir");	             	
			                    System.out.println("Digite seu Nome: ");
			                    nome = leia.nextLine();
			                    nome = leia.nextLine(); //só p/ garantir q ele vai ler
			                    System.out.println("Digite seu CPF: ");
			                    cpf = leia.nextLine();
			                   
                                if(!pessoaDAO.temCPFCadastrado(cpf)){//verifica se o cpf digitado é diferente de todos os que já tem cadastrado
	                                	Pessoa novaPessoa = new Pessoa(nome, cpf);
					                    boolean resultado = pessoaDAO.adicionar(novaPessoa); //vai retornar verdadeiro ou falso para se conseguiu adicionar
					                    
					                    if (resultado == true) {
					                        System.out.println("\nPESSOA CADASTRADA! \n");// o \n é p/ pular uma linha antes e outra depois
					                    } else {
					                        System.out.println("\nPESSOA NÃO CADASTRADA! \n");
					                    }
                                }
                                else{
                                    	System.out.println("\nCPF JÁ POSSUI CADASTRO! \n");
                                }
				    	        break;
				    	        
				    	      //Alterar pessoa    
				            case 2:   
				            	System.out.println("Entrou na opção 2- Alterar"); 
				            	System.out.println("Digite o CPF do usuário que deseja alterar: ");
			                    cpf = leia.nextLine();
			                    cpf = leia.nextLine();
			                    
			                    if(pessoaDAO.temCPFCadastrado(cpf)){ //verifica se o cpf digitado ta cadastrado no sistema	                    
			                    	pessoa = pessoaDAO.procurarCPF(cpf); //procura o cpf e poem dentro do obj pessoa  

			                    	System.out.println("\nDigite qual opção deseja alterar:");
				                    System.out.println("1 - Nome: "+ pessoa.getNome());
				                    System.out.println("2- CPF: " + pessoa.getCpf());
				                    int escolha3 = leia.nextInt();
				                    
				                    switch (escolha3){
						            	case 1:
						            		System.out.println("\nDigite o novo Nome: ");
						                    nome = leia.nextLine();
						                    nome = leia.nextLine();
						                    pessoa.setNome(nome);
						                    break;
						                    
						            	case 2:
						            		System.out.println("\nDigite o novo CPF: ");
						                    cpf = leia.nextLine();
						                    cpf = leia.nextLine();
						                    pessoa.setCpf(cpf);
						                    break;
						                    
						            	default: 
						            		break;
				                    }
				                    
				                    boolean resultado = pessoaDAO.alterar(pessoa); //retorna verdadeiro ou falso se consegui alterar o nome
				    	
		                    		if (resultado == true) {
				                        System.out.println("\nNOME ALTERADO! \n");
				                        System.out.println("-----------------------------");
				                        System.out.println("Novos dados:");
					                    System.out.println("Nome: "+ pessoa.getNome());
					                    System.out.println("CPF: " + pessoa.getCpf());
				                    } 
				                    else {
				                        System.out.println("\nNOME NÃO ALTERADO! \n");
				                    }
			                    }
			                    else{
                                	System.out.println("\nCPF NÃO ENCONTRADO NO SISTEMA! \n");
			                    }
				    	        break;
				    	        
				    	     //Remover pessoa    
				            case 3:    
				            	System.out.println("Entrou na opção 3 - Remover"); 
				            	System.out.println("Digite o CPF do usuário que deseja remover: ");
			                    cpf = leia.nextLine();
			                    cpf = leia.nextLine();
			                    
			                    if(pessoaDAO.temCPFCadastrado(cpf)){ //verifica se o cpf digitado ta cadastrado no sistema	                    
				                    	pessoa = pessoaDAO.procurarCPF(cpf); //procura o cpf e poem dentro do obj pessoa  
				                    	
				                    	/*QUANDO FIZER A CLASSE ALUNO TEM QUE VERIFICAR AQUI SE TEM ALGUM
				                    	 * ALUNO RELACIONADO COM UMA PESSOA P/ SÓ ENTÃO REMOVER*/
			                    	    
					                    boolean resultado = pessoaDAO.remover(pessoa); //retorna verdadeiro ou falso se consegui remover o nome
								    	
			                    		if (resultado == true) {
					                        System.out.println("\nUSUÁRIO REMOVIDO! \n");
					                    } 
					                    else {
					                        System.out.println("\nUSUÁRIO NÃO REMOVIDO!! \n");
					                    }
			                    }
			                    else{
	                            	System.out.println("\nCPF NÃO ENCONTRADO NO SISTEMA! \n");
			                    }
				    	        break;
				    	        
				    	    //Listar pessoas    
				            case 4:    
				            	System.out.println("Entrou na opção 4 - Listar"); 
				            	listarPessoas = pessoaDAO.listarPessoas(); //o arrayList listarPessoas vai receber a lista da função getPessoas
                                
				            	if(!(listarPessoas == null)){ //se a lista é diferente de vazio   			
				            			System.out.println(listarPessoas.toString());
                                }
				            	else{
                                    System.out.println("A lista está vazia!");
                                }
                                break;
				    	        
				            case 5:    
				            	System.out.println("Voltando ao menu principal!");
				    	        break;
				    	    default:
				    	    	System.out.println("Opção invalida");
			    	    }
	    		}
	        case 2:  
	        	int escolha2=6;
	    		while(escolha2 !=5) {
			        	System.out.println("\n-----------------------------");
						System.out.println("         MENU ALUNO        ");
						System.out.println("-----------------------------");
			    		System.out.println("Digite a opção desejada:");
			            System.out.println("1- Inserir");
			            System.out.println("2- Alterar");
			            System.out.println("3- Remover");
			            System.out.println("4- Listar");
			            System.out.println("5- Voltar");
			            escolha2 = leia.nextInt();
			            
			            switch (escolha2){
				            case 1:
				            	System.out.println("Entrou na opção 1- Inserir");
				    	        break;
				            case 2:    
				            	System.out.println("Entrou na opção 2- Alterar");
				    	        break;
				            case 3:    
				            	System.out.println("Entrou na opção 3 - Remover");
				    	        break;
				            case 4:    
				            	System.out.println("Entrou na opção 4 - Listar");
				    	        break;
				            case 5:    
				            	System.out.println("Voltando ao menu principal!");
				    	        break;
				    	    default:
				    	    	System.out.println("Opção invalida");
			            }
	    		}
	        case 3:    
			        	System.out.println("Programa finalizado!");
			        	pessoaDAO.fecharEntidade();
				        break;
		    default:
		    			System.out.println("Opção invalida");
		    }
		}
	}
}

