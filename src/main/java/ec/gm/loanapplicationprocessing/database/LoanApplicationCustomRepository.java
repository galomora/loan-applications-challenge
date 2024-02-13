package ec.gm.loanapplicationprocessing.database;

import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.model.LoanApplicationStatus;

import java.util.List;

/**
 * Custom queries for {@link LoanApplication}
 */
public interface LoanApplicationCustomRepository {
    /**
     * Find {@link LoanApplication} by its status
     * @param status {@link LoanApplicationStatus}
     * @return List of {@link LoanApplication}
     */
    List<LoanApplication> findByStatus (LoanApplicationStatus status);

    /**
     * Find {@link LoanApplication} by its status and assigned officer
     * @param status {@link LoanApplicationStatus}
     * @param officer name of the officer
     * @return List of {@link LoanApplication}
     */
    List<LoanApplication> findByStatusOfficer (LoanApplicationStatus status, String officer);

    /**
     * Find {@link LoanApplication} by its assigned officer
     * @param officer
     * @param officer name of the officer
     * @return List of {@link LoanApplication}
     */
    List<LoanApplication> findByOfficer ( String officer);
}
