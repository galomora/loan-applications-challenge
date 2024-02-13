package ec.gm.loanapplicationprocessing.service;

import ec.gm.loanapplicationprocessing.database.LoanApplicationActionRepository;
import ec.gm.loanapplicationprocessing.database.LoanApplicationChecklistItemRepository;
import ec.gm.loanapplicationprocessing.database.LoanApplicationRepository;
import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;
import ec.gm.loanapplicationprocessing.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class LoanApplicationServiceTest {
    @Mock
    private LoanApplicationRepository loanApplicationRepository;

    @Mock
    private LoanApplicationActionRepository loanApplicationActionRepository;

    @Mock
    private LoanApplicationChecklistItemRepository loanApplicationChecklistItemRepository;

    @InjectMocks
    private LoanApplicationServiceImpl loanApplicationService;

    @Test
    public void createLoanApplicationTest () {
        when(loanApplicationRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().createLoanApplication());
        LoanApplication loanApplication = loanApplicationService.createLoanApplication("Galo", "Officer 1");
        Assertions.assertFalse(Optional.ofNullable(loanApplication).isEmpty());
        verify(loanApplicationRepository, times(1)).save(any());
    }

    @Test
    public void createLoanApplicationChecklistItemTest () throws LoanApplicationException {
        when(loanApplicationChecklistItemRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().createChecklistItem());
        LoanApplicationChecklistItem item = loanApplicationService.createLoanApplicationChecklistItem(
                "Creation task", 1L, "Task for creating Loan Application");
        Assertions.assertFalse(Optional.ofNullable(item).isEmpty());
        verify(loanApplicationChecklistItemRepository, times(1)).save(any());
    }

    @Test
    public void createLoanApplicationChecklistItemEmptyNotesTest () {
        Assertions.assertThrows(LoanApplicationException.class, () -> {
            loanApplicationService.createLoanApplicationChecklistItem(
                    "Creation task", 1L, "");
        });
        verify(loanApplicationChecklistItemRepository, times(0)).save(any());
    }

    @Test
    public void openLoanApplicationTest () throws LoanApplicationException {
        when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(LoanApplicationTestFactory.getInstance().createLoanApplication()));
        when(loanApplicationRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().openLoanApplication());
        when(loanApplicationActionRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().createOpenAction());
        LoanApplicationAction action = loanApplicationService.openLoanApplication(1L, "Officer 1");
        verify(loanApplicationRepository, times(1)).save(any());
        verify(loanApplicationActionRepository, times(1)).save(any());
        Assertions.assertFalse(Optional.ofNullable(action).isEmpty());
        Assertions.assertEquals(ActionName.OPEN, action.getActionName());
    }

    @Test
    public void closeLoanApplicationTest () throws LoanApplicationException {
        when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(LoanApplicationTestFactory.getInstance().createLoanApplication()));
        when(loanApplicationRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().closeLoanApplication());
        when(loanApplicationActionRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().createCloseAction());
        LoanApplicationAction action = loanApplicationService.closeLoanApplication(1L, "Officer GM", LoanApplicationStatus.REJECTED);
        verify(loanApplicationRepository, times(1)).save(any());
        verify(loanApplicationActionRepository, times(1)).save(any());
        Assertions.assertFalse(Optional.ofNullable(action).isEmpty());
        Assertions.assertEquals(ActionName.CLOSE, action.getActionName());
    }

    @Test
    public void closeLoanApplicationWrongStatusTest () throws LoanApplicationException {
        when(loanApplicationRepository.findById(any())).thenReturn(Optional.of(LoanApplicationTestFactory.getInstance().createLoanApplication()));
        Assertions.assertThrows(LoanApplicationException.class, () -> {
            loanApplicationService.closeLoanApplication(1L, "Officer GM", LoanApplicationStatus.OPEN);
        });
        verify(loanApplicationRepository, times(0)).save(any());
        verify(loanApplicationActionRepository, times(0)).save(any());

    }

    @Test
    public void listLoanApplicationsTest () {
        LoanApplicationStatus status = LoanApplicationStatus.OPEN;
        when(loanApplicationRepository.findByStatus(any())).thenReturn(LoanApplicationTestFactory.getInstance().createLoanApplicationList());
        List<LoanApplication> loanApplicationList = loanApplicationService.listLoanApplications(status);
        verify(loanApplicationRepository, times(1)).findByStatus(status);
        Assertions.assertFalse(loanApplicationList.isEmpty());
    }

    @Test
    public void listAssignedLoanApplicationsTest () {
        when(loanApplicationRepository.findByOfficer(any())).thenReturn(LoanApplicationTestFactory.getInstance().createLoanApplicationList());
        List<LoanApplication> loanApplicationList = loanApplicationService.listAssignedLoanApplications("Officer GM");
        verify(loanApplicationRepository, times(1)).findByOfficer(any());
        Assertions.assertFalse(loanApplicationList.isEmpty());
    }

    @Test
    public void listAssignedOpenLoanApplicationsTest () {
        LoanApplicationStatus status = LoanApplicationStatus.OPEN;
        when(loanApplicationRepository.findByStatusOfficer(any(), any())).thenReturn(LoanApplicationTestFactory.getInstance().createLoanApplicationList());
        List<LoanApplication> loanApplicationList = loanApplicationService.listAssignedLoanApplications("Officer GM", status);
        verify(loanApplicationRepository, times(1)).findByStatusOfficer(any(), any());
        Assertions.assertFalse(loanApplicationList.isEmpty());
    }

    @Test
    public void updateChecklistItemTest () throws LoanApplicationException {
        when(loanApplicationChecklistItemRepository.findById(any())).thenReturn(Optional.of(LoanApplicationTestFactory.getInstance().createChecklistItem()));
        when(loanApplicationChecklistItemRepository.save(any())).thenReturn(LoanApplicationTestFactory.getInstance().createChecklistItem());
        LoanApplicationChecklistItem item = loanApplicationService.updateChecklistItem(
                1L, 2L, "Create loan application task", Boolean.TRUE, "My notes", "Officer GM");
        Assertions.assertFalse(Optional.ofNullable(item).isEmpty());
        verify(loanApplicationChecklistItemRepository, times(1)).save(any());
        verify(loanApplicationChecklistItemRepository, times(1)).findById(any());

    }

}
