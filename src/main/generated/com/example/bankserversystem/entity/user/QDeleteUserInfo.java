package com.example.bankserversystem.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeleteUserInfo is a Querydsl query type for DeleteUserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeleteUserInfo extends EntityPathBase<DeleteUserInfo> {

    private static final long serialVersionUID = 1229631499L;

    public static final QDeleteUserInfo deleteUserInfo = new QDeleteUserInfo("deleteUserInfo");

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final StringPath creditRating = createString("creditRating");

    public final DateTimePath<java.time.LocalDateTime> deleteDate = createDateTime("deleteDate", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDeleteUserInfo(String variable) {
        super(DeleteUserInfo.class, forVariable(variable));
    }

    public QDeleteUserInfo(Path<? extends DeleteUserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeleteUserInfo(PathMetadata metadata) {
        super(DeleteUserInfo.class, metadata);
    }

}

