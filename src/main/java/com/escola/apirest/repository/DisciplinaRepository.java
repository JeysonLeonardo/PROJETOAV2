package com.escola.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.apirest.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{
	Disciplina findByid(Integer id);
	
	//Primeiro, vamos definir os métodos de consulta usando Containing , Contains e IsContaining :
	//https://www.baeldung.com/spring-jpa-like-queries
	List<Disciplina> findByNomeDisciplinaContaining(String nomeDisciplina);//Contendo
	List<Disciplina> findByNomeDisciplinaContains(String nomeDisciplina);//Contém 
	List<Disciplina> findByNomeDisciplinaIsContaining(String nomeDisciplina);//Está Contendo e Semelhante

}
