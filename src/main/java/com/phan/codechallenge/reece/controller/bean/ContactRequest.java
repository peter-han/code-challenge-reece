package com.phan.codechallenge.reece.controller.bean;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class ContactRequest {

    @NotEmpty
    @Size(max = 40)
    String userName;

    @NotEmpty
    @Size(max = 100)
    String bookName;

    @Size(max = 100)
    String contactName;

    @Pattern(regexp = "^\\d{10}$")
    String phone;
}
