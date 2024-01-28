package org.example.tonutoz.spring.webflux.webflux.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User,Long>, UserCostomRepository {

}
