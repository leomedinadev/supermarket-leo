package ec.com.leodev.supermarket.security;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectRequestsWithoutToken() throws Exception {
        mockMvc.perform(get("/products/all")).andExpect(status().isForbidden());
    }

    @Test
    void shouldRejectInvalidTokensWithoutFailing() throws Exception {
        for (String authorization : new String[]{"Bearer", "Bearer ", "Bearer no-es-un-jwt", "Basic abc"}) {
            mockMvc.perform(get("/products/all").header(HttpHeaders.AUTHORIZATION, authorization))
                    .andExpect(status().isForbidden());
        }
    }

    @Test
    void shouldRejectWrongCredentials() throws Exception {
        mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"admin\", \"password\": \"otra-clave\"}"))
                .andExpect(status().isForbidden());
        mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"otro\", \"password\": \"clave-de-prueba\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldGiveAccessWithTheTokenFromLogin() throws Exception {
        String response = mockMvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"admin\", \"password\": \"clave-de-prueba\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").isNotEmpty())
                .andReturn().getResponse().getContentAsString();
        String jwt = JsonPath.read(response, "$.jwt");

        mockMvc.perform(get("/products/all").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
                .andExpect(status().isOk());
    }

    @Test
    void shouldKeepTheApiDocsPublic() throws Exception {
        mockMvc.perform(get("/v3/api-docs")).andExpect(status().isOk());
    }
}
