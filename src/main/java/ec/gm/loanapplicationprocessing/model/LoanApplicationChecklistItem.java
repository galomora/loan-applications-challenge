package ec.gm.loanapplicationprocessing.model;


import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;

import javax.persistence.*;
import java.util.Optional;
import java.util.function.Predicate;

@Entity
public class LoanApplicationChecklistItem {

//    @EmbeddedId
//    private LoanApplicationChecklistItemId id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Boolean isComplete;
    @Column(nullable = false, length = 1000)
    private String notes;

    @Column(nullable = false, length = 50)
    private String checklistTask;

    @Column(nullable = false, name = "LOAN_APPLICATION_ID")
    private Long loanApplicationId;

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn (
            foreignKey = @ForeignKey(name="CHECKLIST_LOAN_APPLICATION_FK"),
            insertable = false, updatable = false, name="LOAN_APPLICATION_ID"
    )
    private LoanApplication loanApplication;

    public LoanApplicationChecklistItem() {
        this.isComplete = Boolean.FALSE;
    }



    public LoanApplicationChecklistItem(String checklistTask, Boolean isComplete, String notes) throws LoanApplicationException {
        this();
        this.checklistTask = checklistTask;
        this.isComplete = isComplete == null ? Boolean.FALSE : isComplete;
        Optional<String> optionalNotes = Optional.ofNullable(notes);
        this.notes = optionalNotes.filter(Predicate.not(String::isEmpty)).orElseThrow( () ->
                new LoanApplicationException("Notes cannot be empty")
        );
    }

    public LoanApplicationChecklistItem(Long loanApplicationId, String task, Boolean isComplete, String notes) throws LoanApplicationException {
        this(task, isComplete, notes);
        this.loanApplicationId = loanApplicationId;
    }

    public LoanApplicationChecklistItem(Long loanApplicationId, String task, String notes) throws LoanApplicationException {
        this(loanApplicationId, task, Boolean.FALSE, notes);
    }

    public LoanApplicationChecklistItem(LoanApplication loanApplication, String task, String notes) throws LoanApplicationException {
        this(loanApplication.getLoanApplicationId(), task, Boolean.FALSE, notes);
        this.loanApplication = loanApplication;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public String getNotes() {
        return notes;
    }

    public Long getLoanApplicationId () {
        return this.getLoanApplicationId();
    }

    public String getChecklistTask () {
        return this.getChecklistTask();
    }

    public Long getId() {
        return id;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void updateChecklistItem (String task, Boolean isComplete, String notes, String officer) throws LoanApplicationException {
        if (! canEdit (officer)) {
            throw new LoanApplicationException(
                    "Loan application is currently not OPEN and assigned to you. To work in it you must first close it, and open and assign it to you");
        }
        Optional<String> optionalTask = Optional.ofNullable(task);
        this.checklistTask = optionalTask.filter(Predicate.not(String::isEmpty)).orElseThrow( () ->
                new LoanApplicationException("Checklist task cannot be empty")
        );
        this.isComplete = isComplete == null ? this.isComplete : isComplete;
        Optional<String> optionalNotes = Optional.ofNullable(notes);
        this.notes = optionalNotes.filter(Predicate.not(String::isEmpty)).orElseThrow( () ->
            new LoanApplicationException("Notes cannot be empty")
        );
    }

    public Boolean canEdit (String officer) {
        this.getLoanApplication().getLoanApplicationId();
        return this.getLoanApplication().canEdit(officer);
    }
}
