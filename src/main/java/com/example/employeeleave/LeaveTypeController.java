package com.example.employeeleave;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.*;
@RestController @RequestMapping("/api/leave-types")
public class LeaveTypeController {
 private final LeaveTypeRepository repo; public LeaveTypeController(LeaveTypeRepository r){repo=r;}
 @GetMapping public List<LeaveType> all(){return repo.findAll().stream().filter(LeaveType::isActive).toList();}
 @PostMapping @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')") public ResponseEntity<LeaveType> create(@RequestBody LeaveType b){if(b.getName()==null||b.getName().isBlank())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name is required");if(repo.existsByNameIgnoreCase(b.getName().trim()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Leave type already exists");b.setName(b.getName().trim());if(b.getMaxDays()==null||b.getMaxDays()<1)b.setMaxDays(10);return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(b));}
 @PutMapping("/{id}") @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')") public LeaveType update(@PathVariable Long id,@RequestBody LeaveType b){LeaveType x=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Leave type not found"));if(b.getName()!=null&&!b.getName().isBlank())x.setName(b.getName().trim());x.setDescription(b.getDescription());if(b.getMaxDays()!=null&&b.getMaxDays()>0)x.setMaxDays(b.getMaxDays());return repo.save(x);}
 @DeleteMapping("/{id}") @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')") public ResponseEntity<?> delete(@PathVariable Long id){LeaveType x=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Leave type not found"));x.setActive(false);repo.save(x);return ResponseEntity.noContent().build();}
}
