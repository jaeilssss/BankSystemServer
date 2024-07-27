package com.example.bankserversystem.dto.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class BeforeCreditCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="before_credit_charge")
    private Long id;


}
