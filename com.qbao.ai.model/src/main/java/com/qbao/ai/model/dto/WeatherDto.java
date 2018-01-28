
package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class WeatherDto {

    @SerializedName("weatherInfo")
    @Expose
    private List<WeatherInfo> weatherInfo = null;

    @SerializedName("city")
    @Expose
    private String city;

    public List<WeatherInfo> getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(List<WeatherInfo> weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public WeatherDto withWeatherInfo(List<WeatherInfo> weatherInfo) {
        this.weatherInfo = weatherInfo;
        return this;
    }

    public WeatherDto withCity(String city) {
        this.city = city;
        return this;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(weatherInfo).append(city).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WeatherDto) == false) {
            return false;
        }
        WeatherDto rhs = ((WeatherDto) other);
        return new EqualsBuilder().append(weatherInfo, rhs.weatherInfo).append(city,rhs.city).isEquals();
    }

}
