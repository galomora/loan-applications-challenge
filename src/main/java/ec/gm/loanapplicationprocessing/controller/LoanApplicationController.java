package ec.gm.loanapplicationprocessing.controller;

import ec.gm.loanapplicationprocessing.controller.request.LoanApplicationRequest;
import ec.gm.loanapplicationprocessing.controller.response.LoanApplicationResponse;
import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.service.LoanApplicationService;
import ec.gm.loanapplicationprocessing.service.exception.LoanApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/loanApplications")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @RequestMapping (
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationResponse createLoanApplication(
            @RequestBody @Validated LoanApplicationRequest request
            ) {
        LoanApplication loanApplication = this.loanApplicationService.createLoanApplication(
                        request.getApplicantName(), request.getOfficer());
        return new LoanApplicationResponse(loanApplication);
    }

    @RequestMapping (path="/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public LoanApplicationResponse getLoanApplication (@PathVariable Long id) {
        LoanApplication loanApplication =
                null;
        try {
            loanApplication = this.loanApplicationService.getLoanApplication(id);
        } catch (LoanApplicationNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return new LoanApplicationResponse(loanApplication);
    }


}
