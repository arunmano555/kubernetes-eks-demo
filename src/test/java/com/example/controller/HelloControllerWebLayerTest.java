package com.example.controller;

import com.example.service.HelloServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = HelloController.class,
excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class HelloControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HelloServiceImpl helloService;

    @Test
    @DisplayName("Display Hello")
    void testHello_whenCalled_thenReturnsHello() throws Exception {
        //arrange
        RequestBuilder  requestBuilder = MockMvcRequestBuilders.get("/api/hello");
        String expectedResponse = "Hello from Spring Boot API!";
        when(helloService.hello()).thenReturn(expectedResponse);
        //act
        MvcResult   result = mockMvc.perform(requestBuilder).andReturn();
        String response = result.getResponse().getContentAsString();

        //assert
        assertEquals(expectedResponse, response);
    }
}
