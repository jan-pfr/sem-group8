package com.napier.semgroup.reports;


public class LanguageReport {

    /*
     * The Name of the language
     */
    public String name;

    /*
     *The Percentage of the language spoken in the country.
     */
    public double percentage;
    public String toString() {
        return String.format("%-52s %-26s",
                name, percentage);
    }
}
