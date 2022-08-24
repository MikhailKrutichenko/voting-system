package com.graduation.votingSystem;

import com.graduation.votingSystem.model.Role;
import com.graduation.votingSystem.model.User;

public class UserTestData {

    public static final int NOT_EXIST_ID = 4;

    public static final int USER_1_ID = 10000;
    public static final int USER_2_ID = 10001;
    public static final int USER_3_ID = 10002;
    public static final int USER_4_ID = 10003;
    public static final int USER_5_ID = 10004;
    public static final int USER_6_ID = 10005;
    public static final int ADMIN_ID = 10006;

    public static final User USER_1 = new User(10000, Role.USER, "user1", "user1@mail.ru", "123");
    public static final User USER_2 = new User(10001, Role.USER, "user2", "user2@mail.ru", "456");
    public static final User USER_3 = new User(10002, Role.USER, "user3", "user3@mail.ru", "789");
    public static final User USER_4 = new User(10003, Role.USER, "user4", "user4@mail.ru", "124");
    public static final User USER_5 = new User(10004, Role.USER, "user5", "user5@mail.ru", "125");
    public static final User USER_6 = new User(10005, Role.USER, "user6", "user6@mail.ru", "126");
    public static final User ADMIN = new User(10006, Role.ADMIN, "admin", "admin@mail.ru", "111");

    public static User getNew() {
        return new User(null, Role.USER, "New", "new@mail.ru", "111");
    }

    public static User getUpdated() {
        return new User(ADMIN_ID, Role.ADMIN, "Updated", "updated@Mail.ru", "232");
    }

    public static User getWithNotExistId() {
        return new User(NOT_EXIST_ID, Role.USER, "Updated", "updated@mail.ru", "12312");
    }
}
