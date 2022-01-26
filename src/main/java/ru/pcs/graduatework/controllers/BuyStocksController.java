package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.model.Portfolio;
import ru.pcs.graduatework.model.Stock;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.PortfolioService;
import ru.pcs.graduatework.service.StocksService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/portfolio/{client-id}/stocks/buy")
public class BuyStocksController {

    private final ClientsService clientsService;
    private final StocksService stocksService;
    private final PortfolioService portfolioService;

    @GetMapping
    public String buyStocksPage(Model model, @PathVariable("client-id") Integer clientId) {
        Client client = clientsService.getClient(clientId);
        List<Stock> stocks = stocksService.getAllStocks();
        model.addAttribute("client", client);
        model.addAttribute("stocks", stocks);
        return "personal-buy-stocks";
    }

    @GetMapping("/{stock-id}")
    public String buyStocksPageCount(Model model,
                                     @PathVariable("client-id") Integer clientId,
                                     @PathVariable("stock-id") Integer stockId) {
        Client client = clientsService.getClient(clientId);
        Stock stock = stocksService.getStock(stockId);
        model.addAttribute("client", client);
        model.addAttribute("stock", stock);
        return "personal-buy-stocks-count";
    }

    @PostMapping("/{stock-id}")
    public String buyStock(Model model,
                           @PathVariable("client-id") Integer clientId,
                           @PathVariable("stock-id") Integer stockId,
                           @RequestParam("stocksCount") Integer stocksCount) {
        Client client = clientsService.getClient(clientId);
        Stock stock = stocksService.getStock(stockId);
        if (portfolioService.addStocks(client, stock, stocksCount)) {
            List<PortfolioForm> portfolio = stocksService.getPortfolioInformation(client.getId());
            model.addAttribute("client", client);
            model.addAttribute("portfolio", portfolio);
            return "personal-portfolioPage";
        } else {
            model.addAttribute("client", client);
            model.addAttribute("stock", stock);
            return "personal-buy-stocks-count-notEnoughCash";
        }
    }

}
