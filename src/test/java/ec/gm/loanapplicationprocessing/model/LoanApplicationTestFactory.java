package ec.gm.loanapplicationprocessing.model;

import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;

import java.util.List;

public class LoanApplicationTestFactory {
    private static LoanApplicationTestFactory instance;

    public static LoanApplicationTestFactory getInstance () {
        if (instance == null) { instance = new LoanApplicationTestFactory (); }
        return instance;
    }

    public LoanApplication createLoanApplication () {
        LoanApplication loanApplication = new LoanApplication("Galo", "Officer GM");
        return loanApplication;
    }

    public LoanApplicationChecklistItem createChecklistItem () throws LoanApplicationException {
        LoanApplicationChecklistItem item = null;
        LoanApplication loanApplication = createLoanApplication();
        loanApplication.openLoanApplication("Officer GM");
        try {
            item = new LoanApplicationChecklistItem(
                    loanApplication , "Start task", "Start the loan application" );
        } catch (LoanApplicationException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public LoanApplication openLoanApplication () throws LoanApplicationException {
        LoanApplication loanApplication =
                createLoanApplication();
        loanApplication.openLoanApplication("Officer GM");
        return loanApplication;
    }

    public LoanApplication closeLoanApplication () {
        LoanApplication loanApplication =
                createLoanApplication();
        try {
            loanApplication.closeLoanApplication(LoanApplicationStatus.PENDING);
        } catch (LoanApplicationException e) {
            throw new RuntimeException(e);
        }
        return loanApplication;
    }

    public LoanApplicationAction createOpenAction () throws LoanApplicationException {
        LoanApplicationAction action = new LoanApplicationAction(
                ActionName.OPEN, "Officer GM", openLoanApplication()
        );
        return action;
    }

    public LoanApplicationAction createCloseAction () {
        LoanApplicationAction action = new LoanApplicationAction(
                ActionName.CLOSE, "Officer GM", closeLoanApplication()
        );
        return action;
    }

    public List<LoanApplication> createLoanApplicationList () {
        List<LoanApplication> loanApplications = List.of(createLoanApplication(), createLoanApplication());
        return loanApplications;
    }

}
