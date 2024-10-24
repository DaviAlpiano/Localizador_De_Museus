package com.davi.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.davi.museumfinder.database.MuseumFakeDatabase;
import com.davi.museumfinder.dto.CollectionTypeCount;
import com.davi.museumfinder.service.CollectionTypeService;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("Testes da CollectionTypeService")
@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeServiceTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private MuseumFakeDatabase museumFakeDatabase;

  @Test
  void countTest() {
    CollectionTypeService service = new CollectionTypeService(museumFakeDatabase);
    CollectionTypeCount count = service.countByCollectionTypes("hist");
    String[] expected = new String[]{"hist"};
    assertEquals(0, count.count());
    assertEquals(Arrays.toString(expected), Arrays.toString(count.collectionTypes()));
  }
}
