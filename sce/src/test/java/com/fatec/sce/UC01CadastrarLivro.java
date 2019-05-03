package com.fatec.sce;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fatec.sce.model.Livro;
@RunWith(SpringJUnit4ClassRunner.class)
public class UC01CadastrarLivro extends AbstractTest {
	
	@Test
	public void CT01incluiLivro() throws Exception {
	   String uri = "/livros";
	   setUp();
	   Livro livro = new Livro();
	   livro.setIsbn("4444");
	   livro.setTitulo("Clean code");
	   livro.setAutor("Martin");
	   String inputJson = super.mapToJson(livro);
	  
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Livro incluido com sucesso");
	}
	@Test
	public void CT02incluiLivro_ja_cadastrado() throws Exception {
	   String uri = "/livros";
	   setUp();
	   Livro livro = new Livro();
	   livro.setIsbn("3333"); //chave ja cadastrada
	   livro.setTitulo("Clean code");
	   livro.setAutor("Martin");
	   String inputJson = super.mapToJson(livro);
	  
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(400, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Livro já cadastrado");
	}
	
	
}
