package com.example.bankserversystem.entity.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountHistory is a Querydsl query type for AccountHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountHistory extends EntityPathBase<AccountHistory> {

    private static final long serialVersionUID = -487530588L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountHistory accountHistory = new QAccountHistory("accountHistory");

    public final QAccount account;

    public final StringPath accountNumber = createString("accountNumber");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath historyMessage = createString("historyMessage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QAccountHistory(String variable) {
        this(AccountHistory.class, forVariable(variable), INITS);
    }

    public QAccountHistory(Path<? extends AccountHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccountHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccountHistory(PathMetadata metadata, PathInits inits) {
        this(AccountHistory.class, metadata, inits);
    }

    public QAccountHistory(Class<? extends AccountHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
    }

}

