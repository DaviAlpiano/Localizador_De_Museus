package com.davi.museumfinder.solution;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.davi.museumfinder.model.Museum;
import com.davi.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@DisplayName("Testes da Controller")
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private MuseumService service;

  @Test
  @DisplayName("Testando a rota get para id")
  void getTest() throws Exception {
    Long id = 1L;
    Museum museum = new Museum();
    museum.setId(id);
    Mockito
        .when(service.getMuseum(id))
        .thenReturn(museum);

    ResultActions result = mockMvc.perform(get("/museums/museums/{id}", id));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"));
  }
}
