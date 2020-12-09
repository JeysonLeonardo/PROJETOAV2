package com.escola.apirest.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCurso")
	private Integer id;

	@Column
	private String nomeCurso;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true) 
	//@JsonIgnore
	@Column(name = "idAluno")
	private List<Aluno> listaAlunos = new ArrayList<Aluno>();

	public Curso() {
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public Curso(String _nomeCurso) {
		this.nomeCurso = _nomeCurso;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer _id) {
		this.id = _id;
	}

	public String getNomeCurso() {
		return this.nomeCurso;
	}

	public void setNomeCurso(String _nomeCurso) {
		this.nomeCurso = _nomeCurso;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nomeCurso=" + nomeCurso + ", disciplinas=" + disciplinas + "]";
	}

}