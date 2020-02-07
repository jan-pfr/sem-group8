package com.napier.semgroup.reports;

public class Country {

    /*
     *Country's Code
     */
    public String code;

    /*
     *Country's Name
     */
    public String  name;

    /*
     *Country's Continent
     */
    public String  continent;

    /*
     *Country's Region
     */
    public String  region;

    /*
     *Country's Population
     */
    public Integer  population;

    /*
     *Country's Capital
     */
    public Integer  capital;

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                ", capital=" + capital +
                '}';
    }
}
