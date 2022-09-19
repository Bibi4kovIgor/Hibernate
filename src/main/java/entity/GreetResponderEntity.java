package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "GREET_RESPONDER")
public class GreetResponderEntity implements MyEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(name = "Responder_address",
                joinColumns = @JoinColumn(name = "responder_id"),
                inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<AddressEntity> addresses = new LinkedHashSet<>();


}
