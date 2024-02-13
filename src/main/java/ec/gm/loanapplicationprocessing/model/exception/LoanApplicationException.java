package ec.gm.loanapplicationprocessing.model.exception;

public class LoanApplicationException extends Exception {
    public LoanApplicationException() {
        super();
    }

    public LoanApplicationException(String message) {
        super(message);
    }

    public LoanApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanApplicationException(Throwable cause) {
        super(cause);
    }

    protected LoanApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
