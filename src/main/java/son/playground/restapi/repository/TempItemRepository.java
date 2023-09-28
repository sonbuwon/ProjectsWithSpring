package son.playground.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.playground.restapi.domain.TempItem;

@Repository
public interface TempItemRepository extends JpaRepository<TempItem, Long> {

}
