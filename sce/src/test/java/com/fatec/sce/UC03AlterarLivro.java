package com.fatec.sce;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fatec.sce.model.Livro;


import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
public class UC03AlterarLivro extends AbstractTest {

	@Test
	public void alterarLivro() throws Exception {
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
}
