package grupoorbitais.equipeoorbitais.modelo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pessoa") //nome da tabela no bd
public class Pessoa {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //os dados dessa chave serão criados pelo banco de dado
	@SequenceGenerator(name = "pessoa", schema = "comum", sequenceName = "pessoa_seq", initialValue = 1)
	@Column(name="id") //nome da coluna no bd
	public int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf")
	private String cpf;
	
	//1 pessoa - N alunos
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pessoa")
	private List<Aluno> listarAlunos;

	//CRIANDO CONSTRUTORES
	public Pessoa() {
	}
	
	public Pessoa(int id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
	

	//CRIANDO GETS E SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    
    public List<Aluno> getAlunos() {
        return listarAlunos;
    }

    public void setAlunos(List<Aluno> listarAlunos) {
        this.listarAlunos = listarAlunos;
    } 
    
   /* @Override
    public String toString() {
        return "Nome: " + nome + "\nCPF: " + cpf + "\n";
    }*/
}
