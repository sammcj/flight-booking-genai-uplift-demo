package com.dxbair.services.flightbooking.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "airports")
public class Airport {
	@Id
	private String iataCode;
	private String name;
	private String countryIsoCode;

	public Airport() {
		super();
	}

	public Airport(String iataCode, String name, String countryIsoCode) {
		this.iataCode = iataCode;
		this.name = name;
		this.countryIsoCode = countryIsoCode;
	}

	public String getIataCode() {
		return iataCode;
	}

	public String getName() {
		return name;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}
}
