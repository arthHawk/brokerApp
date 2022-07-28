package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.dto.StockDto;
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
        ClientDto clientDto = clientsService.getClient(clientId);
        List<StockDto> stockDto = stocksService.getAllStocks();
        model.addAttribute("client", clientDto);
        model.addAttribute("stocks", stockDto);
        return "personal-buy-stocks";
    }

    @GetMapping("/{stock-id}")
    public String buyStocksPageCount(Model model,
                                     @PathVariable("client-id") Integer clientId,
                                     @PathVariable("stock-id") Integer stockId) {
        ClientDto clientDto = clientsService.getClient(clientId);
        StockDto stockDto = stocksService.getStock(stockId);
        model.addAttribute("client", clientDto);
        model.addAttribute("stock", stockDto);
        return "personal-buy-stocks-count";
    }

    @PostMapping("/{stock-id}")
    public String buyStock(Model model,
                           @PathVariable("client-id") Integer clientId,
                           @PathVariable("stock-id") Integer stockId,
                           @RequestParam("stocksCount") Integer stocksCount) {
        ClientDto clientDto = clientsService.getClient(clientId);
        StockDto stockDto = stocksService.getStock(stockId);
        if (portfolioService.addStocks(clientDto, stockDto, stocksCount)) {
            List<ClientPortfolioDto> portfolio = stocksService.getPortfolioInformation(clientDto.getId());
            model.addAttribute("client", clientDto);
            model.addAttribute("portfolio", portfolio);
            return "personal-portfolioPage";
        } else {
            model.addAttribute("client", clientDto);
            model.addAttribute("stock", stockDto);
            return "personal-buy-stocks-count-notEnoughCash";
        }
    }

}
