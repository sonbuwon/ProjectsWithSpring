package son.playground.imagecard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import son.playground.imagecard.domain.Card;
import son.playground.imagecard.service.CardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class CardController {

    private final CardService cardService;

    @GetMapping("/list")
    public String getList(Model model) {
        List<Card> cards = this.cardService.getCardAll();
        model.addAttribute("cards", cards);
        return "/cardList";
    }
}
