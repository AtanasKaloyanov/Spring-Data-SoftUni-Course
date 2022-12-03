package softuni.exam.models.dto.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportWrapperDTO {

    @XmlElement(name = "task")
    private List<TaskImportDTO> taskImportDTOList;

    public TaskImportWrapperDTO() {}

    public TaskImportWrapperDTO(List<TaskImportDTO> taskImportDTOList) {
        this.taskImportDTOList = taskImportDTOList;
    }

    public List<TaskImportDTO> getTaskImportDTOList() {
        return taskImportDTOList;
    }

    public void setTaskImportDTOList(List<TaskImportDTO> taskImportDTOList) {
        this.taskImportDTOList = taskImportDTOList;
    }
}
