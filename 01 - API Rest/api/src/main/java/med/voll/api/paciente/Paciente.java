package med.voll.api.paciente;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Paciente {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private Endereco endereco;
	private Boolean ativo;

	public Paciente(DadosCadastroPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.cpf = dados.cpf();
		this.endereco = new Endereco(dados.endereco());
		this.ativo = true;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados) {
		if (dados.nome() != null && dados.nome() != "")
			this.nome = dados.nome();

		if (dados.telefone() != null && dados.nome() != "")
			this.telefone = dados.telefone();

		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}

	}

	public void excluir() {
		this.ativo = false;
	}

}
