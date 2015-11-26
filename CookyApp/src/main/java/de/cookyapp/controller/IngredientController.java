package de.cookyapp.controller;

import de.cookyapp.persistence.entities.IngredientEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.cookyapp.persistence.dao.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IngredientController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping("/ingredients")
    public String printIngredient(ModelMap model) {
        IngredientDao dao = new IngredientDao();
        model.addAttribute(dao.getAllIngredients());
        return "ingredients";
    }

    @RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
    public String handleAddIngredient(HttpServletRequest req) {
        System.out.println("In Add Ingredient");
        if(req.getParameter("newIngredient") != "") {
            IngredientDao dao = new IngredientDao();
            IngredientEntity ingredient = new IngredientEntity();
            System.out.println("Ingredient: " + req.getParameter("newIngredient"));
            ingredient.setName(req.getParameter("newIngredient"));
            dao.insertIngredient(ingredient);
        }
        return "redirect:/ingredients";
    }

    @RequestMapping(value = "/removeIngredient", method = RequestMethod.POST)
    public String handleRemoveIngredients(HttpServletRequest req) {
        if (req.getParameter("removeID") != null) {
            System.out.println("In Remove Ingredients");
            IngredientDao dao = new IngredientDao();
            String remove = req.getParameter("removeID");
            System.out.println(remove);
            int removeId = Integer.parseInt(remove);
            dao.removeIngredient(removeId);
        }
        return "redirect:/ingredients";
    }

    @RequestMapping(value="/editIngredient", method = RequestMethod.POST)
    public String handleEditIngredient(HttpServletRequest req) {
        if(req.getParameter("editedId") != null && req.getParameter("editName") != null) {
            System.out.println("In Edit");
            IngredientDao dao = new IngredientDao();
            String edit = req.getParameter("editedId");
            String newName = req.getParameter("editName");
            System.out.println("id " + edit);
            System.out.println("name " + newName);
            int editId = Integer.parseInt(edit);
            dao.editIngredient(editId, newName);
        }
        return "redirect:/ingredients";
    }

    @RequestMapping(value="/edit")
    public String handleEdit (ModelMap model, HttpServletRequest req) {
        System.out.println("In Edit");
        String id = req.getParameter("editId");
        int nId = Integer.parseInt(id);
        IngredientDao dao = new IngredientDao();
        model.addAttribute(dao.getIngredientById(nId));
        System.out.println("Ingredient: " + dao.getIngredientById(nId).getName());
        return "edit";
    }

    @RequestMapping(value="/home")
    public String handleHome () {
        return "home";
    }
}