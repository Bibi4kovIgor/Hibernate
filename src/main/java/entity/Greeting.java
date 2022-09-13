package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "GREETING")
public class Greeting {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID identifier;

    @Column(name = "greet")
    private String greeting;

    @Column(name = "target")
    private String target;

}
