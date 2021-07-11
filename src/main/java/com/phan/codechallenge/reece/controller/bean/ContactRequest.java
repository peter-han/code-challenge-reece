package com.phan.codechallenge.reece.controller.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactRequest {
    String userName;
    String bookName;
    String contactName;
    Integer phone;
}
