package com.example.employeeleave;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController public class HomeController {
 @GetMapping("/") public Map<String,String> home(){return Map.of("message","Employee Leave Management System API is running");}
 @GetMapping("/api/health") public Map<String,String> health(){return Map.of("status","UP");}
}
