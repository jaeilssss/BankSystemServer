package com.example.bankserversystem.entity.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeleteAccount is a Querydsl query type for DeleteAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeleteAccount extends EntityPathBase<DeleteAccount> {

    private static final long serialVersionUID = 2012805221L;

    public static final QDeleteAccount deleteAccount = new QDeleteAccount("deleteAccount");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final StringPath accountNumber = createString("accountNumber");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDeleteAccount(String variable) {
        super(DeleteAccount.class, forVariable(variable));
    }

    public QDeleteAccount(Path<? extends DeleteAccount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeleteAccount(PathMetadata metadata) {
        super(DeleteAccount.class, metadata);
    }

}

