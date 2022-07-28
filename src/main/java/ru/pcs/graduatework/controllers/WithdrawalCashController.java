package ru.pcs.graduatework.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.StocksService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/portfolio/{client-id}/cash/withdrawal")
@RequiredArgsConstructor
public class WithdrawalCashController {

    private final ClientsService clientsService;
    private final StocksService stocksService;

    @GetMapping
    public String withdrawalCashFromClientPage(Model model, @PathVariable("client-id") Integer clientId) {
        ClientDto clientDto = clientsService.getClient(clientId);
        model.addAttribute("client", clientDto);
        return "personal-withdrawalCash";
    }

    @PostMapping
    public String withdrawalCashFromClient(Model model, @PathVariable("client-id") Integer clientId,
                                           @RequestParam("withdrawalValue") BigDecimal withdrawalValue) {
        clientsService.withdrawalCash(clientId, withdrawalValue);
        ClientDto clientDto = clientsService.getClient(clientId);
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
