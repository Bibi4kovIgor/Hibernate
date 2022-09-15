package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "GREETING")
public class GreetingEntity implements Serializable, MyEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID identifier;

    @Column(name = "greet")
    private String greeting;

    @Column(name = "target")
    private String target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "greeter_id")
    private GreeterEntity greeter;

}
