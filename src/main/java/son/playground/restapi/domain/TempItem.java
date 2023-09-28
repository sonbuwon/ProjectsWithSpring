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
public class TempItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_item_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne(mappedBy = "tempItem", cascade = CascadeType.ALL)
    private TempFavorite tempFavorite;
}
