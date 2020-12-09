package com.escola.apirest.resources;

import java.util.List;

import javax.validation.Valid;

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
import com.escola.apirest.repository.CursoRepository;
import com.escola.apirest.repository.DisciplinaRepository;

@RestController
@RequestMapping(value="/api")
public class DisciplinaResource {
	
	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	@Autowired
	CursoRepository     cursoRepository;
	
	@GetMapping("/disciplinas")
	public List<Disciplina> listarDisciplinas(){
		return disciplinaRepository.findAll();
	}

	@GetMapping("/disciplina/{id}")
	public Disciplina listaDisciplina(@PathVariable(value="id") Integer id){
		return disciplinaRepository.findByid(id);
	}
	
	@PostMapping("/disciplina/curso/{idCurso}")
	public Disciplina DisciplinaComCurso(@PathVariable("idCurso") Integer idCurso, @Valid @RequestBody Disciplina disciplina) {
		Curso curso = cursoRepository.findByid(idCurso);
		curso.getDisciplinas().add(disciplina);
		return disciplinaRepository.save(disciplina);
	}
	
	@PostMapping("/disciplina")
	public Disciplina salvaDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
	@DeleteMapping("/disciplina")
	public void deletaDisciplina(@RequestBody Disciplina disciplina) {
		disciplinaRepository.delete(disciplina);
	}
	
	@DeleteMapping("/disciplina/{id}")
	public void deletaDisciplinaId(@PathVariable(value="id") Integer id) {
		Disciplina disciplina = disciplinaRepository.findByid(id);
		disciplinaRepository.delete(disciplina);
	}
	
	@PutMapping("/disciplina")
	public Disciplina atualizaDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
}
