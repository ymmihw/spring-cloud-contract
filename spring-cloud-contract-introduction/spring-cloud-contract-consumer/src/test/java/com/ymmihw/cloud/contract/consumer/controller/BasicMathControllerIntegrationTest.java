package com.ymmihw.cloud.contract.consumer.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubsMode.LOCAL,
    ids = "com.ymmihw.spring.cloud.contract:spring-cloud-contract-producer:+:stubs:8090")
public class BasicMathControllerIntegrationTest {

  public BasicMathControllerIntegrationTest() {
    System.setProperty("maven.repo.local", "D:\\LocalRepository");
  }

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculate?number=2")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().string("Even"));
  }

  @Test
  public void given_WhenPassOddNumberInQueryParam_ThenReturnOdd() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculate?number=1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().string("Odd"));
  }
}
