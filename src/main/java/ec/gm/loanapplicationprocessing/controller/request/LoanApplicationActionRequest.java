package ec.gm.loanapplicationprocessing.controller.request;

import ec.gm.loanapplicationprocessing.model.ActionName;
import ec.gm.loanapplicationprocessing.model.LoanApplicationStatus;

import javax.validation.constraints.NotNull;

public class LoanApplicationActionRequest {
    @NotNull
    private String officer;
    @NotNull
    private ActionName actionName;

    private LoanApplicationStatus loanApplicationStatus;

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public LoanApplicationStatus getLoanApplicationStatus() {
        return loanApplicationStatus;
    }

    public ActionName getActionName() {
        return actionName;
    }

    public void setActionName(ActionName actionName) {
        this.actionName = actionName;
    }

    public void setLoanApplicationStatus(LoanApplicationStatus loanApplicationStatus) {
        this.loanApplicationStatus = loanApplicationStatus;
    }
}
