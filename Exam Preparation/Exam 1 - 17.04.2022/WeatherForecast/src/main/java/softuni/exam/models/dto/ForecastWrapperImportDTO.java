package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastWrapperImportDTO {

    @XmlElement(name = "forecast")
    private List<ForecastImportDTO> forecasts;

    ForecastWrapperImportDTO() {}

    public ForecastWrapperImportDTO(List<ForecastImportDTO> forecasts) {
        this.forecasts = forecasts;
    }

    public List<ForecastImportDTO> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastImportDTO> forecasts) {
        this.forecasts = forecasts;
    }
}
