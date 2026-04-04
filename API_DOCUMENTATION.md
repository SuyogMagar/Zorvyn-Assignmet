# API Documentation

This document provides a comprehensive overview of the REST API endpoints available in the **Financial Tracker** application. The APIs are logically grouped into three main categories: **Financial Records**, **Users**, and **Dashboard Summaries**. 

> [!IMPORTANT]
> **Authentication:** Most endpoints are secured and require **Basic Authentication**. You must pass the base64-encoded `username:password` in the `Authorization` header. Access to specific endpoints depends on the user's role (`ADMIN`, `ANALYST`, `VIEWER`).

---

## 1. Financial Records (`/api/records`)

### Get All Records
* **Method:** `GET`
* **URL:** `/api/records`
* **Access:** Admin, Analyst, Viewer
* **Description:** Retrieves a list of all financial records present in the system.

### Create a Record
* **Method:** `POST`
* **URL:** `/api/records`
* **Access:** Admin, Analyst
* **Description:** Adds a new financial record (Income/Expense) to the database.
* **Request Body (JSON):**
```json
{
  "amount": 1000.00,
  "type": "INCOME",
  "category": "Bonus",
  "date": "2026-03-01",
  "notes": "Bonus/Incentive"
}
```

### Delete a Record
* **Method:** `DELETE`
* **URL:** `/api/records/{id}`
* **Access:** Admin, Analyst
* **Description:** Deletes an existing financial record by its ID.

---

## 2. Users (`/api/users`)

### Get All Users
* **Method:** `GET`
* **URL:** `/api/users`
* **Access:** Admin, Viewer
* **Description:** Retrieves a complete list of all registered users.

### Get User by ID
* **Method:** `GET`
* **URL:** `/api/users/{id}`
* **Access:** Admin, Analyst
* **Description:** Retrieves information about a specific user using their ID.

### Create a User
* **Method:** `POST`
* **URL:** `/api/users`
* **Access:** Admin
* **Description:** Registers a new user with a specific role and status. 
* **Request Body (JSON):**
```json
{
  "name": "Viewer Demo",
  "email": "viewerDemo@example.com",
  "password": "securepassword123",
  "role": "VIEWER",
  "status": "ACTIVE"
}
```

### Activate User
* **Method:** `PATCH`
* **URL:** `/api/users/{id}/activate`
* **Access:** Admin
* **Description:** Changes an inactive user's status back to `ACTIVE`, restoring their login privileges.

### Deactivate User
* **Method:** `PATCH`
* **URL:** `/api/users/{id}/deactivate`
* **Access:** Admin
* **Description:** Changes an active user's status to `INACTIVE`, preventing them from logging in.

---

## 3. Dashboard Summaries (`/api/summary`)

Provides aggregated data and analytics, generally used for populating visual dashboards.

### Total Income
* **Method:** `GET`
* **URL:** `/api/summary/total-income`
* **Description:** Calculates and returns the absolute sum of all `INCOME` records.

### Total Expense
* **Method:** `GET`
* **URL:** `/api/summary/total-expense`
* **Description:** Calculates and returns the absolute sum of all `EXPENSE` records.

### Net Balance
* **Method:** `GET`
* **URL:** `/api/summary/net-balance`
* **Description:** Returns the complete net balance (Total Income minus Total Expense).

### Monthly Summary (General)
* **Method:** `GET`
* **URL:** `/api/summary/monthly`
* **Description:** Provides a breakdown of financial metrics across different months.

### Monthly Income
* **Method:** `GET`
* **URL:** `/api/summary/monthly-income`
* **Description:** Provides a month-by-month breakdown specifically for `INCOME` records.

### Category-wise Breakdown
* **Method:** `GET`
* **URL:** `/api/summary/category-wise`
* **Description:** Aggregates and groups records by their explicitly defined categories (e.g., Salary, Food, Utilities) for pie-chart distributions.
