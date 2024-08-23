package com.dxbair.services.flightbooking.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Flight {

	@Id
	@GeneratedValue(generator = "flight-id-gen")
	@GenericGenerator(
			name = "flight-id-gen",
			parameters = @Parameter(name = "prefix", value = "FL"),
			strategy = "com.dxbair.services.flightbooking.domain.util.StringSequenceIdGenerator"
	)
	private String id;

	private String departure;
	private String arrival;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;

	@ManyToMany(mappedBy = "flights", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<FlightBooking> bookings = new HashSet<>();

	public Flight() {
		super();
	}

	public Flight(String id, String departure, String arrival, LocalDateTime departureDate, LocalDateTime arrivalDate) {
		super();
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getDeparture() {
		return departure;
	}

	public String getArrival() {
		return arrival;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public Set<FlightBooking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<FlightBooking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Flight{" +
				"id='" + id + '\'' +
				", departure='" + departure + '\'' +
				", arrival='" + arrival + '\'' +
				", departureDate=" + departureDate +
				", arrivalDate=" + arrivalDate +
				'}';
	}
}
