package com.davi.museumfinder.solution;

import com.davi.museumfinder.dto.CollectionTypeCount;
import com.davi.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@DisplayName("Testes da CollectionTypeController")
@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CollectionTypeService service;

  @Test
  @DisplayName("Testando a rota get")
  void getTest() throws Exception {
    String[] collectionTypes = new String[]{"hist"};
    CollectionTypeCount count = new CollectionTypeCount(collectionTypes, 4);
    Mockito
        .when(service.countByCollectionTypes("hist"))
        .thenReturn(count);

    ResultActions result = mockMvc.perform(get("/collections/count/hist"));
    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes[0]").value("hist"))
        .andExpect(jsonPath("$.count").value(4));;
  }
}
