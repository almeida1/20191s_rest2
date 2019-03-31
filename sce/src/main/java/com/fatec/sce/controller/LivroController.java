package com.fatec.sce.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sce.model.Livro;

@RestController
public class LivroController {
	private static Map<String, Livro> livroRepo = new HashMap<>();
	static {
		Livro c1 = new Livro("1111", "Engenharia de Software", "Pressman");
		Livro c2 = new Livro("2222", "Engenharia de Software", "Sommerville");
		Livro c3 = new Livro("3333", "Testes de Software", "Delamaro");

		livroRepo.put(c1.getIsbn(), c1);
		livroRepo.put(c2.getIsbn(), c2);
		livroRepo.put(c3.getIsbn(), c3);

	}

	@RequestMapping(value = "/livros", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getTodos() {
		return new ResponseEntity<>(livroRepo.values(), HttpStatus.OK);
	}
	@RequestMapping(value = "/livros", method = RequestMethod.POST)
	public ResponseEntity<Object> cadastraLivro(@RequestBody Livro livro) {
		if (livroRepo.get(livro.getIsbn()) != null) {
			return new ResponseEntity<>("Livro já cadastrado", HttpStatus.BAD_REQUEST); //(400)
		} else {
			livroRepo.put(livro.getIsbn(), livro);
			return new ResponseEntity<>("Livro incluido com sucesso", HttpStatus.CREATED);
		}
	}
	@RequestMapping(value = "/livros", method = RequestMethod.PUT)
	public ResponseEntity<Object> atualizaLivro(@RequestBody Livro livro) {
		livroRepo.remove(livro.getIsbn());
		livroRepo.put(livro.getIsbn(), livro);
		return new ResponseEntity<>("Livro atualizado com sucesso", HttpStatus.OK);
	}
	@RequestMapping(value = "/livros/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> consultaLivro(@PathVariable("id") String id) {
		Livro livro = livroRepo.get(id);
		if (livro != null) 
		 return new ResponseEntity<>(livro, HttpStatus.OK);
		else
		 return new ResponseEntity<>("Livro não encontrado", HttpStatus.NOT_FOUND);
	}
	/**
	 * Exclui o registro
	 * @param id String isbn do livro
	 * @return ok ou not found
	 */
	@RequestMapping(value = "/livros/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> excluirLivro(@PathVariable("id") String id) {
		Livro livro = livroRepo.get(id);
		if (livro != null) {
			livroRepo.remove(id);
			return new ResponseEntity<>("Livro excluido", HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Livro não encontrado", HttpStatus.NOT_FOUND);
	}
}