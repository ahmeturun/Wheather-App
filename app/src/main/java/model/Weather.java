package model;

/**
 * Created by ahmet on 8/17/2016.
 */
public class Weather {

    public Place place;
    public String iconData;
    public double[] weeklyTemps;
    public String[] weeklyIcons;
    public String[] weeklyDates;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

    public double[] getWeeklyTemps() {
        return weeklyTemps;
    }

    public void setWeeklyTemps(double[] weeklyTemps) {
        this.weeklyTemps = weeklyTemps;
    }

    public String[] getWeeklyIcons() {
        return weeklyIcons;
    }

    public void setWeeklyIcons(String[] weeklyIcons) {
        this.weeklyIcons = weeklyIcons;
    }

    public String[] getWeeklyDates() {
        return weeklyDates;
    }

    public void setWeeklyDates(String[] weeklyDates) {
        this.weeklyDates = weeklyDates;
    }
}
