package ru.pcs.graduatework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pcs.graduatework.service.ClientsService;
import ru.pcs.graduatework.service.StocksService;

@Controller
@RequiredArgsConstructor
public class SignInController {

    private final ClientsService clientsService;
    private final StocksService stocksService;

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

//    @PostMapping("/signIn")
//    public String loginClient(Model model, ClientForm form) {
//        Client client = clientsService.searchClientByForm(form.getLogin());
//        try {
//            if (clientsService.authorization(client, form)) {
//                List<PortfolioForm> portfolio = stocksService.getPortfolioInformation(client.getId());
//                model.addAttribute("client", client);
//                if (portfolio.size() > 0) {
//                    model.addAttribute("portfolio", portfolio);
//                    return "personal-portfolioPage";
//                } else {
//                    return "personal-portfolioPage-stocksZero";
//                }
//            } else {
//                return "signIn";
//            }
//        } catch (RuntimeException e) {
//            return "signIn";
//        }
//    }
}
