package de.cookyapp.web.controller;

import de.cookyapp.service.services.interfaces.ICategoryCrudService;
import de.cookyapp.web.viewmodel.Category;
import de.cookyapp.web.viewmodel.cookbook.Cookbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasper on 01.06.2016.
 */
@Controller
@RequestMapping( "/cookingMatches" )
public class CookingMatchController {
    private ICategoryCrudService categoryCrudService;

    @Autowired
    public CookingMatchController (ICategoryCrudService categoryCrudService) {
        this.categoryCrudService = categoryCrudService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView showCategories () {
        ModelAndView modelAndView = new ModelAndView("CategoryTile");
        List<Category> categories = categoryCrudService.getAllCategories()
                .stream()
                .map( category -> new Category( category ) )
                .collect( Collectors.toList() );
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}
