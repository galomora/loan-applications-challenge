package ec.gm.loanapplicationprocessing.database;

import ec.gm.loanapplicationprocessing.model.LoanApplicationChecklistItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationChecklistItemRepository extends CrudRepository<LoanApplicationChecklistItem, Long> {
}
