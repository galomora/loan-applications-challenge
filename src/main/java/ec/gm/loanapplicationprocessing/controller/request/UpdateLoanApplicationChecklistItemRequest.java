package ec.gm.loanapplicationprocessing.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateLoanApplicationChecklistItemRequest {
    @NotNull
    @Min(1)
    private Long loanApplicationId;
    private Boolean isComplete;
    @NotNull
    private String task;
    @NotNull
    private String notes;
    @NotNull
    private String officer;

    public UpdateLoanApplicationChecklistItemRequest() {}

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }


    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }
}
