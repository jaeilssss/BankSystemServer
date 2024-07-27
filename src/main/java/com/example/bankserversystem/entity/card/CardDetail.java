package com.example.bankserversystem.entity.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public abstract class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debit_card_id")
    private Long id;
    private String description;

    private String benefit;
}
