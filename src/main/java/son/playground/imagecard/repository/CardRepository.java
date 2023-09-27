package son.playground.imagecard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.playground.imagecard.domain.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
