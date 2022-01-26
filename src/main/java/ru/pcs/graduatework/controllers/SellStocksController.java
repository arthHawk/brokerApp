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
@RequestMapping("/portfolio/{client-id}/stocks/sell")
@RequiredArgsConstructor
public class SellStocksController {

    private final PortfolioService portfolioService;
    private final StocksService stocksService;
    private final ClientsService clientsService;


    @GetMapping("/{pf-ticker}")
    public String sellStocksPageCount(Model model,
                                      @PathVariable("client-id") Integer clientId,
                                      @PathVariable("pf-ticker") String ticker) {
        Integer portfolioId = portfolioService.searchPortfolioIdByClientIdAndTicker(clientId, ticker);
        List<Stock> stocks = stocksService.findAllByTicker(ticker);
        Stock stock = stocks.get(0);
        Portfolio portfolio = portfolioService.getByID(portfolioId);
        Client client = clientsService.getClient(clientId);
        model.addAttribute("client", client);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("stock", stock);
        return "personal-sell-stocks-count";
    }

    @PostMapping("/{portfolio-id}")
    public String sellStock(Model model,
                            @PathVariable("client-id") Integer clientId,
                            @PathVariable("portfolio-id") Integer portfolioId,
                            @RequestParam("stocksCount") Integer stocksCount,
                            @RequestParam("stock-id") Integer stockId) {
        Client client = clientsService.getClient(clientId);
        Portfolio portfolio = portfolioService.getByID(portfolioId);
        Stock stock = stocksService.getById(stockId);
        if (portfolioService.subtractStocks(client, stock, portfolio, stocksCount)) {
            List<PortfolioForm> portfolios = stocksService.getPortfolioInformation(client.getId());
            model.addAttribute("client", client);
            model.addAttribute("portfolio", portfolios);
            return "personal-portfolioPage";
        } else {
            model.addAttribute("client", client);
            model.addAttribute("portfolio", portfolio);
            model.addAttribute("stock", stock);
            return "personal-sell-stocks-count-incorrect-stocksCount";
        }
    }
}
