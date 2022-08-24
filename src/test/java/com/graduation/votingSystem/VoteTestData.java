package com.graduation.votingSystem;

import com.graduation.votingSystem.model.Vote;

import java.time.LocalDate;
import java.time.Month;

import static com.graduation.votingSystem.RestaurantTestData.MM_ID;
import static com.graduation.votingSystem.UserTestData.ADMIN_ID;
import static com.graduation.votingSystem.UserTestData.USER_1_ID;

public class VoteTestData {

    public static final int VOTE_1_ID = 10016;
    public static final int VOTE_2_ID = 10017;
    public static final int VOTE_3_ID = 10018;
    public static final int VOTE_4_ID = 10019;
    public static final int VOTE_5_ID = 10020;
    public static final int VOTE_6_ID = 10021;

    public static final int VOTE_NEW_ID = 10022;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, USER_1_ID, RestaurantTestData.IBURGER_ID,
            LocalDate.of(2022, Month.AUGUST, 17));
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, UserTestData.USER_2_ID, RestaurantTestData.IBURGER_ID,
            LocalDate.of(2022, Month.AUGUST, 17));
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, UserTestData.USER_3_ID, RestaurantTestData.MM_ID,
            LocalDate.of(2022, Month.AUGUST, 17));
    public static final Vote VOTE_4 = new Vote(VOTE_4_ID, UserTestData.USER_4_ID, RestaurantTestData.MM_ID,
            LocalDate.of(2022, Month.AUGUST, 17));
    public static final Vote VOTE_5 = new Vote(VOTE_5_ID, UserTestData.USER_5_ID, RestaurantTestData.MM_ID,
            LocalDate.of(2022, Month.AUGUST, 17));
    public static final Vote VOTE_6 = new Vote(VOTE_6_ID, UserTestData.USER_6_ID, RestaurantTestData.MM_ID,
            LocalDate.of(2022, Month.AUGUST, 17));

    public static Vote getNew() {
        return new Vote(VOTE_NEW_ID, ADMIN_ID, MM_ID, LocalDate.of(2022, Month.AUGUST, 17));
    }

    public static Vote getChange() {
        return new Vote(VOTE_1_ID, USER_1_ID, RestaurantTestData.MM_ID, LocalDate.of(2022, Month.AUGUST, 17));
    }
}
