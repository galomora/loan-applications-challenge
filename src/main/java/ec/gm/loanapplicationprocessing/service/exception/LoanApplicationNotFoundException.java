package ec.gm.loanapplicationprocessing.service.exception;

public class LoanApplicationNotFoundException extends Exception{
    public LoanApplicationNotFoundException() {
        super();
    }

    public LoanApplicationNotFoundException(String message) {
        super(message);
    }

    public LoanApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanApplicationNotFoundException(Throwable cause) {
        super(cause);
    }

    protected LoanApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
