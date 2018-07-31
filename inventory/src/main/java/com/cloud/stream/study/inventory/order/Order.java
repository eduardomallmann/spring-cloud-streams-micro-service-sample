package com.cloud.stream.study.inventory.order;

import com.cloud.stream.study.inventory.product.ProductSold;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private List<ProductSold> items;

}

