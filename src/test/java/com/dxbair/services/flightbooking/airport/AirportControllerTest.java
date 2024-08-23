package com.dxbair.services.flightbooking.airport;

import com.dxbair.services.flightbooking.domain.entity.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    private List<Airport> airportList;

    @BeforeEach
    public void setUp() {
        Airport airport1 = new Airport("JFK", "John F. Kennedy International Airport", "US");
        Airport airport2 = new Airport("LHR", "Heathrow Airport", "GB");
        airportList = Arrays.asList(airport1, airport2);

        when(airportService.getAllAirports()).thenReturn(airportList);
        when(airportService.getAirportById("JFK")).thenReturn(airport1);
    }

    @Test
    public void testGetAllAirports() throws Exception {
        mockMvc.perform(get("/airports")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'iataCode':'JFK','name':'John F. Kennedy International Airport','countryIsoCode':'US'},{'iataCode':'LHR','name':'Heathrow Airport','countryIsoCode':'GB'}]"));
    }

    @Test
    public void testGetAirportById() throws Exception {
        mockMvc.perform(get("/airports/JFK")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'iataCode':'JFK','name':'John F. Kennedy International Airport','countryIsoCode':'US'}"));
    }
}