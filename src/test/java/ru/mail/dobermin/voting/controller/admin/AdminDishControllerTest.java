package ru.mail.dobermin.voting.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mail.dobermin.voting.ControllerTest;
import ru.mail.dobermin.voting.entity.Dish;
import ru.mail.dobermin.voting.service.dish.DishServiceImpl;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AdminDishControllerTest extends ControllerTest {

    @MockBean
    private DishServiceImpl dishService;

    @Test
    void getAll() throws Exception {
        when(dishService.getAll()).thenReturn(getDishes());
        String result = getMvc().perform(
                        get(getUrlAdminDish())
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
    void create() throws Exception {
        when(dishService.saveAll(getDishes())).thenReturn(getDishes());
        getMvc().perform(
                        post(getUrlAdminDish())
                                .content(getObjectMapper().writeValueAsString(getDishes()))
                                .header("Authorization", getToken())
                                .contentType(APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        Dish updateDish = getDishes().get(0);
        updateDish.setId(getId());
        when(dishService.update(String.valueOf(getId()), updateDish)).thenReturn(updateDish);
        getMvc().perform(
                        put(getUrlAdminDish() + "/" + getId())
                                .content(getObjectMapper().writeValueAsString(updateDish))
                                .header("Authorization", getToken())
                                .contentType(APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        getMvc().perform(
                        MockMvcRequestBuilders.delete(getUrlAdminDish() + "/" + getId())
                                .header("Authorization", getToken())
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}