package com.example.bankserversystem.dto.card;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBeforeCreditCharge is a Querydsl query type for BeforeCreditCharge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBeforeCreditCharge extends EntityPathBase<BeforeCreditCharge> {

    private static final long serialVersionUID = -1387057492L;

    public static final QBeforeCreditCharge beforeCreditCharge = new QBeforeCreditCharge("beforeCreditCharge");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBeforeCreditCharge(String variable) {
        super(BeforeCreditCharge.class, forVariable(variable));
    }

    public QBeforeCreditCharge(Path<? extends BeforeCreditCharge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBeforeCreditCharge(PathMetadata metadata) {
        super(BeforeCreditCharge.class, metadata);
    }

}

