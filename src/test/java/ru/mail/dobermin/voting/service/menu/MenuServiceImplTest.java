package ru.mail.dobermin.voting.service.menu;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mail.dobermin.voting.ControllerTest;
import ru.mail.dobermin.voting.entity.Menu;
import ru.mail.dobermin.voting.repository.MenuRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenuServiceImplTest extends ControllerTest {

    @Mock
    private MenuRepository menuRepository;

    @Test
    void getAll() {
        when(menuRepository.findAll()).thenReturn(getMenus());
        List<Menu> result = getMenuService().getAll();
        assertEquals(getMenus().size(), result.size());
    }

    @Test
    void getById() {
        Long id = getId();
        Menu newMenus = getMenus().get(0);
        newMenus.setId(id);
        when(menuRepository.getById(id)).thenReturn(newMenus);
        Menu result = getMenuService().getById(String.valueOf(id));
        assertEquals(id, result.getId());
    }

    @Test
    void update() {
        Long id = getId();
        Menu newMenu = getMenus().get(0);
        newMenu.setId(id);
        when(menuRepository.findById(id)).thenReturn(java.util.Optional.of(newMenu));
        when(menuRepository.save(newMenu)).thenReturn(newMenu);
        Menu result = getMenuService().update(String.valueOf(getId()), newMenu);
        assertEquals(newMenu.getId(), result.getId());
    }

    @Test
    void saveAll() {
        List<Menu> menus = getMenus().stream().toList();
        when(menuRepository.saveAll(menus)).thenReturn(menus);
        List<Menu> result = getMenuService().saveAll(menus);
        assertEquals(menus.size(), result.size());
    }

    @Test
    void delete() {
        Long id = getId();
        getMenuService().delete(String.valueOf(id));
        verify(menuRepository).deleteById(id);
    }
}