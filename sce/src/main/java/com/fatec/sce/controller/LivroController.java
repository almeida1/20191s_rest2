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

	@RequestMapping(value = "/livros")
	public ResponseEntity<Object> getLivro() {
		return new ResponseEntity<>(livroRepo.values(), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/livros", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Livro livro) {
		if (livroRepo.get(livro.getIsbn()) != null) {
			return new ResponseEntity<>("Livro já cadastrado", HttpStatus.CREATED);
		} else {
			livroRepo.put(livro.getIsbn(), livro);
			return new ResponseEntity<>("Livro incluido com sucesso", HttpStatus.CREATED);

		}
	}
	@RequestMapping(value = "/livros{id}", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Livro livro) { 
	      livroRepo.remove(id);
	      livroRepo.put(livro.getIsbn(), livro);
	      return new ResponseEntity<>("Livro atualizado com sucesso", HttpStatus.OK);
	   }   
}