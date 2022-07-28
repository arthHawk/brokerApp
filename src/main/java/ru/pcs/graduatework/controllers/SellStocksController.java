package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.dto.StockDto;
import ru.pcs.graduatework.entities.PortfolioEntity;
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
        List<StockDto> stockDtoList = stocksService.findAllByTicker(ticker);
        StockDto stockDto = stockDtoList.get(0);
        PortfolioEntity portfolioEntity = portfolioService.getByID(portfolioId);
        ClientDto clientDto = clientsService.getClient(clientId);
        model.addAttribute("client", clientDto);
        model.addAttribute("portfolio", portfolioEntity);
        model.addAttribute("stock", stockDto);
        return "personal-sell-stocks-count";
    }

    @PostMapping("/{portfolio-id}")
    public String sellStock(Model model,
                            @PathVariable("client-id") Integer clientId,
                            @PathVariable("portfolio-id") Integer portfolioId,
                            @RequestParam("stocksCount") Integer stocksCount,
                            @RequestParam("stock-id") Integer stockId) {
        ClientDto clientDto = clientsService.getClient(clientId);
        PortfolioEntity portfolioEntity = portfolioService.getByID(portfolioId);
        StockDto stockDto = stocksService.getById(stockId);
        if (portfolioService.subtractStocks(clientDto, stockDto, portfolioEntity, stocksCount)) {
            List<ClientPortfolioDto> portfolios = stocksService.getPortfolioInformation(clientDto.getId());
            model.addAttribute("client", clientDto);
            model.addAttribute("portfolio", portfolios);
            return "personal-portfolioPage";
        } else {
            model.addAttribute("client", clientDto);
            model.addAttribute("portfolio", portfolioEntity);
            model.addAttribute("stock", stockDto);
            return "personal-sell-stocks-count-incorrect-stocksCount";
        }
    }
}
