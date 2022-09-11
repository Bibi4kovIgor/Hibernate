package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Greeting")
public class Greeter {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID identifier;

    @Column(name = "greet")
    private String greeting;

    @Column(name = "target")
    private String target;
}
