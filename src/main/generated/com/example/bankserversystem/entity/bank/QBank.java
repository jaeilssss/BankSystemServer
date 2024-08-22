package com.example.bankserversystem.entity.bank;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBank is a Querydsl query type for Bank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBank extends EntityPathBase<Bank> {

    private static final long serialVersionUID = -1272006318L;

    public static final QBank bank = new QBank("bank");

    public final com.example.bankserversystem.entity.QAbstractEntity _super = new com.example.bankserversystem.entity.QAbstractEntity(this);

    public final StringPath bankName = createString("bankName");

    public final StringPath closeTime = createString("closeTime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final StringPath openTime = createString("openTime");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBank(String variable) {
        super(Bank.class, forVariable(variable));
    }

    public QBank(Path<? extends Bank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBank(PathMetadata metadata) {
        super(Bank.class, metadata);
    }

}

