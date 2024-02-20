package ec.gm.loanapplicationprocessing.model;

import jakarta.persistence.*;

@Entity
public class LoanApplicationAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column (length = 25, nullable = false)
    private ActionName actionName;

    @Column(nullable = true, length = 250)
    private String officer;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn (
            foreignKey = @ForeignKey(name="ACTION_LOAN_APPLICATION_FK"),
            insertable = true, updatable = true, name="LOAN_APPLICATION_ID"
    )
    private LoanApplication loanApplication;

    public LoanApplicationAction () {}

    public LoanApplicationAction(ActionName actionName, String officer, LoanApplication loanApplication) {
        this.actionName = actionName;
        this.officer = officer;
        this.loanApplication = loanApplication;
    }

    public Long getId() {
        return id;
    }

    public ActionName getActionName() {
        return actionName;
    }

    public String getOfficer() {
        return officer;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }
}
