package com.escola.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.escola.apirest.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
	Curso findByid(Integer id);		
	
	@Query("SELECT c.nomeCurso FROM Curso as c LEFT JOIN Aluno as a ON a.matricula = c.id WHERE a.matricula = ?1 ")
	public List<Curso> findByLeftJoin(Integer id);
}
