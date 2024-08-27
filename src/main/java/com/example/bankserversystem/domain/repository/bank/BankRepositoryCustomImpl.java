package com.example.bankserversystem.domain.repository.bank;

import com.example.bankserversystem.domain.logic.GeometryPoint;
import com.example.bankserversystem.dto.bank.CreateBankRequest;
import com.example.bankserversystem.entity.bank.Bank;
import com.example.bankserversystem.entity.bank.QBank;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankRepositoryCustomImpl implements BankRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    QBank qBank = QBank.bank;
    private final double RADIUS_IN_METERS = 1000.0;
    public BankRepositoryCustomImpl(EntityManager entityManager) {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    @Override
    public List<Bank> getNearBank(Double latitude, Double longitude) {
        // 위도, 경도 값 검증
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid latitude or longitude");
        }

        Point point = GeometryPoint.createPoint(latitude, longitude);

        BooleanExpression distanceCondition = Expressions.booleanTemplate(
                "function('ST_DWithin', ST_SetSRID({0}, 4326), ST_SetSRID({1}, 4326), {2}) = true",
                qBank.location,
                point,
                RADIUS_IN_METERS
        );
        return jpaQueryFactory.selectFrom(qBank)
                .where(distanceCondition)
                .fetch();
    }
}
