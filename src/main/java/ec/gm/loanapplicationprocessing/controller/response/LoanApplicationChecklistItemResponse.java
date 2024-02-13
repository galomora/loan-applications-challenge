package ec.gm.loanapplicationprocessing.controller.response;

import ec.gm.loanapplicationprocessing.model.LoanApplicationChecklistItem;

public class LoanApplicationChecklistItemResponse {
    private Long id;
    private String task;
    private Boolean isComplete;
    private String notes;
    public LoanApplicationChecklistItemResponse () {}
    public LoanApplicationChecklistItemResponse(Long id, String task, Boolean isComplete, String notes) {
        this.id = id;
        this.task = task;
        this.isComplete = isComplete;
        this.notes = notes;
    }
    public LoanApplicationChecklistItemResponse(LoanApplicationChecklistItem item) {
        this.id = item.getId();
        this.task = item.getChecklistTask();
        this.isComplete = item.getComplete();
        this.notes = item.getNotes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
