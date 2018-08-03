package com.cloud.stream.study.order.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/order-repository")
public interface OrderRepository extends MongoRepository<Order, String> {
}
