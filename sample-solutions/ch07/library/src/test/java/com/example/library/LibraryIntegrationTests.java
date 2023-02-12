package com.example.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testAddSpecies() throws Exception {
		var body = "{\"id\":\"1\", \"name\":\"Elephant\"}";
		mockMvc.perform(post("/species")
			.contentType(MediaType.APPLICATION_JSON)
			.content(body))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.name").value("Elephant"));
	}
}
