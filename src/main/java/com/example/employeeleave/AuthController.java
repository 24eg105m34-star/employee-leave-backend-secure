package com.example.employeeleave;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.Map;
@RestController @RequestMapping("/api/users")
public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
 @GetMapping("/me") public Map<String,Object> me(org.springframework.security.core.Authentication auth){User u=users.findByEmailIgnoreCase(auth.getName()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));return publicUser(u);}
 @GetMapping("/{id}") public Map<String,Object> getUser(@PathVariable Long id,org.springframework.security.core.Authentication auth){User target=users.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));if(!"ROLE_ADMIN".equals(auth.getAuthorities().iterator().next().getAuthority())&&!target.getEmail().equalsIgnoreCase(auth.getName()))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You cannot view another user's profile");return publicUser(target);}
 @PostMapping("/register") public ResponseEntity<?> register(@Valid @RequestBody RegisterBody b){
  String email=b.getEmail().trim().toLowerCase();
  if(users.existsByEmailIgnoreCase(email)) throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
  User u=users.save(new User(b.getName().trim(),email,encoder.encode(b.getPassword())));
  return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Registration successful","user",publicUser(u)));
 }
 @PostMapping("/login") public Map<String,Object> login(@Valid @RequestBody LoginBody b){
  User u=users.findByEmailIgnoreCase(b.getEmail().trim()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));
  if(!encoder.matches(b.getPassword(),u.getPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
  return Map.of("message","Login successful","user",publicUser(u),"token",jwt.issue(u));
 }
 private Map<String,Object> publicUser(User u){return Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole());}
 public static class RegisterBody {
  @NotBlank @Size(max=100) private String name;
  @NotBlank @Email @Size(max=180) private String email;
  @NotBlank @Size(min=6,max=100) private String password;
  public String getName(){return name;} public String getEmail(){return email;} public String getPassword(){return password;}
  public void setName(String v){name=v;} public void setEmail(String v){email=v;} public void setPassword(String v){password=v;}
 }
 public static class LoginBody {
  @NotBlank @Email private String email; @NotBlank private String password;
  public String getEmail(){return email;} public String getPassword(){return password;}
  public void setEmail(String v){email=v;} public void setPassword(String v){password=v;}
 }
}
