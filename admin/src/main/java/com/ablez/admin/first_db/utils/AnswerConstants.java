package com.ablez.admin.first_db.utils;

import java.util.HashMap;
import java.util.Map;

public class AnswerConstants {
    public static final Map<Integer, String> EP01_SUSPECT = new HashMap<>() {{
        put(1, "김도진");
        put(2, "도유미");
        put(3, "박대기");
    }};

    public static final Map<Integer, String> EP02_SUSPECT1 = new HashMap<>() {{
        put(1, "곽계자");
        put(2, "홍물주");
        put(3, "가장임");
    }};

    public static final Map<Integer, String> EP02_SUSPECT2 = new HashMap<>() {{
        put(1, "자살");
        put(2, "사고사");
    }};

    public static final Map<Integer, String> EP03_SUSPECT = new HashMap<>() {{
        put(1, "애경인");
        put(2, "이순휘");
        put(3, "우환해");
        put(4, "우승해");
    }};
}
