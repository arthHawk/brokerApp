package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;
import ru.pcs.graduatework.model.Stock;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.PortfolioService;
import ru.pcs.graduatework.service.StocksService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/portfolio")
public class PortfolioController {

    private final ClientsService clientsService;
    private final StocksService stocksService;
    private final PortfolioService portfolioService;

// New method
    // Authentication выгружает данные о пользователе из SpringSecurityContext
    @GetMapping
    public String portfolioPage(Authentication authentication, Model model) {
        if (authentication == null) {
            return "signIn";
        }
        String login = authentication.getName();
        Client client = clientsService.searchClientByLogin(login);
        List<PortfolioForm> portfolio = stocksService.getPortfolioInformation(client.getId());
        model.addAttribute("client", client);
        model.addAttribute("portfolio", portfolio);
        if (portfolio.size() > 0) {
            return "personal-portfolioPage";
        } else {
            return "personal-portfolioPage-stocksZero";
        }
    }

}
