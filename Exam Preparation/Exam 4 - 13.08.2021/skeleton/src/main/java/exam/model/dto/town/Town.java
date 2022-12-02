package exam.model.dto.town;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
public class Town {

    private String name;

    public Town() {}

    public Town(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
