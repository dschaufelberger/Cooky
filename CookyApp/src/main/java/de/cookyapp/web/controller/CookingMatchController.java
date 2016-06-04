package de.cookyapp.web.controller;

import de.cookyapp.service.dto.User;
import de.cookyapp.service.dto.UserPreference;
import de.cookyapp.service.services.interfaces.ICategoryCrudService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.service.services.interfaces.IUserPreferenceCrudService;
import de.cookyapp.web.viewmodel.Matches.Category;
import de.cookyapp.web.viewmodel.Matches.CategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasper on 01.06.2016.
 */
@Controller
@RequestMapping( "/cookingMatches" )
public class CookingMatchController {
    private ICategoryCrudService categoryCrudService;
    private IUserPreferenceCrudService preferenceCrudService;
    private IUserCrudService userService;


    @Autowired
    public CookingMatchController (ICategoryCrudService categoryCrudService, IUserPreferenceCrudService preferenceCrudService, IUserCrudService userService) {
        this.categoryCrudService = categoryCrudService;
        this.preferenceCrudService = preferenceCrudService;
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView showCategories () {
        ModelAndView modelAndView = new ModelAndView("CategoryTile");
        List<Category> categories = categoryCrudService.getAllCategories().stream()
                .map( category -> new Category( category ) )
                .collect( Collectors.toList() );
        CategoryList categoryList = new CategoryList(categories);
        modelAndView.addObject("categories", categoryList);

        return modelAndView;
    }

    @RequestMapping ("/addPreferences")
    public String addPreference (@ModelAttribute("categories") @Valid CategoryList categories)  {
        String view = "";
        User user = userService.getCurrentUser();
        if (categories != null) {
            List<UserPreference> preferences = new ArrayList<>();
            for (String current : categories.getUserCategories()) {
                UserPreference preference = new UserPreference();
                preference.setUserId(user.getId());
                preference.setCategoryName(current);
                preferences.add(preference);
            }
            preferenceCrudService.savePreferences(preferences);
            view = "redirect:/cookingMatches";
        }
        return view;
    }
}
