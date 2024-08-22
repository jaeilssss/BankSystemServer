package com.example.bankserversystem.entity.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 1189860048L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath accountPassword = createString("accountPassword");

    public final StringPath accountType = createString("accountType");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final com.example.bankserversystem.entity.deposit.QDeposit deposit;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> totalDeposit = createNumber("totalDeposit", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.example.bankserversystem.entity.user.QUserInfo userInfo;

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.deposit = inits.isInitialized("deposit") ? new com.example.bankserversystem.entity.deposit.QDeposit(forProperty("deposit")) : null;
        this.userInfo = inits.isInitialized("userInfo") ? new com.example.bankserversystem.entity.user.QUserInfo(forProperty("userInfo")) : null;
    }

}

