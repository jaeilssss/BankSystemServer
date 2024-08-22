package com.example.bankserversystem.entity.card;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCardDetail is a Querydsl query type for CardDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardDetail extends EntityPathBase<CardDetail> {

    private static final long serialVersionUID = 631012547L;

    public static final QCardDetail cardDetail = new QCardDetail("cardDetail");

    public final StringPath benefit = createString("benefit");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCardDetail(String variable) {
        super(CardDetail.class, forVariable(variable));
    }

    public QCardDetail(Path<? extends CardDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCardDetail(PathMetadata metadata) {
        super(CardDetail.class, metadata);
    }

}

