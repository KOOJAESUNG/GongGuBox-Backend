package com.gonggubox.constant;

import jakarta.persistence.*;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;


    //값타입 클래스는 기본 생성자가 꼭 있어야함
    protected Address() {} //무분별하게 생성되는 것을 막기 위해 protected로 선언

}
