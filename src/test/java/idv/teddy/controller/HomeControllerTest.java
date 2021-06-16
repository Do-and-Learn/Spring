package idv.teddy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testHelloWithValidUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(xpath("normalize-space()").string("哩賀!"));
    }

    @Test
    @WithAnonymousUser
    public void testHelloWithInvalidUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(unauthenticated());
    }
}