package com.escola.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.escola.apirest.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	//public Optional<Aluno> findByMatricula(Integer id);
	public Aluno findByMatricula(Integer id);
	
	@Query("SELECT a FROM Aluno a where a.nome = ?1 AND a.rg like %?2%")
    public List<Aluno> findByNomeAndRg(String nome, String rg);
	
	@Query("SELECT a FROM Aluno a where a.nome = ?1 OR a.email like %?2%")
    public List<Aluno> findByNomeOrEmail(String nome, String email);	
	
	@Query("select a from Aluno a where a.nome LIKE %?1%")
    public List<Aluno> findByNomeLike(String nome);
	
	@Query("SELECT  a.nome FROM Curso as c INNER JOIN Aluno as a ON a.matricula = c.id WHERE a.matricula = ?1 ")
	public List<Aluno> findByAlunoInnerJoinCurso(Integer matricula); 
	

}
