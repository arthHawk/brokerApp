package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pcs.graduatework.dto.ClientDto;
import ru.pcs.graduatework.dto.ClientPortfolioDto;
import ru.pcs.graduatework.forms.ClientForm;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.StocksService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final ClientsService clientsService;
    private final StocksService stocksService;

    @GetMapping("/signUp")
    public String registrationClient(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }
        model.addAttribute("clientForm", new ClientForm());
        model.addAttribute("isLoginUnique", true);

        return "signUp";
    }

    @PostMapping("/signUp")
    public String addClient(Model model, @Valid ClientForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("clientForm", form);
            model.addAttribute("isLoginUnique", true);
            return "signUp";
        }
        if (!clientsService.isLoginUnique(form.getLogin())) {
            model.addAttribute("clientForm", form);
            model.addAttribute("isLoginUnique", false);
            return "signUp";
        }
        clientsService.addClient(form);
        ClientDto clientDto = clientsService.searchClientByLogin(form.getLogin());
        List<ClientPortfolioDto> portfolio = stocksService.getPortfolioInformation(clientDto.getId());
        model.addAttribute("client", clientDto);
        model.addAttribute("portfolio", portfolio);
        return "personal-portfolioPage-stocksZero";
    }
}
