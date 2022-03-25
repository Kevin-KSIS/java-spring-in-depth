package lowk.learning.moneyMana.exception;

import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.util.Msg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.ACCEPTED)
public class RequestExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<Msg> CustomException(MethodArgumentNotValidException ex){
        String msg = ex.getAllErrors().get(0).getDefaultMessage();
        return new Msg(Constant.FAIL, msg).done();
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<Msg> CustomHttpMessageNotReadableException(HttpMessageNotReadableException ex){
//        String msg = ex.getMessage();
//        return new Msg(Constant.FAIL, msg).done();
//    }

}
