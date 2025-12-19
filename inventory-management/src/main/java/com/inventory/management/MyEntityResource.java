package com.inventory.management;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface MyEntityResource extends PanacheEntityResource<MyEntity, Long> {
}