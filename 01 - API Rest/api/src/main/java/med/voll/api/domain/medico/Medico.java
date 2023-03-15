package med.voll.api.domain.medico;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private String crm;

	@Enumerated(STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	private Boolean ativo;

	public Medico(DadosCadastroMedico dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
		this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
		if (dados.nome() != null)
			this.nome = dados.nome();

		if (dados.telefone() != null)
			this.telefone = dados.telefone();

		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}

	}

}
