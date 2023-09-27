package son.playground.imagecard.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import son.playground.imagecard.domain.Card;

@SpringBootTest
@Log4j2
public class CardRepositoryTests {

    @Autowired
    private CardRepository repository;

    @Test
    public void saveTest() {
        Card card = new Card();
        card.setTitle("Temp Title");
        card.setImagePath("Temp Image Path");

        this.repository.save(card);
    }

    @Test
    public void modifiedDateTest() {
        Card card = this.repository.findById(1L).orElseThrow();
        card.setTitle("Modified Title");

        this.repository.save(card);
    }
}
