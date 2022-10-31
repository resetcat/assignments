package org.countries;

public class Country {
    private final String name;
    private final String capital;
    private final String currencies;
    private final int population;
    private final int area;

    public Country(String name, String capital, String currencies, int population, int area) {
        this.name = name;
        this.capital = capital;
        this.currencies = currencies;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

    public double calcDensity() {
        return (double) population / area;
    }

    @Override
    public String toString() {
        return "{Country name = " + name + ", capital = " + capital + ", currency = " + currencies + ", population =" + " " + " " + population + ", area = " + area + "}";
    }
}
