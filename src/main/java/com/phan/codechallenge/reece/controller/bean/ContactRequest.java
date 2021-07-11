package com.phan.codechallenge.reece.controller.bean;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@Valid
public class ContactRequest {

    @NotEmpty
    @Size(max = 40)
    String userName;

    @NotEmpty
    @Size(max = 100)
    String bookName;

    @Size(max = 100)
    String contactName;

    @Max(10)
    @Min(10)
    Integer phone;
}
