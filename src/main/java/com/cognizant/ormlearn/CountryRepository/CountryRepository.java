package com.cognizant.ormlearn.CountryRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
	
	//day 2 handson 1 here table name is entity name and column names are variables name in country class
	@Query("select c from country c where c.name like %?1% order by name")
	public List<Country> findByCountryWith(String text);

}