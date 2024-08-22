package com.example.bankserversystem.entity.card;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDebitCard is a Querydsl query type for DebitCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDebitCard extends EntityPathBase<DebitCard> {

    private static final long serialVersionUID = 868840602L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDebitCard debitCard = new QDebitCard("debitCard");

    public final QCard _super;

    // inherited
    public final com.example.bankserversystem.entity.account.QAccount account;

    //inherited
    public final StringPath cardCompany;

    public final QDebitCardDetail cardDetail;

    //inherited
    public final StringPath cardNumber;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final DatePath<java.time.LocalDate> expirationDate;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final StringPath password;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt;

    // inherited
    public final com.example.bankserversystem.entity.user.QUserInfo userInfo;

    public QDebitCard(String variable) {
        this(DebitCard.class, forVariable(variable), INITS);
    }

    public QDebitCard(Path<? extends DebitCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDebitCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDebitCard(PathMetadata metadata, PathInits inits) {
        this(DebitCard.class, metadata, inits);
    }

    public QDebitCard(Class<? extends DebitCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCard(type, metadata, inits);
        this.account = _super.account;
        this.cardCompany = _super.cardCompany;
        this.cardDetail = inits.isInitialized("cardDetail") ? new QDebitCardDetail(forProperty("cardDetail")) : null;
        this.cardNumber = _super.cardNumber;
        this.createdAt = _super.createdAt;
        this.expirationDate = _super.expirationDate;
        this.id = _super.id;
        this.password = _super.password;
        this.updatedAt = _super.updatedAt;
        this.userInfo = _super.userInfo;
    }

}

