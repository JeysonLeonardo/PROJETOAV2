package com.escola.apirest.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escola.apirest.models.Aluno;
import com.escola.apirest.models.Curso;
import com.escola.apirest.models.Disciplina;
import com.escola.apirest.repository.AlunoRepository;
import com.escola.apirest.repository.CursoRepository;
import com.escola.apirest.repository.DisciplinaRepository;

@RestController
@RequestMapping(value = "/api")
public class CursoResource {

	@Autowired
	CursoRepository cursoRepository;

	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	AlunoRepository alunoRespository;

	@GetMapping("/cursos")
	public List<Curso> listarCursos() {
		return cursoRepository.findAll();
	}

	@GetMapping("/curso/{id}")
	public Curso exibeCurso(@PathVariable(value = "id") Integer id) {
		return cursoRepository.findByid(id);
	}

	@GetMapping("/cursoLeftJoin/{id}")
	public List<Curso> buscaCurso(@PathVariable(value = "id") Integer id) {
		return cursoRepository.findByLeftJoin(id);
	}

	@PostMapping("/curso")
	public Curso salvaCurso(@RequestBody Curso curso) {
		// pesquisar se a disciplina existe e trazer ela para o curso
		for (int i = 0; i < curso.getListaAlunos().size(); i++) {
			Aluno aluno = curso.getListaAlunos().get(i);
			aluno.setCurso(curso); //Relação bidirecional.
		}
		try 
		{
			return cursoRepository.save(curso);
		}catch(Exception e) {
			throw e;
		}
	}

	@DeleteMapping("/curso/{id}")
	public void deletaCurso(@PathVariable(value = "id") Integer id) {
		Curso curso = cursoRepository.findByid(id);
		cursoRepository.delete(curso);
	}

	@PutMapping("/curso")
	public Curso atualizaCurso(@RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}

}
