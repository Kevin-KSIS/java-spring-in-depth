package lowk.learning.moneyMana.controller;

import lowk.learning.moneyMana.dto.CategoryDTO;
import lowk.learning.moneyMana.dto.TransactionDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/trans")
public class TransactionController {
    @PostMapping("/add")
    public void add(@RequestBody TransactionDTO body){
    }

    @PutMapping("/edit")
    public void edit(@RequestBody TransactionDTO body){
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody TransactionDTO body){
    }

}
