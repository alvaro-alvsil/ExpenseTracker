# Expense Tracker

## Descripción
Esta aplicación permite a los usuarios gestionar sus gastos personales. Incluye funcionalidades de registro e inicio de sesión, y soporta operaciones CRUD sobre los gastos registrados. Está diseñada como solución al enunciado [Expense Tracker API](https://roadmap.sh/projects/expense-tracker-api).

- Gestión de acceso: Controlado medtiante roles (administrador/ususario) y autenticación con tokens JWT.

- Gastos: Se permiten crear, listar, editar y eliminar registros de datos, los cuales se clasifican en múltiples categorías.

## Requisitos
- Java 21
- Maven
- MySQL

## Instalación
1. Clonar repositorios
```bash
git clone https://github.com/alvaro-alvsil/ExpenseTracker.git
```
2. Definir las siguientes variables de entorno:
   - JWT_SECRET: Clave secreta para firmar y verificar tokens JWT.
   - JWT_EXPIRATION_MS: Tiempo de expiración del token JWT.
   - SPRING_DATASOURCE_URL: URL de conexión a la base de datos.
   - SPRING_DATASOURCE_USERNAME: Usuario de la base de datos.
   - SPRING_DATASOURCE_PASSWORD: Contraseña del usuario.


