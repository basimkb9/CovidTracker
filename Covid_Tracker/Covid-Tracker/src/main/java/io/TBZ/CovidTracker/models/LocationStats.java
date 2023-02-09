package io.TBZ.CovidTracker.models;

public class LocationStats
{
    private String state;
    private String country;
    private int ConfirmedCases;
    private int ConfirmedDeaths;
    private int ConfirmedRecovered;

    public int getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(int diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
    }

    private int diffFromPrevDay;

    public String getState() {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public int getConfirmedCases()
    {
        return ConfirmedCases;
    }

    public void setConfirmedCases(int confirmedCases)
    {
        ConfirmedCases = confirmedCases;
    }

    public int getConfirmedDeaths()
    {
        return ConfirmedDeaths;
    }

    public void setConfirmedDeaths(int confirmedDeaths)
    {
        ConfirmedDeaths = confirmedDeaths;
    }

    public int getConfirmedRecovered()
    {
        return ConfirmedRecovered;
    }

    public void setConfirmedRecovered(int confirmedRecovered)
    {
        ConfirmedRecovered = confirmedRecovered;
    }

    @Override
    public String toString()
    {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", ConfirmedCases=" + ConfirmedCases +
                ", ConfirmedDeaths=" + ConfirmedDeaths +
                ", ConfirmedRecovered=" + ConfirmedRecovered +
                '}';
    }
}
