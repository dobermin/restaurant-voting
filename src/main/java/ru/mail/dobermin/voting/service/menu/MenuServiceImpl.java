package ru.mail.dobermin.voting.service.menu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.entity.Menu;
import ru.mail.dobermin.voting.repository.MenuRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getById(String id) {
        return menuRepository.getById(Long.valueOf(id));
    }

    @Override
    public Menu update(String id, Menu menu) {
        Menu menuOptional = menuRepository.findById(Long.valueOf(id)).orElseThrow();
        if (Objects.equals(menuOptional.getId(), menu.getId())) {
            menuOptional.setDishes(menu.getDishes());
            menuOptional.setRestaurant(menu.getRestaurant());
            menuRepository.save(menuOptional);
        }
        return menuOptional;
    }

    @Override
    public List<Menu> saveAll(List<Menu> menus) {
        return menuRepository.saveAll(menus);
    }

    @Override
    public void delete(String id) {
        menuRepository.deleteById(Long.valueOf(id));
    }
}
