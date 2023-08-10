package com.gonggubox.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    //대분류
    LIFE("생활"),
    FASHION("패션"),
    FOOD("식품"),


    //소분류

    //LIFE
    DETERGENT("세제"),
    SHAMPOO("샴푸");

    //......


    final String value;
}
