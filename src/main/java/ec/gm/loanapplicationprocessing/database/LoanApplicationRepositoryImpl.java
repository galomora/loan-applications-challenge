package ec.gm.loanapplicationprocessing.database;

import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.model.LoanApplicationStatus;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
@Repository
public class LoanApplicationRepositoryImpl implements LoanApplicationCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @SuppressWarnings("unchecked")
    public List<LoanApplication> findByStatus(LoanApplicationStatus status) {
        return this.entityManager.createQuery(
            "from LoanApplication l where l.status = ?").setParameter(1, status).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LoanApplication> findByStatusOfficer(LoanApplicationStatus status, String officer) {
        return this.entityManager.createQuery(
                "from LoanApplication l where l.status = ? and l.assignedOfficer = ?")
                .setParameter(1, status).setParameter(2, officer).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LoanApplication> findByOfficer(String officer) {
        return this.entityManager.createQuery(
                        "from LoanApplication l where l.assignedOfficer = ?")
                .setParameter(1, officer).setParameter(2, officer).getResultList();
    }
}
