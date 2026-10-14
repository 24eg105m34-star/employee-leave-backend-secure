# Employee Leave Management Backend (security-hardened demo)

Spring Boot 3, Java 17, Spring Data JPA, MySQL, BCrypt password hashing, JWT authentication, and server-side role checks.

## Run locally
1. Install JDK 17+, Maven, and MySQL.
2. Create the database or allow the configured local connection to create it:
   `CREATE DATABASE employee_leave;`
3. Set environment variables for your environment before starting Spring Boot. On Windows PowerShell, use `$env:DB_USERNAME="root"`, `$env:DB_PASSWORD="your-mysql-password"`, `$env:JWT_SECRET="replace-with-a-random-secret-at-least-32-characters"`, and `$env:FRONTEND_ORIGIN="http://localhost:5173"`. Do not commit secrets. Required settings:
   - `DB_URL=jdbc:mysql://localhost:3306/employee_leave`
   - `DB_USERNAME=your_mysql_user`
   - `DB_PASSWORD=your_mysql_password`
   - `JWT_SECRET` to a random secret of at least 32 bytes (use a unique secret, not the local fallback)
   - `FRONTEND_ORIGIN=http://localhost:5173`
4. Run `mvn spring-boot:run`.
5. Start the frontend on `http://localhost:5173`.

The application refuses to start unless `JWT_SECRET` is set to a unique secret of at least 32 bytes. Never commit or share it. The API uses stateless bearer JWTs with a one-hour default expiry. Passwords are hashed using BCrypt. SQL output is disabled by default.

## First administrator setup
Public registration always creates an `EMPLOYEE`; the client cannot choose its role. Register your account through the UI, then use a local trusted MySQL admin connection to promote that one account:

```sql
UPDATE users SET role = 'ADMIN' WHERE email = 'your-email@example.com';
```

Confirm exactly one intended account was updated. Sign out and sign in again to refresh the UI role. After that, create Manager accounts using the protected Admin > Employee Management screen. Never expose MySQL publicly or share database credentials.

## API permissions
- Public: `GET /api/health`, `POST /api/users/register`, `POST /api/users/login`, `GET /api/leave-types` (active types only).
- Authenticated: profile, leave request operations.
- Employee: can only read their own leave requests; the server derives the request owner from the verified JWT and ignores any client-supplied user ID.
- Manager/Admin: can review requests; nobody can approve or reject their own request.
- Admin only: user/employee management, department management, and leave-type create/update/deactivate.

## Security notes and limitations
This is a student-project baseline, not a production security certification. Before public deployment, add HTTPS, rate limiting/lockout, audit logging, account recovery, stronger password policy, data backups, and automated tests. A JWT stored in `sessionStorage` reduces persistence after the browser session but is still accessible to JavaScript; protect against XSS and consider a Secure, HttpOnly, SameSite cookie architecture for production. Manager visibility is organization-wide because the current schema has no manager/team assignment. Add team ownership rules before using real employee data.
