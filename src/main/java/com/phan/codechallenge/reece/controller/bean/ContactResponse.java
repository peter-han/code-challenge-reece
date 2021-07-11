package com.phan.codechallenge.reece.controller.bean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactResponse {
    private String name;
    private Integer phone;
    private String addressBook;
}
