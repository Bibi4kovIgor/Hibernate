package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NamedQueries({
        @NamedQuery(
                name = "selectAllGreets",
                query = "select p from GreeterEntity p where name = :name"
        )
})

@Getter
@Setter
@Entity
@Table(name = "GREETER")
public class GreeterEntity implements Serializable, MyEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID identifier;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "greeter")
    private Set<GreetingEntity> greetings = new HashSet<>();

    public String toString() {
        return "GreeterEntity(identifier=" + this.getIdentifier() + ", name=" + this.getName() + ")";
    }
}
