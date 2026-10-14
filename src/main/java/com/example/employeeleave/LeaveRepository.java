package com.example.employeeleave;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LeaveRepository extends JpaRepository<LeaveRequest,Long>{
 List<LeaveRequest> findByUserIdOrderByCreatedAtDesc(Long userId);
}
