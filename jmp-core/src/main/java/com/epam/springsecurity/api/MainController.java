package com.epam.springsecurity.api;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import com.epam.springsecurity.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to manage the main endpoints in the application
 */
@Controller
public class MainController {

    private final UsersService usersService;

    public MainController(UsersService usersService) {this.usersService = usersService;}

    @GetMapping("/login")
    public String getLoginPage(final ModelMap model, @RequestParam("error") final Optional<String> error) {
        error.ifPresent(e -> model.addAttribute("error", e));
        return "login";
    }

    @GetMapping("/logoutSuccess")
    public String getLogoutSuccessPage() {
        return "logout";
    }

    @GetMapping("/info")
    public String getInfoPage() {
        return "info";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/blocked")
    public String getBlockedUsersPage(Model model) {
        Map<String, LocalDateTime> blockedUsers = usersService.getBlockedUsers();
        if (!blockedUsers.isEmpty()) {
            model.addAttribute("blockedUsers", blockedUsers);
        }
        return "blocked";
    }

}
