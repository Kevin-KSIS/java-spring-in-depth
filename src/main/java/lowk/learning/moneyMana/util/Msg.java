package lowk.learning.moneyMana.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Msg {
    @NonNull
    String status;

    String msg = "";
    String data = "";

    public Msg(@NonNull String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseEntity<Msg> done(){
        return ResponseEntity.ok(this);
    }
}
