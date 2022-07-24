# About Project
**Name:** Employee Management System

**Description:** Employee Management System Restful API using Spring Boot 

**Author:** Gurshobit Brar

## Download & Execution
    
Download From Releases Section 

[Latest Release v1.0.0](/releases/latest)

or Click on Link Below

[Download JAR File](/releases/download/v1.0.0/EmployeeManagementSystem-1.0.0-SNAPSHOT.jar)

    Execution: java -java EmployeeManagementSystem-1.0.0-SNAPSHOT.jar

    
## Server Details

    App Url: http://localhost:8085/EmployeeManagementSystem/
    Url: http://localhost
    Port: 8085
    Path: EmployeeManagementSystem/


## Auth Details

**_Username:_** _admin@dummymail.com_

**_Password:_** _12345678_

## API End Points

**GET:**

_Users List: http://localhost:8085/EmployeeManagementSystem/api/account/users/_

        Auth Required: yes
        User Role: ADMIN

        Sameple Output:
        {
            "data": [
                {
                    "id": 1,
                    "username": "admin@dummymail.com",
                    "password": "PasswordHashes",
                    "roles": [
                        {
                            "id": 1,
                            "name": "ROLE_ADMIN"
                        }
                    ]
                },
                {
                    "id": 2,
                    "username": "user@dummymail.com",
                    "password": "PasswordHashes",
                    "roles": [
                        {
                            "id": 2,
                            "name": "ROLE_USER"
                        }
                    ]
                }
            ],
            "success": true,
            "info": {
                "message": "List of all users"
            }
        }

_User Roles: http://localhost:8085/EmployeeManagementSystem/api/account/roles/_

        Auth Required: yes
        User Role: ADMIN

        Sample Output:
        {
            "data": [
                {
                    "id": 1,
                    "name": "ROLE_ADMIN"
                },
                {
                    "id": 2,
                    "name": "ROLE_USER"
                },
                {
                    "id": 3,
                    "name": "ROLE_EDITOR"
                }
            ],
            "success": true,
            "info": {
                "message": "List of all roles"
            }
        }
        

_Employees List: http://localhost:8085/EmployeeManagementSystem/api/employees/list_

        Auth Required: yes
        User Role: ADMIN,USER

        Sample Output:
        {
            "data": [
                {
                    "id": 1,
                    "firstName": "Ajay",
                    "lastName": "Kumar",
                    "email": "ajay.kumar@dummymail.com"
                },
                {
                    "id": 3,
                    "firstName": "Ram",
                    "lastName": "Singh",
                    "email": "ram.singh@dummymail.com"
                },
                {
                    "id": 6,
                    "firstName": "Neha",
                    "lastName": "Kumari",
                    "email": "neha.kumari@dummymail.com"
                },
                {
                    "id": 7,
                    "firstName": "test",
                    "lastName": "employee",
                    "email": "test.employee@dummymail.com"
                }
            ],
            "success": true,
            "info": {
                "message": "List of all employees"
            }
        }


_Employee Detail: http://localhost:8085/EmployeeManagementSystem/api/employees/employee/{id}_
    
        Example Url:
            http://localhost:8085/EmployeeManagementSystem/api/employees/employee/5

        Auth Required: yes
        User Role: ADMIN,USER

        Sample Output:
        {
            "data": {
                "id": 5,
                "firstName": "Ajay",
                "lastName": "Kumar",
                "email": "ajay.kumar@dummymail.com"
            },
            "success": true,
            "info": {
                "message": "Employee Details with id: 5"
            }
        }

_Sorted Employee List: http://localhost:8085/EmployeeManagementSystem/api/employees/sort?direction={directionName}_

        Example Urls: 
            http://localhost:8085/EmployeeManagementSystem/api/employees/sort?direction=desc
            http://localhost:8085/EmployeeManagementSystem/api/employees/sort?direction=asc
        
        Auth Required: yes
        User Role: ADMIN,USER

        Sample Output:
        {
            "data": [
                {
                    "id": 7,
                    "firstName": "test",
                    "lastName": "employee",
                    "email": "test.employee@dummymail.com"
                },
                {
                    "id": 2,
                    "firstName": "Ram",
                    "lastName": "Kumar",
                    "email": "ram.kumar@dummymail.com"
                },
                {
                    "id": 6,
                    "firstName": "Neha",
                    "lastName": "Kumari",
                    "email": "neha.kumari@dummymail.com"
                },
                {
                    "id": 1,
                    "firstName": "Ajay",
                    "lastName": "Kumar",
                    "email": "ajay.kumar@dummymail.com"
                }
            ],
            "success": true,
            "info": {
                "message": "List of all employees"
            }
        }


