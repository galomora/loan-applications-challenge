package ec.gm.loanapplicationprocessing.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateLoanApplicationChecklistItemRequest {
    @NotNull
    @Min(1)
    private Long loanApplicationId;
    @NotNull
    private String task;
    @NotNull
    private String notes;

    public CreateLoanApplicationChecklistItemRequest() {}

    public CreateLoanApplicationChecklistItemRequest (Long loanApplicationId, String task, String notes) {
        this.loanApplicationId = loanApplicationId;
        this.task = task;

        this.notes = notes;

    }

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
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

}
