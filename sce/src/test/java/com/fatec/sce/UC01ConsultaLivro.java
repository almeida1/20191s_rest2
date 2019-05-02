package com.fatec.sce;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fatec.sce.model.Livro;
@RunWith(SpringJUnit4ClassRunner.class)
public class UC01ConsultaLivro extends AbstractTest {
	@Test
	public void CT01consultaListaLivros() throws Exception {
	   String uri = "/livros";
	   setUp();
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Livro[] productlist = super.mapFromJson(content, Livro[].class);
	   assertTrue(productlist.length > 0);
	}
	@Test
	public void CT02incluiLivro() throws Exception {
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
	public void updateProduct() throws Exception {
	   String uri = "/livros/2222";
	   setUp();
	   Livro livro = new Livro();
	   livro.setTitulo("Novo Livro");
	   
	   String inputJson = super.mapToJson(livro);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Livro atualizado com sucesso");
	}
	/**
	 * Verifica o comportamento na chamada de um endereco invalido
	 * O erro 404 é um código de resposta HTTP que indica que o cliente pôde comunicar com 
	 * o servidor, mas ou o servidor não pôde encontrar o que foi pedido 
	 * @throws Exception
	 */
	@Test
	public void CT02getProductsList() throws Exception {
	   String uri = "/livro"; //endereco invalido
	   setUp();
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(404, status);
	  
	}
}
