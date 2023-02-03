package com.betaplan.alberto.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.betaplan.alberto.bookclub.models.Book;
import com.betaplan.alberto.bookclub.models.LoginUser;
import com.betaplan.alberto.bookclub.models.User;
import com.betaplan.alberto.bookclub.services.BookService;
import com.betaplan.alberto.bookclub.services.UserService;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    // ///////////// INIT ////////////////////////////
    @GetMapping("/")
    public String init(HttpSession session) {
        System.out.println("///////////////////////INIT////////////////////////");
        System.out.println("//////////////// INIT SESSION :"+ session.getAttribute("user_id"));
        return "redirect:/home";
    }


    // ///////////// USER AND REGISTER ///////////////
    // ///////////////////////////////////////////////
    // ------------------- view -------------------
    @GetMapping("/initial")
    public String index(Model model, HttpSession session) {
        // Enlazar objetos User y LoginUser vacíos al JSP
        // para capturar la entrada del formulariocopy
        System.out.println("*************** INDEX ************");
        System.out.println("login en session: " + session.getAttribute("user_id"));
        if (session.getAttribute("user_id") != null) { // If user is in session
            return "redirect:/home"; // re-route to dashboard
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        System.out.println("------------- INDEX --------------");
        return "index";
    }

    // --------------- register user ------------
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
                           HttpSession session) {
        System.out.println("*************** POST REGISTER ************");
        // PARA HACER después: llamar a un método de registro en el servicio
        // ¡para hacer algunas validaciones adicionales y crear un nuevo usuario!

        userService.register(newUser, result);

        if (result.hasErrors()) {
            // Asegúrate de enviar el LoginUser vacío antes
            // de volver a renderizar la página.
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }

        // ¡Sin errores!
        // PARA HACER después: Almacena sus ID de la base de datos en sesión,
        // en otras palabras, inicia sus sesiones.
        session.setAttribute("user_id", newUser.getId());
        System.out.println("------------------ POST REGISTER --------------------");
        return "redirect:/home";
    }

    // ----------------- login -----------------

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
                        HttpSession session) {
        System.out.println("*************** LOGIN ************");
        // Añadir una vez implementado el servicio:
        // User user = userServ.login(newLogin, result);

        User user = userService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }

        // ¡Sin errores!
        // PARA HACER después: Almacena sus ID de la base de datos en sesión,
        // en otras palabras, inicia sus sesiones.
        session.setAttribute("user_id", user.getId());
        System.out.println("---------------- LOGIN ------------------");
        return "redirect:/home";
    }
    // ////// HOME //////////////////////////////////////////////////////

    @GetMapping("/home")
    public String dashboard(Model model, HttpSession session) {
        System.out.println("************** HOME *********** ");
        System.out.println("value ccreated session: " + session.getAttribute("user_id"));
        if (session.isNew() || session.getAttribute("user_id") == null) {
            return "redirect:/books/new";
        } else {

            // USER NAME
            User loggedInUser = userService.retrieveUser((Long) session.getAttribute("user_id"));
            model.addAttribute("loggedInUser", loggedInUser);

            // ALL BOOKS
            List<Book> books = bookService.allBooks();
            model.addAttribute("books", books);
            System.out.println("************** HOME *********** ");
            return "home";
        }
    }
    // ----------------------- logout ---------------------------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("************** LOGOUT *********** ");
        session.invalidate();
        System.out.println("----------------- LOGOUT ---------- ");
        return "redirect:/"; // then redirect to index
    }
}