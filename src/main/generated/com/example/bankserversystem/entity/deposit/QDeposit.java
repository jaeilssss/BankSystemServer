package com.example.bankserversystem.entity.deposit;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeposit is a Querydsl query type for Deposit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeposit extends EntityPathBase<Deposit> {

    private static final long serialVersionUID = -394532174L;

    public static final QDeposit deposit = new QDeposit("deposit");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath depositCreditCondition = createString("depositCreditCondition");

    public final NumberPath<Long> depositId = createNumber("depositId", Long.class);

    public final StringPath depositName = createString("depositName");

    public final NumberPath<Double> interestRate = createNumber("interestRate", Double.class);

    public final StringPath tag = createString("tag");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QDeposit(String variable) {
        super(Deposit.class, forVariable(variable));
    }

    public QDeposit(Path<? extends Deposit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeposit(PathMetadata metadata) {
        super(Deposit.class, metadata);
    }

}

