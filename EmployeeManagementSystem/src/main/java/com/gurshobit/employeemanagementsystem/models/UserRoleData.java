package com.gurshobit.employeemanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRoleData {
    @NotNull
    @Min(value = 1)
    private int userId;
    @NotNull
    @Min(value = 1)
    private int roleId;

}
