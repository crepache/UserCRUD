package com.cliente.repository;

import com.cliente.model.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, String> {

}
