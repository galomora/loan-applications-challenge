package ec.gm.loanapplicationprocessing.service;

import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.model.LoanApplicationAction;
import ec.gm.loanapplicationprocessing.model.LoanApplicationChecklistItem;
import ec.gm.loanapplicationprocessing.model.LoanApplicationStatus;
import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;
import ec.gm.loanapplicationprocessing.service.exception.LoanApplicationNotFoundException;

import java.util.List;

public interface LoanApplicationService {
    /**
     * Creates a {@link LoanApplication}
     * @param applicantName name of the person that applies to the loan
     * @param officer that creates the loan application
     */
    LoanApplication createLoanApplication (String applicantName, String officer);

    /**
     * Creates a Chechlist item for a Loan Application
     * @param task name of the task
     * @param loanApplicationId id of the {@link LoanApplication}
     * @param notes text required
     * @return {@link LoanApplicationChecklistItem} created
     * @throws LoanApplicationException error while creating {@link LoanApplicationChecklistItem}
     */
    LoanApplicationChecklistItem createLoanApplicationChecklistItem (String task, Long loanApplicationId, String notes) throws LoanApplicationException;

    /**
     * Opens a {@link LoanApplication}
     * @param loanApplicationId id of the {@link LoanApplication}
     * @param officer that opens the application
     */
    LoanApplicationAction openLoanApplication (Long loanApplicationId, String officer) throws LoanApplicationException;

    /**
     * Closes a {@link LoanApplication}
     * @param loanApplicationId id of the {@link LoanApplication} to close
     * @param officer that closes the loan application
     * @param loanApplicationStatus one of the values of {@link LoanApplicationStatus} to close the application
     */
    LoanApplicationAction closeLoanApplication (Long loanApplicationId, String officer, LoanApplicationStatus loanApplicationStatus) throws LoanApplicationException;

    /**
     * Gets a list of {@link LoanApplication} searching by its status
     * @param loanApplicationStatus one of the values of {@link LoanApplicationStatus}
     * @return list of {@link LoanApplication}
     */
    List<LoanApplication> listLoanApplications (LoanApplicationStatus loanApplicationStatus);
    /**
     * Gets a list of {@link LoanApplication} searching by its assigned officer
     * @param assignedOfficer officer that the loan applications are assigned to
     * @return list of {@link LoanApplication}
     */
    List<LoanApplication> listAssignedLoanApplications (String assignedOfficer);

    /**
     * Gets a list of {@link LoanApplication} searching by its assigned officer and status
     * @param assignedOfficer officer that the loan applications are assigned to
     * @param loanApplicationStatus one of the values of {@link LoanApplicationStatus}
     * @return list of {@link LoanApplication}
     */
    List<LoanApplication> listAssignedLoanApplications (String assignedOfficer, LoanApplicationStatus loanApplicationStatus);

    /**
     * Updates a {@link LoanApplicationChecklistItem}
     *
     * @param loanApplicationId id of the loan application
     *                          @param itemId id of the Checklist Item
     * @param task name of the task of the checklist item
     * @param isComplete true or false
     * @param notes for the checklist item, required
     * @param officer that executes the update
     * @throws LoanApplicationException when an error occurred while updating
     */
    LoanApplicationChecklistItem updateChecklistItem (Long loanApplicationId, Long itemId, String task, Boolean isComplete, String notes, String officer) throws LoanApplicationException;

    /**
     * Getting a loan application
     * @param id of the {@link LoanApplication}
     * @return LoanApplication searched for
     * @throws LoanApplicationNotFoundException when not found
     */
    LoanApplication getLoanApplication (Long id) throws LoanApplicationNotFoundException;
}
