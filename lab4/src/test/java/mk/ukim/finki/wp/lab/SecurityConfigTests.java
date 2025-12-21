package mk.ukim.finki.wp.lab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenAnonymousAccessAddDish_thenRedirectsToLogin() throws Exception {
        this.mockMvc.perform(get("/dishes/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void whenAdminAccessAddDish_thenOk() throws Exception {
        this.mockMvc.perform(get("/dishes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("dish-form"));
    }
}
