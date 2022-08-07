package tacos;

import static org.hamcrest.Matchers.containsString;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))

                //HTTP 200(OK) 상태.
                .andExpect(status().isOk())

                //뷰 이름 home
                .andExpect(view().name("home"))

                //브라우저에 보이는 뷰에는 반드시 "Welcome to..."가 포함되어있어야 함.
                .andExpect(content().string(
                        containsString("Welcome to...")));
    }
}