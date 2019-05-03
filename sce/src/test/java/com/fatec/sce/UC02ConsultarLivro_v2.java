package com.fatec.sce;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UC02ConsultarLivro_v2 {

	@org.springframework.boot.web.server.LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/livros"), HttpMethod.GET, entity,
				String.class);

		String expected = "[{\"isbn\":\"1111\",\"titulo\":\"Engenharia de Software\",\"autor\":\"Pressman\"},{\"isbn\":\"2222\",\"titulo\":\"Engenharia de Software\",\"autor\":\"Sommerville\"},{\"isbn\":\"3333\",\"titulo\":\"Testes de Software\",\"autor\":\"Delamaro\"}]";
		System.out.println(response.getBody());

		try {
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("errro ====>" + e.getMessage());
		}
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}