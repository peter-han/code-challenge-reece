package com.phan.codechallenge.reece.controller.bean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactResponse {
    private String name;
    private String phone;
    private String addressBook;
}
