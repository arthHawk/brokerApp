package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.StocksService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ClientsService clientsService;
    private final StocksService stocksService;

    @GetMapping("/admin-clients")
    public String getPortfoliosPage(Model model) {
        List<ClientDto> clients = clientsService.getAllClients();
        model.addAttribute("clientsAttr", clients);
        return "adminClients";
    }

    @PostMapping("/admin-add-client")
    public String adminAddClient(ClientForm form) {
        clientsService.addClient(form);
        return "redirect:/admin-clients";
    }

    @PostMapping("/admin-clients/{client-id}/admin-delete")
    public String adminDeleteUser(@PathVariable("client-id") Integer clientId) {
        clientsService.deleteClient(clientId);
        return "redirect:/admin-clients";
    }

    @GetMapping("/admin-clients/{client-id}")
    public String adminGetClientsPage(Model model, @PathVariable("client-id") Integer clientId) {
        ClientDto clientDto = clientsService.getClient(clientId);
        model.addAttribute("client", clientDto);
        return "adminClientPersonalPage";
    }

    @PostMapping("/admin-clients/{client-id}/admin-update")
    public String adminUpdateUser(@PathVariable("client-id") Integer clientId) {

        return "redirect:/admin-clients";
    }

    @GetMapping("/admin-clients/{client-id}/portfolio")
    public String adminGetPortfolioByClient(Model model, @PathVariable("client-id") Integer clientId) {
        List<ClientPortfolioDto> portfolio = stocksService.getPortfolioInformation(clientId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("portfolio", portfolio);
        return "adminPortfoliosOfClient";
    }
}
