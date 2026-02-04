package kg.attractor.edufood.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "ROLE_NAME", nullable = false)
    private  Role role;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
