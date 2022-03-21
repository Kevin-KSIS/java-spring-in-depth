package lowk.learning.moneyMana.controller;

import lowk.learning.moneyMana.dto.CategoryDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/category")
public class CategoryController {

    @PostMapping("/add")
    public void add(@RequestBody CategoryDTO body){
    }

    @PutMapping("/edit")
    public void edit(@RequestBody CategoryDTO body){
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody CategoryDTO body){
    }

    @GetMapping("/hide")
    public void hide(@RequestBody CategoryDTO body){
    }

    @GetMapping("/display")
    public void display(@RequestBody CategoryDTO body){
    }
}
