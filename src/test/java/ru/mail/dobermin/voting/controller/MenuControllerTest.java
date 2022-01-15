package ru.mail.dobermin.voting.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mail.dobermin.voting.ControllerTest;
import ru.mail.dobermin.voting.service.menu.MenuServiceImpl;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class MenuControllerTest extends ControllerTest {

    @MockBean
    private MenuServiceImpl menuService;

    @Test
    void getAll() throws Exception {
        when(menuService.getAll()).thenReturn(getMenus());
        String result = getMvc().perform(
                        get(getUrlMenu())
                                .header("Authorization", getToken())
                                .contentType(APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        assertTrue(result.contains("Суп"));
        Assertions.assertFalse(result.contains("Суппп"));
    }

    @Test
    void getById() throws Exception {
        when(menuService.getById(String.valueOf(getId()))).thenReturn(getMenus().get(0));
        String result = getMvc().perform(
                        get(getUrlMenu() + "/" + getId())
                                .header("Authorization", getToken())
                                .contentType(APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        assertTrue(result.contains("Суп"));
        Assertions.assertFalse(result.contains("Суппп"));
    }
}