_Search Employee: http://localhost:8085/EmployeeManagementSystem/api/employees/search/{query}_

        Exmaple Url: http://localhost:8085/EmployeeManagementSystem/api/employees/search/employeeName
        
        Auth Required: yes
        User Role: ADMIN,USER

        Sample Output:
        {
            "data": [
                {
                    "id": 7,
                    "firstName": "test",
                    "lastName": "employee",
                    "email": "test.employee@dummymail.com"
                }
            ],
            "success": true,
            "info": {
                "message": "List of all employees"
            }
        }


**POST:**

_Add Employee: http://localhost:8085/EmployeeManagementSystem/api/employees/add_    

        Auth Required: yes
        User Role: ADMIN,USER

        Sample Input:
        {
            "firstName" : "test",
            "lastName" : "employee",
            "email" : "test.employee@dummymail.com"
        }

        Sample Output:
        {
            "data": {
                "id": 7,
                "firstName": "test",
                "lastName": "employee",
                "email": "test.employee@dummymail.com"
            },
            "success": true,
            "info": {
                "message": "Employee Saved Successfully!"
            }
        }

_Add User Role: http://localhost:8085/EmployeeManagementSystem/api/account/roles/add_

        Auth Required: yes
        User Role: ADMIN

        Sample Input:
        {
            "name" : "editor"
        }

        Sample Output:
        {
            "data": {
                "id": 3,
                "name": "ROLE_EDITOR"
            },
            "success": true,
            "info": {
                "message": "Role Saved Successfully"
            }
        }


_Add User: http://localhost:8085/EmployeeManagementSystem/api/account/users/add_

        Auth Required: yes
        User Role: ADMIN

        Sample Input:
        {
            "username":"user@dummymail.com",
            "password":"12345678",
            "roles":[{
                "id":2,
                "name":"ROLE_USER"
            }]
        }

        Sample Output:
        {
            "data": {
                "id": 2,
                "username": "user@dummymail.com",
                "password": "PasswordHashes",
                "roles": [
                    {
                        "id": 2,
                        "name": "ROLE_USER"
                    }
                ]
            },
            "success": true,
            "info": {
                "message": "User Saved Successfully"
            }
        }
_Assign User Role: http://localhost:8085/EmployeeManagementSystem/api/account/users/assign_

        Auth Required: yes
        User Role: ADMIN

        Sample Input:
        {
            "userId" : "2",
            "roleId": "3"
        }
        Sample Output:
        {
            "data": {
                "id": 2,
                "username": "user@dummymail.com",
                "password": "PasswordHashes",
                "roles": [
                    {
                        "id": 2,
                        "name": "ROLE_USER"
                    },
                    {
                        "id": 3,
                        "name": "ROLE_EDITOR"
                    }
                ]
            },
            "success": true,
            "info": {
                "message": "Role assigned to user Successfully"
            }
        }

**PUT:**

_Update Employee: http://localhost:8085/EmployeeManagementSystem/api/employees/update/{id}_

        Example Url: 
            http://localhost:8085/EmployeeManagementSystem/api/employees/update/5

        Auth Required: yes
        User Role: ADMIN

        Sample Input:
        {
            "id" : 5,
            "firstName" : "Test",
            "lastName" : "Delete",
            "email" : "test.delete@dummymail.com"
        }
        Sample Output:
        {
            "data": {
                "id": 5,
                "firstName": "Test",
                "lastName": "Delete",
                "email": "test.delete@dummymail.com"
            },
            "success": true,
            "info": {
                "message": "Updated employee with id: 5"
            }
        }

_Un-assign User Role: http://localhost:8085/EmployeeManagementSystem/api/account/users/assign_

        Auth Required: yes
        User Role: ADMIN

        Sample Input:
        {
            "userId" : "2",
            "roleId": "3"
        }
        Sample Output:
        {
            "data": {
                "id": 2,
                "username": "user@dummymail.com",
                "password": "PasswordHashes",
                "roles": [
                    {
                        "id": 2,
                        "name": "ROLE_USER"
                    }
                ]
            },
            "success": true,
            "info": {
                "message": "Role un-assigned to user Successfully"
            }
        }

**DELETE:**

_Delete Employee: http://localhost:8085/EmployeeManagementSystem/api/employees/delete/{id}_

        Example Url: http://localhost:8085/EmployeeManagementSystem/api/employees/delete/6

        Auth Required: yes
        User Role: ADMIN

        Sample Output:
        {
            "data": {
                "id": 5,
                "firstName": "Test",
                "lastName": "Delete",
                "email": "test.delete@dummymail.com"
            },
            "success": true,
            "info": {
                "message": "Deleted employee with id: 5"
            }
        }
