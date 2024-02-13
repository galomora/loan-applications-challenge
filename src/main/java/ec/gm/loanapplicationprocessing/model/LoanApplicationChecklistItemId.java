package ec.gm.loanapplicationprocessing.model;

import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LoanApplicationChecklistItemId implements Serializable {
    @Column(nullable = false, name = "LOAN_APPLICATION_ID")
    private Long loanApplicationId;
    @Column(nullable = false, length = 50)
    private String checklistTask;

    public LoanApplicationChecklistItemId() {

    }
    public LoanApplicationChecklistItemId(Long loanApplicationId, String checklistTask) {
        this.loanApplicationId = loanApplicationId;
        this.checklistTask = checklistTask;
    }

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public String getChecklistTask() {
        return checklistTask;
    }

    public void update (String task) throws LoanApplicationException {
        if (task == null || task.isEmpty()) {
            throw new LoanApplicationException("Checklist task is required");
        }
        this.checklistTask = task;
    }
}
