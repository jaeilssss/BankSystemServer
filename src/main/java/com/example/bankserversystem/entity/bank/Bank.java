package com.example.bankserversystem.entity.bank;

import com.example.bankserversystem.domain.logic.GeometryPoint;
import com.example.bankserversystem.dto.bank.CreateBankRequest;
import com.example.bankserversystem.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bank extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;

    private String bankName;

    private String openTime;
    private String closeTime;

    private Point location;

    public void setBank(CreateBankRequest createBankRequest) {
        this.bankName = createBankRequest.getBankName();
        this.openTime = createBankRequest.getOpenTime();
        this.closeTime = createBankRequest.getCloseTime();
        this.location = GeometryPoint.createPoint(
                createBankRequest.getLatitude(), createBankRequest.getLongitude());
    }
}
