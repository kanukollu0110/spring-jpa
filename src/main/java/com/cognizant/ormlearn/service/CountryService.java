package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.CountryRepository.CountryRepository;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Transactional
	public List<Country> getAllCountries() {
		return countryRepository.findAll();

	}

	@Transactional
	public List<Country> findCountry(String text) {
		//day 2 handson 1
		return countryRepository.findByCountryWith(text);

	}

	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
		Optional<Country> result = countryRepository.findById(countryCode);
		if (!result.isPresent()) {
			throw new CountryNotFoundException("Country not found");
		}
		Country country = result.get();

		return country;
	}

	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}

	@Transactional
	public void updateCountry(String code, String name) throws CountryNotFoundException {
		Country country = (Country) countryRepository.findById(code).get();
		country.setName(name);
		countryRepository.save(country);

	}

	@Transactional
	public void deleteCountry(String code) {
		Country country = (Country) countryRepository.findById(code).get();
		countryRepository.delete(country);
	}

}
