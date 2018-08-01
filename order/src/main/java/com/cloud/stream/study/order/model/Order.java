package com.cloud.stream.study.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Order {

    @Id
    private String id;
    @NotNull
    private List<CommerceItem> items;

    public BigDecimal getTotalAmount() {
        return this.items.stream().filter(Objects::nonNull).map(CommerceItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getTotalQuantity() {
        return this.items.stream().filter(Objects::nonNull).mapToInt(CommerceItem::getQuantity).sum();
    }

}
