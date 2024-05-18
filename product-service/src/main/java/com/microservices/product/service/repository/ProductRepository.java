package com.microservices.product.service.repository;

import com.microservices.product.service.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    Added here to only understand hibernate N+1 problem and the way to resolve it.

    @Query("SELECT p FROM Product p JOIN FETCH p.qualities")
    List<Product> findAllWithQualities();

    @EntityGraph(attributePaths = "qualities")
    List<Product> hibernate_n_plus_one_issue_solved_using_entity_graph_findAllWithQualities();
    
     */
}
