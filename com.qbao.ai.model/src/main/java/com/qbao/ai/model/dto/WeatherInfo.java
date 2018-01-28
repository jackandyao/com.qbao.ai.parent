
package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class WeatherInfo {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("airQuality")
    @Expose
    private String airQuality;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public WeatherInfo withDate(String date) {
        this.date = date;
        return this;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public WeatherInfo withWeather(String weather) {
        this.weather = weather;
        return this;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public WeatherInfo withTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public WeatherInfo withAirQuality(String airQuality) {
        this.airQuality = airQuality;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(date).append(weather).append(temperature).append(airQuality).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WeatherInfo) == false) {
            return false;
        }
        WeatherInfo rhs = ((WeatherInfo) other);
        return new EqualsBuilder().append(date, rhs.date).append(weather, rhs.weather).append(temperature, rhs.temperature).append(airQuality, rhs.airQuality).isEquals();
    }

}
