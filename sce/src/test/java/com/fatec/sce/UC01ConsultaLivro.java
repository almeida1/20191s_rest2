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
	public void CT01getProductsList() throws Exception {
	   String uri = "/livro";
	   setUp();
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   Livro[] productlist = super.mapFromJson(content, Livro[].class);
	   assertTrue(productlist.length > 0);
	}
	/**
	 * Verifica o comportamento na chamada de um endereco invalido
	 * O erro 404 é um código de resposta HTTP que indica que o cliente pôde comunicar com 
	 * o servidor, mas ou o servidor não pôde encontrar o que foi pedido 
	 * @throws Exception
	 */
	@Test
	public void CT02getProductsList() throws Exception {
	   String uri = "/livros";
	   setUp();
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(404, status);
	  
	}
}
