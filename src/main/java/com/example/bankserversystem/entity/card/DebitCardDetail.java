package com.example.bankserversystem.entity.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("DebitCardDetail")
public class DebitCardDetail extends CardDetail {
}
