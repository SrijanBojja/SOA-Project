# User/Auth Service
Port: 8082
Database: soa_user_db (MySQL)

Public APIs:
POST /api/auth/register
POST /api/auth/login

Protected verification endpoint:
GET /api/auth/protected

Public registration always receives VIEWER. Admin role assignment will be implemented through secured functionality later.

Environment variables:
DB_USERNAME (default root)
DB_PASSWORD (default root)
JWT_SECRET (development default exists; use your own secret for real deployments)
