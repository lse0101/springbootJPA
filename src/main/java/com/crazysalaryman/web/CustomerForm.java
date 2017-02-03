package com.crazysalaryman.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by lse0101 on 2017-02-03.
 */
@Data
public class CustomerForm {

    @NotNull
    @Size(min = 1, max = 127)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 127)
    private String lastName;
}
