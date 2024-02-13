package ec.gm.loanapplicationprocessing.model;

import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoanApplicationTest {
    @Test
    public void testCreateLoanApplication () {
        LoanApplication loanApplication =
                LoanApplicationTestFactory.getInstance().createLoanApplication();
        Assertions.assertEquals(LoanApplicationStatus.CREATED, loanApplication.getStatus());
        Assertions.assertTrue(loanApplication.getChecklistItems().isEmpty());
        Assertions.assertTrue(loanApplication.getActions().isEmpty());
    }

    @Test
    public void testOpenLoanApplication () throws LoanApplicationException {
        LoanApplication loanApplication =
                LoanApplicationTestFactory.getInstance().createLoanApplication();
        loanApplication.openLoanApplication("Officer 2");
        Assertions.assertEquals(LoanApplicationStatus.OPEN, loanApplication.getStatus());
    }

    @Test
    public void testCloseLoanApplication () throws LoanApplicationException {
        LoanApplication loanApplication =
                LoanApplicationTestFactory.getInstance().createLoanApplication();
        loanApplication.closeLoanApplication(LoanApplicationStatus.PENDING);
        Assertions.assertEquals(LoanApplicationStatus.PENDING, loanApplication.getStatus());
    }

    @Test
    public void testCloseWrongStatusLoanApplication () {
        LoanApplication loanApplication =
                LoanApplicationTestFactory.getInstance().createLoanApplication();
        Exception exception = Assertions.assertThrows(LoanApplicationException.class, () -> {
            loanApplication.closeLoanApplication(LoanApplicationStatus.OPEN);
        });

    }

    @Test
    public void createChecklistItem ()  {
        LoanApplicationChecklistItem item = null;
        try {
            item =
                    new LoanApplicationChecklistItem(
                            1L, "Start task", "Start the loan application" );
        } catch (LoanApplicationException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(item.getComplete());
    }

    @Test
    public void updateChecklistItem () throws LoanApplicationException {
        LoanApplicationChecklistItem item = LoanApplicationTestFactory.getInstance().createChecklistItem();
        Exception exception = Assertions.assertThrows(LoanApplicationException.class, () -> {
            item.updateChecklistItem("", null, "My notes", "Officer GM");
        });
        exception = Assertions.assertThrows(LoanApplicationException.class, () -> {
            item.updateChecklistItem(null, null, "My notes", "Officer GM");
        });
        exception = Assertions.assertThrows(LoanApplicationException.class, () -> {
            item.updateChecklistItem("Task Name", null, "", "Officer GM");
        });
        Assertions.assertDoesNotThrow(() -> {
            item.updateChecklistItem("Task Name", null, "Updated notes", "Officer GM");
        });

    }

    @Test
    public void updateChecklistItemOtherOfficer () throws LoanApplicationException {
        LoanApplicationChecklistItem item = LoanApplicationTestFactory.getInstance().createChecklistItem();
        Exception exception = Assertions.assertThrows(LoanApplicationException.class, () -> {
            item.updateChecklistItem("Task Name", Boolean.TRUE, "My notes", "Officer Other");
        });
    }
}
