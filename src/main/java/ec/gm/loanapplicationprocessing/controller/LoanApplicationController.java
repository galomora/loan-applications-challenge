package ec.gm.loanapplicationprocessing.controller;

import ec.gm.loanapplicationprocessing.controller.request.CreateLoanApplicationChecklistItemRequest;
import ec.gm.loanapplicationprocessing.controller.request.LoanApplicationRequest;
import ec.gm.loanapplicationprocessing.controller.request.UpdateLoanApplicationChecklistItemRequest;
import ec.gm.loanapplicationprocessing.controller.response.LoanApplicationChecklistItemResponse;
import ec.gm.loanapplicationprocessing.controller.response.LoanApplicationResponse;
import ec.gm.loanapplicationprocessing.model.LoanApplication;
import ec.gm.loanapplicationprocessing.model.LoanApplicationChecklistItem;
import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;
import ec.gm.loanapplicationprocessing.service.LoanApplicationService;
import ec.gm.loanapplicationprocessing.service.exception.LoanApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping (path="/{id}/items",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public List<LoanApplicationChecklistItemResponse> getItems(
            @PathVariable Long id) {
        List<LoanApplicationChecklistItem> items = new ArrayList<>();
        try {
            items = this.loanApplicationService.getItems(id);
        } catch (LoanApplicationNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return items.stream().map(item -> new LoanApplicationChecklistItemResponse(item)).collect(Collectors.toList());
    }

    @RequestMapping (path="/{loanApplicationId}/items/{itemId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public LoanApplicationChecklistItemResponse getItem (
            @PathVariable Long loanApplicationId, @PathVariable Long itemId) {
        LoanApplicationChecklistItem item = null;
        try {
            item = this.loanApplicationService.getItem(itemId, loanApplicationId);
        } catch (LoanApplicationNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return new LoanApplicationChecklistItemResponse(item);
    }

    @RequestMapping (path="/{loanApplicationId}/items",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LoanApplicationChecklistItemResponse createItem(
            @PathVariable Long loanApplicationId, @RequestBody @Validated CreateLoanApplicationChecklistItemRequest request
    ) {
        LoanApplicationChecklistItem item = null;
        try {
            item = this.loanApplicationService.createLoanApplicationChecklistItem(request.getTask(),
                    request.getLoanApplicationId(), request.getNotes());
        } catch (LoanApplicationException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new LoanApplicationChecklistItemResponse(item);
    }

    @RequestMapping (path="/{loanApplicationId}/items/{itemId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(
            @PathVariable Long loanApplicationId, @PathVariable Long itemId,
            @RequestBody @Validated UpdateLoanApplicationChecklistItemRequest request
    ) {
        LoanApplicationChecklistItem item = null;
        try {
            item = this.loanApplicationService.updateChecklistItem(loanApplicationId, itemId, request.getTask(),
                    request.getComplete(), request.getNotes(), request.getOfficer());
        } catch (LoanApplicationException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }

    }
}
