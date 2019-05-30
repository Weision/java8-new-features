package com.wxx.lambda.enums;

import com.wxx.domain.*;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum RepresentativeEnum {
    CHINESEREPRESENTATIVES("语文", (schoolClass, representative) -> {
        ChineseRepresentative chineseRepresentative = (ChineseRepresentative) representative;
        schoolClass.setChineseRepresentative(chineseRepresentative);
        return schoolClass;
    }),
    ENGLISHREPRESENTATIVES("英语", (schoolClass, representative) -> {
        EnglishRepresentative englishRepresentative = (EnglishRepresentative) representative;
        schoolClass.setEnglishRepresentative(englishRepresentative);
        return schoolClass;
    }),
    MATHEMATICSREPRESENTATIVES("数学", (schoolClass, representative) -> {
        MathematicsRepresentative mathematicsRepresentative = (MathematicsRepresentative) representative;
        schoolClass.setMathematicsRepresentative(mathematicsRepresentative);
        return schoolClass;
    });

    private String representativeVal;
    private BiFunction<SchoolClass, Representative, SchoolClass> schoolClassBiFunction;

    RepresentativeEnum(String representativeVal, BiFunction<SchoolClass,
            Representative, SchoolClass> schoolClassBiFunction) {
        this.representativeVal = representativeVal;
        this.schoolClassBiFunction = schoolClassBiFunction;
    }

    public static BiFunction<SchoolClass, Representative, SchoolClass> getSchoolClass(
            Representative representative) {

        return Arrays.stream(RepresentativeEnum.values())
                .filter(representativeEnum -> representativeEnum.representativeVal
                        .equals(representative.getSubject()))
                .map(representativeEnum -> representativeEnum.schoolClassBiFunction)
                .findFirst()
                .orElse(null);
    }
}
