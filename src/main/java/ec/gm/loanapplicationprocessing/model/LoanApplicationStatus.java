package ec.gm.loanapplicationprocessing.model;

import java.util.List;

public enum LoanApplicationStatus {
    CREATED, OPEN, PENDING, APPROVED, REJECTED;

    public static Boolean isClosingStatus (LoanApplicationStatus status) {
        return List.of(PENDING, APPROVED, REJECTED).contains(status);
    }

    public static List<LoanApplicationStatus> getClosingValues () {
        return List.of(PENDING, APPROVED, REJECTED);
    }
}
