package webapp.controllers;

// Import framework depenencies  - required to handle the HTTP request and send a response
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import webapp.models.*;
import webapp.dao.ProductDao;
import webapp.dao.CategoryDao;


// The @ annotation identifies this as a Controller class
@Controller
public class ApplicationController {

    // Dao instance for Product
    @Autowired
    private ProductDao productData;

    // Dao Instance for Category
    @Autowired
    private CategoryDao categoryData;

    // index accepts a request parameter, named name,  from the address URL
    // the parameter is optional and has a default value if not provided
    // String name will be assigned the param value
    // Model model is used to pass data to the view
    // This method, index(), serves as the site index - the default page
    // Requests for the root '/' will be handled by this method
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        // add name and its value to the view model object
        model.addAttribute("name", name);

        //Load and return the index view
        return "index";
    }

    // The about page will be accessed using /about from the browser
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {

        //Load and return the about view
        return "about";
    }

    // This method displays the product page
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    // Uses a Model instance - which will be passed to a view
    // cat parameter is for category id
    public String products(@RequestParam(name = "cat", required = false, defaultValue = "0") String cat, Model model){

        List<Product> products;

        // Initialise id (default value used to get all products)
        int id = 0;

        // The parameter may be not be valid - which could crash the application
        // This trys to parse the string converting it to an it
        // If successfull id will be assigned the cat value
        // Otherwise - catch any exception
        // If it fails (i.e an exception occurs) id value will not be changed (from 0).
        try { 
            id = Integer.parseInt(cat); 
         }
        catch(NumberFormatException e) {
            System.out.println("Bad input for cat id: " + e);
        }

        // If id is 0 then get all products otherwise get products for cat id
        if (id == 0) {
            // Get the product list from the ProductDao instance
            products = productData.findAll();
        } else {
            products = productData.findByCategory(id);
        }


        // Get the product list from the ProductDao instance
        List<Category> categories = categoryData.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        // Return the view
        return "products";
    }
}
