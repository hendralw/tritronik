# Tritronik

1. select d.dept_name, count(employee_count)
from departments d
join dept_emp e on e.dept_no = d.dept_no
group by d.dept_name

2. check *FindMinimumAmount* func at ProcurementAppsApplication
![image](https://user-images.githubusercontent.com/49546149/222930392-d1d93e73-2713-43e7-bbbb-2c6733ab0224.png)

3. check *FindMaximumHeight* func at ProcurementAppsApplication
![image](https://user-images.githubusercontent.com/49546149/222930428-a3bde4c3-2cfe-45e9-9087-e5158ae2db27.png)

![image](https://user-images.githubusercontent.com/49546149/222930667-406366b0-bf35-4abc-8fc1-a228d3875b5c.png)

- All of API run with H2 Database, you can access at localhost:8080/h2.
- For run this app, make sure you run kafka and zookeeper in your local with default port 9092.

url | username | password
------------- | ------------- | -------------
jdbc:h2:mem:user_tbl | mydb | mydb
-------------
User API
-------------
endpoint  | method | request body
------------- | ------------- | -------------
localhost:8080/api/users/login | POST | {"username":"admin","password":"admin"}
localhost:8080/api/users/register | POST | {"username":"admin2","password":"admin2"}
localhost:8080/api/users/check_in | POST | {"user_id": 1 ,"room_id": 1}
localhost:8080/api/users/check_out | POST | {"user_id": 1 ,"room_id": 1}

User API with Kafka (check java console for see the result of kafka listener)
-------------
endpoint  | method 
------------- | ------------- 
localhost:8080/api/users | GET |

Room API
-------------
endpoint  | method
------------- | -------------
localhost:8080/api/rooms | GET

Reservation API
-------------
endpoint  | method | request body
------------- | ------------- | -------------
localhost:8080/api/reservations | POST | {"user_id":1,"reservations":[{"room_id":1}]}
