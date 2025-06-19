package com.example.personaladminservice;

import com.example.personaladminservice.controller.PersonalAdminController;
import com.example.personaladminservice.model.PersonalAdmin;
import com.example.personaladminservice.repository.PersonalAdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import java.util.Arrays;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc // Need this for MockMvc when using @SpringBootTest
@ActiveProfiles("test") // Activate the 'test' profile
class PersonalAdminServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Use MockBean for repository if you don't want to hit actual DB in this specific test slicing
    private PersonalAdminRepository personalAdminRepository;
    // If you were using @WebMvcTest(PersonalAdminController.class) instead of @SpringBootTest,
    // then PersonalAdminRepository would be mocked by default if not provided.
    // With @SpringBootTest, we need to explicitly mock it if we want to avoid DB interaction for certain tests.

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }

    @Test
    void whenGetAllAdmins_thenReturnJsonArray() throws Exception {
        PersonalAdmin admin1 = new PersonalAdmin(1L, "Admin One", "admin1@example.com");
        PersonalAdmin admin2 = new PersonalAdmin(2L, "Admin Two", "admin2@example.com");

        given(personalAdminRepository.findAll()).willReturn(Arrays.asList(admin1, admin2));

        mockMvc.perform(get("/admins")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(admin1.getName())))
                .andExpect(jsonPath("$[1].name", is(admin2.getName())));
    }

    @Test
    void whenGetAllAdmins_andNoAdmins_thenReturnNoContent() throws Exception {
        given(personalAdminRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/admins")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
