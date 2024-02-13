package ec.gm.loanapplicationprocessing.service;

import ec.gm.loanapplicationprocessing.database.LoanApplicationActionRepository;
import ec.gm.loanapplicationprocessing.database.LoanApplicationChecklistItemRepository;
import ec.gm.loanapplicationprocessing.database.LoanApplicationRepository;
import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;
import ec.gm.loanapplicationprocessing.service.exception.LoanApplicationNotFoundException;
import ec.gm.loanapplicationprocessing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanApplicationActionRepository loanApplicationActionRepository;

    @Autowired
    private LoanApplicationChecklistItemRepository loanApplicationChecklistItemRepository;
    @Override
    public LoanApplication createLoanApplication(String applicantName, String officer) {
        LoanApplication loanApplication = new LoanApplication(applicantName, officer);
        LoanApplication persisted = this.loanApplicationRepository.save(loanApplication);
        return persisted;
    }

    @Override
    public LoanApplicationChecklistItem createLoanApplicationChecklistItem(String task, Long loanApplicationId, String notes) throws LoanApplicationException {
        LoanApplicationChecklistItem item = new LoanApplicationChecklistItem(loanApplicationId, task, notes);
        this.loanApplicationChecklistItemRepository.save(item);
        return item;
    }


    @Override
    @Transactional
    public LoanApplicationAction openLoanApplication(Long loanApplicationId, String officer) throws LoanApplicationException {
        LoanApplication loanApplication = this.loanApplicationRepository.findById(loanApplicationId).orElseThrow();
        loanApplication.openLoanApplication(officer);
        LoanApplicationAction action = new LoanApplicationAction(ActionName.OPEN, officer, loanApplication);
        this.loanApplicationRepository.save(loanApplication);
        return this.loanApplicationActionRepository.save(action);
    }

    @Override
    @Transactional
    public LoanApplicationAction closeLoanApplication(Long loanApplicationId, String officer, LoanApplicationStatus loanApplicationStatus) throws LoanApplicationException {
        LoanApplication loanApplication = this.loanApplicationRepository.findById(loanApplicationId).orElseThrow();
        loanApplication.closeLoanApplication(loanApplicationStatus);
        LoanApplicationAction action = new LoanApplicationAction(ActionName.CLOSE, officer, loanApplication);
        this.loanApplicationRepository.save(loanApplication);
        return this.loanApplicationActionRepository.save(action);
    }

    @Override
    public List<LoanApplication> listLoanApplications(LoanApplicationStatus loanApplicationStatus) {
        return this.loanApplicationRepository.findByStatus(loanApplicationStatus);
    }

    @Override
    public List<LoanApplication> listAssignedLoanApplications(String assignedOfficer) {
        return this.loanApplicationRepository.findByOfficer(assignedOfficer);

    }

    @Override
    public List<LoanApplication> listAssignedLoanApplications(String assignedOfficer, LoanApplicationStatus loanApplicationStatus) {
        return this.loanApplicationRepository.findByStatusOfficer(loanApplicationStatus, assignedOfficer);
    }

    @Override
    @Transactional
    public LoanApplicationChecklistItem updateChecklistItem(
            Long loanApplicationId, Long itemId, String task, Boolean isComplete, String notes, String officer) throws LoanApplicationException {
        Optional<LoanApplicationChecklistItem> optionalItem = this.loanApplicationChecklistItemRepository.findById(
                itemId);
        LoanApplicationChecklistItem item = optionalItem.orElseThrow( () -> new LoanApplicationException(
                "Checklist item not found for loan application id " + loanApplicationId + " Checkitem id= " + itemId + " task: " + task) );
        item.updateChecklistItem(task, isComplete, notes, officer);
        LoanApplicationAction action = new LoanApplicationAction(ActionName.EDIT, officer, item.getLoanApplication());
        this.loanApplicationActionRepository.save(action);
        return this.loanApplicationChecklistItemRepository.save(item);
    }

    @Override
    public LoanApplication getLoanApplication(Long id) throws LoanApplicationNotFoundException {
        Optional <LoanApplication> optionalLoanApplication = this.loanApplicationRepository.findById(id);
        return optionalLoanApplication.orElseThrow( () -> {
            return new LoanApplicationNotFoundException ("Not found Loan application with id= " + id);
        });
    }

    @Override
    public List<LoanApplicationChecklistItem> getItems(Long id) throws LoanApplicationNotFoundException {
        LoanApplication loanApplication = getLoanApplication(id);
        loanApplication.getChecklistItems().size();
        return loanApplication.getChecklistItems();
    }

    @Override
    public LoanApplicationChecklistItem getItem(Long itemId, Long loanApplicationId) throws LoanApplicationNotFoundException {
        Optional<LoanApplicationChecklistItem> optionalItem = this.loanApplicationChecklistItemRepository.findById(itemId);
        return optionalItem.filter(item -> loanApplicationId.longValue() == item.getLoanApplicationId().longValue())
                .orElseThrow( () -> new LoanApplicationNotFoundException (
                        "Checklist item with id = " + itemId + " for Loan Application with id = " + loanApplicationId + " not found"));
    }

}
