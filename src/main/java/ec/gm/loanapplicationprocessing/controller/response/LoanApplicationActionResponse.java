package ec.gm.loanapplicationprocessing.controller.response;

import ec.gm.loanapplicationprocessing.model.ActionName;
import ec.gm.loanapplicationprocessing.model.LoanApplicationAction;
import ec.gm.loanapplicationprocessing.model.LoanApplicationStatus;

import javax.validation.constraints.NotNull;

public class LoanApplicationActionResponse {

    private Long id;
    @NotNull
    private String officer;
    @NotNull
    private ActionName actionName;

    public LoanApplicationActionResponse () {

    }

    public LoanApplicationActionResponse(Long id, String officer, ActionName actionName) {
        this.id = id;
        this.officer = officer;
        this.actionName = actionName;
    }

    public LoanApplicationActionResponse(LoanApplicationAction action) {
        this (action.getId(), action.getOfficer(), action.getActionName());
    }

    public ActionName getActionName() {
        return actionName;
    }

    public void setActionName(ActionName actionName) {
        this.actionName = actionName;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
