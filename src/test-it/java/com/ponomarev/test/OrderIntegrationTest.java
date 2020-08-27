package com.ponomarev.test;

import static com.ponomarev.JsonUtils.parseToJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ponomarev.web.dto.CreateOrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class OrderIntegrationTest {

  private static final String TEST_CONSUMER_NAME = "Test consumer";
  private static final String TEST_PRODUCER_NAME = "Test producer";

  @Autowired private MockMvc mockMvc;

  @Test
  public void shouldCreateOrder() throws Exception {
    CreateOrderDto order = new CreateOrderDto(TEST_PRODUCER_NAME, TEST_CONSUMER_NAME);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseToJson(order)))
        .andExpect(status().isCreated());
  }
}
