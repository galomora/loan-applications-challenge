package ec.gm.loanapplicationprocessing.database;

import ec.gm.loanapplicationprocessing.model.LoanApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationRepository  extends CrudRepository<LoanApplication, Long>, LoanApplicationCustomRepository {
}
