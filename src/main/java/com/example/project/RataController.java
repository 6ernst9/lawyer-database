package com.example.project;

import com.example.project.RataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping("rate")
@RestController
public class RataController {
    private final RataService rataService;
    @Autowired
    public RataController(RataService rataService) {
        this.rataService = rataService;
    }
    @PostMapping
    public void addRata(@RequestBody RataRequest rata){
        rataService.addRata(rata.getId_cj(), rata.getData(), rata.getSuma());
    }
    @GetMapping
    public List<Rata> getAllRate(){
        return rataService.getAllRate();
    }
}
