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
import com.escola.apirest.repository.AlunoRepository;
import com.escola.apirest.repository.CursoRepository;

@RestController
@RequestMapping(value = "/api")
public class AlunoResource {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	CursoRepository cursoRepository;

//------------------------------------CRUD ------------------------------	
	@GetMapping("/alunos")
	public List<Aluno> listarAlunos() {
		return alunoRepository.findAll();
	}

	@GetMapping("/aluno/{id}")
	public Aluno listaAluno(@PathVariable(value = "id") Integer id) {
		return alunoRepository.findByMatricula(id);
	}

	@PostMapping("/aluno")
	public Aluno salvaAluno(@RequestBody Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	@DeleteMapping("/aluno/{id}")
	public void deletaAluno(@PathVariable(value = "id") Integer id) {
		Aluno aluno = alunoRepository.findByMatricula(id);
		alunoRepository.delete(aluno);
	}

	@PutMapping("/aluno")
	public Aluno atualizaAluno(@RequestBody Aluno AlunoBody) {
		return alunoRepository.save(AlunoBody);

	}

	@PostMapping("/aluno/curso/{idCurso}")
	public Aluno AlunoComCurso(@PathVariable("idCurso") Integer idCurso, @Valid @RequestBody Aluno aluno) {
		Curso curso = cursoRepository.findByid(idCurso);
		curso.getListaAlunos().add(aluno);
		aluno.setCurso(curso);
		return alunoRepository.save(aluno);
	}

	@PutMapping("/aluno/{id}")
	public Aluno atualizaAluno(@PathVariable("id") Integer id, @RequestBody Aluno AlunoBody) {
		Aluno alunoAtualizado = alunoRepository.findByMatricula(id);
		alunoAtualizado.setCurso(AlunoBody.getCurso());
		alunoAtualizado.setEmail(AlunoBody.getEmail());
		alunoAtualizado.setMatricula(id);
		alunoAtualizado.setNome(AlunoBody.getNome());
		alunoAtualizado.setRg(AlunoBody.getRg());
		alunoAtualizado.setTelefone(AlunoBody.getTelefone());
		return alunoRepository.save(alunoAtualizado);
	}

//------------------------------------ Seleções especiais ------------------------------	
	@GetMapping("/alunoInnerJoin/{id}")
	public List<Aluno> listaAlunoNaTabelaCurso(@PathVariable(value = "id") Integer id) {
		return alunoRepository.findByAlunoInnerJoinCurso(id);
	}

	@GetMapping("/alunoPesquisaNome/{nome}")
	public List<Aluno> listaAluno(@PathVariable(value = "nome") String nome) {
		List<Aluno> Alunos = (List<Aluno>) alunoRepository.findByNomeLike(nome);
		return Alunos;
	}

	@GetMapping("/alunoPesquisaNomeERG//{nome}/{rg}")
	public List<Aluno> listaAlunoNomeERG(@PathVariable("nome") String nome, @PathVariable("rg") String rg) {
		List<Aluno> Alunos = (List<Aluno>) alunoRepository.findByNomeAndRg(nome, rg);
		return Alunos;
	}

	@GetMapping("/alunoPesquisaNomeOuEmail/{nome}/{email}")
	public List<Aluno> listaAlunoNomeOuEmail(@PathVariable("nome") String nome, @PathVariable("email") String email) {
		List<Aluno> Alunos = (List<Aluno>) alunoRepository.findByNomeOrEmail(nome, email);
		return Alunos;
	}

}
