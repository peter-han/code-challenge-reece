package com.phan.codechallenge.reece.controller.bean;

import lombok.Data;

@Data
public class ContactRequest {
    String userName;
    String bookName;
    String contactName;
    Integer phone;
}
