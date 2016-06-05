package de.cookyapp.web.controller;

import de.cookyapp.service.dto.User;
import de.cookyapp.service.dto.UserPreference;
import de.cookyapp.service.services.interfaces.ICategoryCrudService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.service.services.interfaces.IUserPreferenceCrudService;
import de.cookyapp.web.viewmodel.Matches.Category;
import de.cookyapp.web.viewmodel.Matches.CategoryList;
import de.cookyapp.web.viewmodel.Matches.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasper on 01.06.2016.
 */
@Controller
@RequestMapping( "/matchCenter" )
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

    @RequestMapping(method =RequestMethod.GET)
    public String matchCenter () {
        String view = "MatchCenterTile";
        return view;
    }

    @RequestMapping( "/categoriesOverview" )
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
            view = "redirect:/matchCenter";
        }
        return view;
    }

    @RequestMapping ("/userPreferences")
    public ModelAndView userPreferencesOverview () {
        ModelAndView modelAndView = new ModelAndView("UserPreferencesOverviewTile");
        User user = userService.getCurrentUser();
        List<UserPreferences> userPreferences = preferenceCrudService.getPreferencesByUserId(user.getId()).stream()
                .map(entity -> new UserPreferences(entity))
                .collect(Collectors.toList());
        modelAndView.addObject("userPreferences", userPreferences);
        return modelAndView;
    }

    @RequestMapping ("/remove/{id}")
    public String removePreference (@PathVariable int id) {
        String view = "";
        preferenceCrudService.deletePreference(id);
        view = "redirect:/userPreferences";
        return view;
    }

    @RequestMapping ("/matches")
    public ModelAndView matchOverview () {
        ModelAndView modelAndView = new ModelAndView("MatchTile");
        User user = userService.getCurrentUser();
        List<UserPreference> userPreferences = preferenceCrudService.getPreferencesByUserId(user.getId());
        List<String> categories = new ArrayList<>();
        for (UserPreference current : userPreferences) {
            categories.add(current.getCategoryName());
        }
        List<UserPreference> matches = preferenceCrudService.getMatches(categories);
        List<User> users = new ArrayList<>();
        for (UserPreference current : matches) {
            users.add(userService.getUserByID(current.getUserId()));
        }
        HashSet<User> userHashSet = new HashSet<>(users);
        modelAndView.addObject("usersList", userHashSet);
        return modelAndView;
    }
}
