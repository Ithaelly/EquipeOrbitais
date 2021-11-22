package grupoorbitais.equipeoorbitais.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*Os @ são anotação das classes, tabelas, colunas e atributos que queremos armazenadas no banco de dados, 
 * indicando que os objetos criados são entidades e devem ser persistidos.*/

@Entity //define que essa classe é uma entidade a ser mapeada pela JPA
@Table(name = "aluno") //nome da tabela no bd
//DUVIDA: fica "graduacao.aluno" ou "aluno" no nome da tabela?
public class Aluno {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) //os dados dessa chave serão criados pelo banco de dado
	@SequenceGenerator(name = "aluno_seq", schema = "graduacao", sequenceName = "aluno_seq", initialValue = 1)
	@Column(name="id") //nome da coluna no bd
	private int id;
	
	@Column(name="matricula") 
	private String matricula;	
	
	@Column(name="ano_entrada")
	private int anoEntrada;	
	
	//@Column(name="id_pessoa") 
	//private int idPessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	//CRIANDO CONSTRUTORES
	public Aluno() {
	}
	
	public Aluno(int id, String matricula, int anoEntrada) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.anoEntrada = anoEntrada;
	}
	
	
	//CRIANDO GETS E SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public int getAnoEntrada() {
		return anoEntrada;
	}
	
	public void setAno_Entrada(int anoEntrada) {
		this.anoEntrada = anoEntrada;
	}
	
	/*@Override
    public String toString() {
        return "Pessoa relacionada com o respectivo (Nome, CPF): " + pessoa.getNome() + ", " + pessoa.getCpf() +
                "\nMatrícula: " + matricula + "\nAno de entrada: " + anoEntrada + "\n";
    }*/
}

