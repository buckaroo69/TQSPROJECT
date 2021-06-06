package marchingfood.tqs.ua.service;

import marchingfood.tqs.ua.model.Menu;
import marchingfood.tqs.ua.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public void save(Menu menu) {
        menuRepository.save(menu);
    }
}
