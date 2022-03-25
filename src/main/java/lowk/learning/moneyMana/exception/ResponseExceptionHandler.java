package lowk.learning.moneyMana.exception;

import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.util.Msg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ResponseStatus(HttpStatus.ACCEPTED)
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            UsernameNotFoundException.class
    })
    public ResponseEntity<Msg> CustomHttpMessageNotReadableException(UsernameNotFoundException ex){
        String msg = ex.getMessage();
        return new Msg(Constant.FAIL, msg).done();
    }
}
