package nekrashevich.artgallery.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "paintings_on_boards")
@Getter
@Setter
public class PaintingOnBoard {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "painting_id", referencedColumnName = "id")
    private Painting painting;

}
