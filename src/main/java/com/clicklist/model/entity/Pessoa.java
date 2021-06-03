package com.clicklist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.clicklist.model.AbstractEntity;
import com.clicklist.util.StringUtil;

@Entity
@Table(name = "pessoa")
public class Pessoa extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/* Mapeando a Entidade Pessoa - Irá virar uma tabela no banco de dados. */

	@Id
	@Column(name = "id_pessoa", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	@Column(name = "nascimento", nullable = false)
	private String dataNascimento;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "bairro", nullable = false)
	private String bairro;

	@Column(name = "cidade", nullable = false)
	private String cidade;

	/*
	 * @ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
	 * 
	 * @JoinColumn(name = "fk_endereco", nullable = false) private Endereco
	 * endereco;
	 */

	/* Contrutores */

	public Pessoa(Long id) {
		this.id = id;
	}

	public Pessoa() {
	}

	/* Sets e Gets */

	@Override
	public Long getId() {
		return id;
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
		this.cpf = StringUtil.removeCharacters(cpf);
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = StringUtil.removeCharacters(cpf);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
