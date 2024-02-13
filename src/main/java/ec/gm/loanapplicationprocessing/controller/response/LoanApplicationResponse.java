package ec.gm.loanapplicationprocessing.controller.response;

import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.model.LoanApplicationStatus;

public class LoanApplicationResponse {
    private Long id;
    private String applicantName;
    private LoanApplicationStatus status;
    private String assignedOfficer;

    public LoanApplicationResponse () {
        super();
    }
    public LoanApplicationResponse(Long id, String applicantName, LoanApplicationStatus status, String assignedOfficer) {
        this.id = id;
        this.applicantName = applicantName;
        this.status = status;
        this.assignedOfficer = assignedOfficer;
    }

    public LoanApplicationResponse (LoanApplication loanApplication) {
        this (
            loanApplication.getLoanApplicationId(),
                loanApplication.getApplicantName(),
                loanApplication.getStatus(),
                loanApplication.getAssignedOfficer()
        );
    }

    public Long getId() {
        return id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public String getAssignedOfficer() {
        return assignedOfficer;
    }
}
