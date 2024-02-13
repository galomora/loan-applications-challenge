package ec.gm.loanapplicationprocessing.controller.handler;

import ec.gm.loanapplicationprocessing.model.exception.LoanApplicationException;
import ec.gm.loanapplicationprocessing.service.exception.LoanApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoanApplicationException.class)
    public ResponseEntity<EndpointErrorMessage> generateNotFoundException(LoanApplicationException ex) {
        EndpointErrorMessage message = new EndpointErrorMessage(
                ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<EndpointErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<EndpointErrorMessage> generateNoElementException(NoSuchElementException ex) {
        EndpointErrorMessage message = new EndpointErrorMessage(
                ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<EndpointErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanApplicationNotFoundException.class)
    public ResponseEntity<EndpointErrorMessage> generateNoElementException(LoanApplicationNotFoundException ex) {
        EndpointErrorMessage message = new EndpointErrorMessage(
                ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());
        return new ResponseEntity<EndpointErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
