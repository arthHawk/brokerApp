package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.StocksService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/portfolio/{client-id}/cash/add")
@RequiredArgsConstructor
public class AddCashController {

    private final ClientsService clientsService;
    private final StocksService stocksService;

    @GetMapping
    public String addCashToClientPage(Model model, @PathVariable("client-id") Integer clientId) {
        Client client = clientsService.getClient(clientId);
        model.addAttribute("client", client);
        return "personal-addCash";
    }

    @PostMapping
    public String addCashToClient(Model model, @PathVariable("client-id") Integer clientId,
                                  @RequestParam("addValue") BigDecimal addValue) {
        clientsService.addCash(clientId, addValue);
        Client client = clientsService.getClient(clientId);
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
