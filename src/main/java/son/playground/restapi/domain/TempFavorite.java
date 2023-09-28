package son.playground.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TempFavorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_favorite_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "temp_item_id")
    private TempItem tempItem;
}
