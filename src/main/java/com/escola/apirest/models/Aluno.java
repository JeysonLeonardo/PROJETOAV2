package com.escola.apirest.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAluno")
	private Integer matricula;

	@Column
	private String rg;

	@Column
	private String nome;

	@Column
	private String telefone;

	@Column
	private String email;

	@ManyToOne
	private Curso curso;

	public Aluno() {
	}

	@JsonIgnore
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String _rg) {
		this.rg = _rg;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String _nome) {
		this.nome = _nome;
	}

	public Integer getMatricula() {
		return this.matricula;
	}

	public void setMatricula(Integer _matricula) {
		this.matricula = _matricula;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String _telefone) {
		this.telefone = _telefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String _email) {
		this.email = _email;
	}

	@Override
	public String toString() {
		return "Aluno [rg=" + rg + ", nome=" + nome + ", matricula=" + matricula + ", telefone=" + telefone + ", email="
				+ email + ", curso=" + curso + "]";
	}

}
