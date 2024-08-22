package com.example.bankserversystem.entity.card;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDebitCardDetail is a Querydsl query type for DebitCardDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDebitCardDetail extends EntityPathBase<DebitCardDetail> {

    private static final long serialVersionUID = 405872011L;

    public static final QDebitCardDetail debitCardDetail = new QDebitCardDetail("debitCardDetail");

    public final QCardDetail _super = new QCardDetail(this);

    //inherited
    public final StringPath benefit = _super.benefit;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QDebitCardDetail(String variable) {
        super(DebitCardDetail.class, forVariable(variable));
    }

    public QDebitCardDetail(Path<? extends DebitCardDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDebitCardDetail(PathMetadata metadata) {
        super(DebitCardDetail.class, metadata);
    }

}

