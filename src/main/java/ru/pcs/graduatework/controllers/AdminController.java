package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.forms.PortfolioForm;
import ru.pcs.graduatework.model.Client;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.StocksService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ClientsService clientsService;
    private final StocksService stocksService;



    @GetMapping("/adminClients")
    public String getPortfoliosPage(Model model) {
        List<Client> clients = clientsService.getAllClients();
        model.addAttribute("clientsAttr", clients);
        return "adminClients";
    }

    @PostMapping("/adminAddClient")
    public String adminAddClient(ClientForm form) {
        clientsService.addClient(form);
        return "redirect:/adminClients";
    }

    @PostMapping("/adminClients/{client-id}/adminDelete")
    public String adminDeleteUser(@PathVariable("client-id") Integer clientId) {
        clientsService.deleteClient(clientId);
        return "redirect:/adminClients";
    }

    @GetMapping("/adminClients/{client-id}")
    public String adminGetClientsPage(Model model, @PathVariable("client-id") Integer clientId) {
        Client client = clientsService.getClient(clientId);
        model.addAttribute("client", client);
        return "adminClientPersonalPage";
    }

    @PostMapping("/adminClients/{client-id}/adminUpdate")
    public String adminUpdateUser(@PathVariable("client-id") Integer clientId) {

        return "redirect:/adminClients";
    }

    @GetMapping("/adminClients/{client-id}/portfolio")
    public String adminGetPortfolioByClient(Model model, @PathVariable("client-id") Integer clientId) {
        List<PortfolioForm> portfolio = stocksService.getPortfolioInformation(clientId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("portfolio", portfolio);
        return "adminPortfoliosOfClient";
    }
}
