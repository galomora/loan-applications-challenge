package ec.gm.loanapplicationprocessing.model;

import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanApplicationId;
    @Column(nullable = false, length = 100)
    private String applicantName;
    @Column(nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private LoanApplicationStatus status;

    @Column(nullable = true, length = 250)
    private String assignedOfficer;

    @OneToMany (mappedBy = "loanApplication", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LoanApplicationChecklistItem> checklistItems;

    @OneToMany (mappedBy = "loanApplication", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<LoanApplicationAction> actions;

    public LoanApplication () {
        checklistItems = new ArrayList<>();
        actions = new ArrayList<>();
        status = LoanApplicationStatus.CREATED;
    }

    public LoanApplication(String applicantName, String assignedOfficer) {
        this ();
        this.applicantName = applicantName;
        this.assignedOfficer = assignedOfficer;
    }

    public void openLoanApplication (String officer) throws LoanApplicationException {
        if (LoanApplicationStatus.OPEN.equals(status)) {
            throw new LoanApplicationException(
                "Loan application is already open and assigned to " + this.assignedOfficer
            + ". You must close it first.");
        }
        status = LoanApplicationStatus.OPEN;
        this.assignedOfficer = officer;
    }

    public void closeLoanApplication (LoanApplicationStatus status) throws LoanApplicationException {
        if (LoanApplicationStatus.isClosingStatus(status)) {
            this.status = status;
        } else {
            throw new LoanApplicationException("Allowed status to close loan application: " +
                    Arrays.toString(LoanApplicationStatus.getClosingValues().toArray()));
        }

    }

    public String getApplicantName() {
        return applicantName;
    }

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public String getAssignedOfficer() {
        return assignedOfficer;
    }

    public List<LoanApplicationChecklistItem> getChecklistItems() {
        return checklistItems;
    }

    public List<LoanApplicationAction> getActions() {
        return actions;
    }

    public Boolean canEdit(String officer) {
        return LoanApplicationStatus.OPEN.equals(status)
                && (this.assignedOfficer != null && ! this.assignedOfficer.isEmpty() && this.assignedOfficer.equals(officer));

    }
}
