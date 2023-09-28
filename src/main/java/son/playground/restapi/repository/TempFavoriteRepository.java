package son.playground.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.playground.restapi.domain.TempFavorite;
import son.playground.restapi.domain.TempItem;

@Repository
public interface TempFavoriteRepository extends JpaRepository<TempFavorite, Long> {
    public void deleteByTempItem(TempItem tempItem);
}
