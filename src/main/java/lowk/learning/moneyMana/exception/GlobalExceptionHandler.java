package lowk.learning.moneyMana.exception;

import lowk.learning.moneyMana.contanst.Constant;
import lowk.learning.moneyMana.util.Msg;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Msg> CustomMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String msg = ex.getAllErrors().get(0).getDefaultMessage();
        return new Msg(Constant.FAIL, msg).done();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Msg> CustomHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        String msg = ex.getMessage();
        return new Msg(Constant.FAIL, msg).done();
    }
}
