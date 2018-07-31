package com.cloud.stream.study.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommerceItem {

    @NotEmpty
    private String name;
    @NotEmpty
    private String code;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer quantity;

    public BigDecimal getSubTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
