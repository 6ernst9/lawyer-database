package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("persoane")
public class PersoanaController {
    private final PersoanaService persoanaService;
    @Autowired
    public PersoanaController(PersoanaService persoanaService) {
        this.persoanaService = persoanaService;
    }
    @PostMapping
    public void addPersoana(@RequestBody Persoana persoana){
        persoanaService.addPersoana(persoana);
    }
    @GetMapping
    public List<Persoana> getAllPersoana(){
        return persoanaService.getAllPersoana();
    }
    @GetMapping("/hired")
    public List<Object[]> getPersoanaHiredInYear(@RequestParam(name = "year", defaultValue = "2022") int year, @RequestParam(name = "functie", defaultValue = "avocat") String functie){
        return persoanaService.findPersoaneHiredInYear(year, functie);
    }
    @GetMapping("/filter/avg_salary")
    public Double get_avg_salary_by_idp(@RequestParam(name = "id") Long id,
                                   @RequestParam(name = "year", defaultValue = "2022") int year){
        return persoanaService.get_avg_salary_by_idp(id, year);
    }
    @GetMapping(path = "/highest")
    public List<Object[]> getPersoanaWithHighestOnorar(){
        return persoanaService.findPersoaneWithHighestOnorar();
    }
    @GetMapping("/avg_salary")
    public List<Object[]> findAvgSalaryForYear(@RequestParam(name = "year") int year){
        return persoanaService.findAvgSalaryForYear(year);
    }
}
