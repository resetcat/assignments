package org.countries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class CountryMainTest {
    List<Country> testCountryList = new ArrayList<>();

    @BeforeEach
    void createTestCountries(){
        for (int i = 1; i < 20; i++) {
            testCountryList.add(new Country(null,null,null,i,100-i));
        }
    }


    @Test
    void testGetCountriesSize10() {
        int result = CountryMain.getCountries(testCountryList, Comparator.comparing(Country::calcDensity)).size();
        Assertions.assertEquals(10,result);
    }

    @Test
    void testGetTenBiggestArea() {
         Country biggestResult = CountryMain.getTenBiggestArea(testCountryList).get(0);
         Country biggestActual = testCountryList.get(0);
        Assertions.assertEquals(biggestResult,biggestActual);
    }

    @Test
    void testGetTenMostPopulated() {
        int biggestResult = CountryMain.getTenMostPopulated(testCountryList).get(0).getPopulation();
        int biggestActual = 19;
        Assertions.assertEquals(biggestResult,biggestActual);
    }
}