package softuni.exam.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import softuni.exam.models.entity.enums.DayOfWeek;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportDTO {

    //   //<forecasts>
    //    //    <forecast>
    //    //        <day_of_week>NULL</day_of_week>
    //    //        <max_temperature>25</max_temperature>
    //    //        <min_temperature>-5</min_temperature>
    //    //        <sunrise>06:12:09</sunrise>
    //    //        <sunset>21:19:52</sunset>
    //    //        <city>1</city>
    //    //    </forecast>
    //    //    <forecast>
    //    //        <day_of_week>FRIDAY</day_of_week>
    //    //        <max_temperature>25</max_temperature>
    //    //        <min_temperature>-5</min_temperature>
    //    //        <sunrise>06:12:09</sunrise>
    //    //        <sunset>21:19:52</sunset>
    //    //        <city>1</city>
    //    //    </forecast>

    @NotNull
    @XmlElement(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Min(value = -20)
    @Max(value = 60)
    @NotNull
    @XmlElement(name = "max_temperature")
    private Double maxTemperature;

    @Min(value = -50)
    @Max(value = 40)
    @NotNull
    @XmlElement(name = "min_temperature")
    private Double minTemperature;

    @NotNull
    private String sunrise;

    @NotNull
    private String sunset;

    private Long city;

    public ForecastImportDTO() {}

    public ForecastImportDTO(DayOfWeek dayOfWeek, Double maxTemperature, Double minTemperature, String sunrise, String sunset, Long city) {
        this.dayOfWeek = dayOfWeek;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.city = city;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

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

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }
}
