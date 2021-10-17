package com.codeXie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseItem implements Serializable {
    private Integer itemid;
    private Integer expid;
    private String type;
    private Double amount;
    private String itemdesc;
}
