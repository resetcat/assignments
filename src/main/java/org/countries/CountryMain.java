package org.countries;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CountryMain {

    public static void main(String[] args) throws IOException {
        // add list from local txt
        String stringList = createFromTxtFile();
        // add directly from API
        String apiList = callApi("https://restcountries.com/v2/regionalbloc/eu");


        JSONArray list = new JSONArray(stringList);// apilist if from API
        List<Country> countryList = createCountryList(list);
        List<Country> tenMostPopulated = getTenMostPopulated(countryList);
        System.out.println("Ten EU most populated countries are "+ tenMostPopulated.stream().map(Country::getName).toList());
        List<Country> tenBiggestArea = getTenBiggestArea(countryList);
        System.out.println("Ten EU countries with largest area are " + tenBiggestArea.stream().map(Country::getName).toList());
        List<Country> tenBiggestDensity = getTenBiggestDensity(countryList);
        System.out.println("Ten EU countries with biggest population density are "+ tenBiggestDensity.stream().map(Country::getName).toList());




    }

    public static String createFromTxtFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/java/org/countries/countryJSON.txt"));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            text.append(line);
        }
        in.close();
        return text.toString();
    }

    public static List<Country> getTenBiggestDensity(List<Country> countryList) {
        return getCountries(countryList, Comparator.comparing(Country::calcDensity));
    }


    public static List<Country> getTenBiggestArea(List<Country> countryList) {
        return getCountries(countryList, Comparator.comparing(Country::getArea));
    }

    public static List<Country> getTenMostPopulated(List<Country> countryList) {
        return getCountries(countryList, Comparator.comparing(Country::getPopulation));
    }

    public static List<Country> getCountries(List<Country> countryList, Comparator<Country> comparing) {
        countryList.sort(comparing.reversed());
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            countries.add(countryList.get(i));
        }
        return countries;
    }

    public static String callApi(String theURL) {
        try {
            URL url = new URL(theURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder inputData = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                inputData.append(inputLine);
            }
            in.close();
            return inputData.toString();
        } catch (IOException e) {
            System.out.println("Error!");
            return "";
        }
    }

    public static List<Country> createCountryList(JSONArray json) {
        List<Country> list = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            if (json.getJSONObject(i).getBoolean("independent")) {
                list.add(createCountry(json.getJSONObject(i)));
            }
        }
        return list;
    }

    public static Country createCountry(JSONObject obj) {
        String name = obj.getString("name");
        String capital = obj.getString("capital");
        String currencies = obj.getString("name");
        int population = obj.getInt("population");
        int area = obj.getInt("area");
        return new Country(name, capital, currencies, population, area);
    }

}


