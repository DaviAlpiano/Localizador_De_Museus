package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Testes da Service")
@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private MuseumFakeDatabase museumFakeDatabase;

  @Test
  @DisplayName("Testando o retorno do getMuseum")
  void getMuseum() throws Exception {
    Long id = 1L;
    Museum museum = new Museum();
    museum.setId(id);
    MuseumService museumService = new MuseumService(museumFakeDatabase);
    Mockito.when(museumFakeDatabase.getMuseum(id)).thenReturn(Optional.of(museum));

    Museum result = museumService.getMuseum(1L);

    assertEquals(1L, result.getId());
  }

  @Test
  @DisplayName("Testando se lança a Ex")
  void testExceptionMessage() {
    MuseumService museumService = new MuseumService(museumFakeDatabase);

    Exception exception = assertThrows(MuseumNotFoundException.class, () -> {
      museumService.getMuseum(1L);
    });

    String expectedMessage = "Museu não encontrado!";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
