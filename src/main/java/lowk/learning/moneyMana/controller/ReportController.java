package lowk.learning.moneyMana.controller;

import lowk.learning.moneyMana.dto.CategoryDTO;
import lowk.learning.moneyMana.dto.ReportDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/report")
public class ReportController {

    @PostMapping("/")
    public void display(@RequestBody ReportDTO body){
    }

    @PostMapping("/exportPDF")
    public void exportPDF(@RequestBody ReportDTO body){
    }

    @PostMapping("/exportEXL")
    public void exportEXL(@RequestBody ReportDTO body){
    }

}
