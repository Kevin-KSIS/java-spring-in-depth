package lowk.learning.moneyMana.controller;

import lowk.learning.moneyMana.dto.AccountDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController{

    @PostMapping("/add")
    public void add(@RequestBody AccountDTO body){
    }

    @PutMapping("/edit")
    public void edit(@RequestBody AccountDTO body){
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody AccountDTO body){
    }

    @GetMapping("/lock")
    public void lock(@RequestBody AccountDTO body){
    }

    @GetMapping("/unlock")
    public void unlock(@RequestBody AccountDTO body){
    }

}
