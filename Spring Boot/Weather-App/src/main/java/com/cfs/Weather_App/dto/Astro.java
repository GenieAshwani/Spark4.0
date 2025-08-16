package com.cfs.Weather_App.dto;

public class Astro{
    public String sunrise;
    public String sunset;
    public String moonrise;
    public String moonset;
    public String moon_phase;
    public int moon_illumination;
    public int is_moon_up;
    public int is_sun_up;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public String getMoon_phase() {
        return moon_phase;
    }

    public void setMoon_phase(String moon_phase) {
        this.moon_phase = moon_phase;
    }

    public int getMoon_illumination() {
        return moon_illumination;
    }

    public void setMoon_illumination(int moon_illumination) {
        this.moon_illumination = moon_illumination;
    }

    public int getIs_moon_up() {
        return is_moon_up;
    }

    public void setIs_moon_up(int is_moon_up) {
        this.is_moon_up = is_moon_up;
    }

    public int getIs_sun_up() {
        return is_sun_up;
    }

    public void setIs_sun_up(int is_sun_up) {
        this.is_sun_up = is_sun_up;
    }
}
