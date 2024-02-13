package ec.gm.loanapplicationprocessing.controller.request;

import javax.validation.constraints.NotNull;

public class LoanApplicationRequest {
    @NotNull
    private String applicantName;
    @NotNull
    private String officer;

    public LoanApplicationRequest () {

    }

    public LoanApplicationRequest (String applicantName, String officer) {
        this.applicantName = applicantName;
        this.officer = officer;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

}
