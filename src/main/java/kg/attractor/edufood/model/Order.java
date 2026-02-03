package kg.attractor.edufood.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalSum;
    private LocalDate createdDate;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER")
    private User customer;
}
