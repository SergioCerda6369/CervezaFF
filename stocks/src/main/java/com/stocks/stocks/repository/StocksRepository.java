package com.stocks.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.stocks.model.Stocks;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer>  {

}
