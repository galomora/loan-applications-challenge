package ec.gm.loanapplicationprocessing.database;

import ec.gm.loanapplicationprocessing.model.LoanApplicationAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationActionRepository  extends CrudRepository<LoanApplicationAction, Long> {

}
