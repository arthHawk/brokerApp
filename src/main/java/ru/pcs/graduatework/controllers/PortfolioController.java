package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.PortfolioService;
import ru.pcs.graduatework.service.StocksService;

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
        ClientDto clientDto = clientsService.searchClientByLogin(login);
        List<ClientPortfolioDto> portfolio = stocksService.getPortfolioInformation(clientDto.getId());
        model.addAttribute("client", clientDto);
        model.addAttribute("portfolio", portfolio);
        if (portfolio.size() > 0) {
            return "personal-portfolioPage";
        } else {
            return "personal-portfolioPage-stocksZero";
        }
    }

}
