package marchingfood.tqs.ua.service;

import marchingfood.tqs.ua.model.Menu;
import marchingfood.tqs.ua.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public void save(Menu menu) {
        menuRepository.save(menu);
    }
    public List<Menu> getMenus(){return menuRepository.findAll();}
    public Menu getMenuById(long menuId){return menuRepository.findById(menuId);}
    public void tryDelete(long id) {
        Menu menu = menuRepository.findById(id);
        if (menu==null){ throw new ResourceNotFoundException("Menu with id "+id+ " was not found");}
        menuRepository.delete(menu);
    }

    public void edit(long id, Menu menu) {
        Menu preExisting = menuRepository.findById(id);
        if (preExisting==null){throw new ResourceNotFoundException("Menu with id "+id+ " was not found");}
        preExisting.setDescription(menu.getDescription());
        preExisting.setName(menu.getName());
        preExisting.setPrice(menu.getPrice());
        preExisting.setImageurl(menu.getImageurl());
        menuRepository.save(preExisting);
    }
}
