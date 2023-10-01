package son.playground.imagecard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import son.playground.imagecard.domain.Card;
import son.playground.imagecard.repository.CardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> getCardAll() {
        return this.cardRepository.findAll();
    }

    // 수정 날짜 기준 내림차순
    public List<Card> getCardAllByModifiedAtDesc() {
        return this.cardRepository.findAllByOrderByModifiedAtDesc();
    }

    public void deleteCard(Long id) {
        this.cardRepository.deleteById(id);
    }
}
