# rental_management_system

## Objectives
Cyberjaya Online Rental Management System is a system that allows users to view real properties that are managed by the system admin. After launching the system, all users can view the property without an account; the user can also search property according to the property type. If a user wishes to be a possible tenant, one can register an account for more features. <br><br> The objectives of this system include: <br><br>
•	To allow all user to view active property in the system <br>
•	To allow user to register an account to be a possible tenant <br>
•	To allow possible tenant to edit profile <br>
•	To allow system admin to view and manage property, such as deleting, activating, and inactivating the property <br>
•	To allow system admin to print report of the property or user list <br><br>

## Assumptions and Dependencies
The assumptions in this project include: <br><br>
•	All property in Cyberjaya Online Rental Management System is managed by the admin; the user is either a user without an account or a possible tenant with an account. There is no property agent or property owner. <br>
•	Rent property feature for a tenant is not included in this system, major features for a possible tenant are currently limited to edit profile and view active property <br>
•	The program can run on BlueJ or command prompt<br>

## Graphic User Interface (GUI)
Overview of the whole project, please refer to **GUI.pdf**

## Use-case diagram
There are three actors in this project: **admin, possible tenant, and user**. <br><br>
Admin: an admin can manage all property and user; <br> 
User: a user can view the active property, but one must register an account to be a possible tenant which then one can edit the account username and password. <br> 
The major use case of each actor is listed in the below figure: <br><br>

--> Use Case Diagram of Cyberjaya Rental Management System <br>
![image](https://github.com/jianxin21/rental_management_system/assets/141626881/50494c00-f30f-4173-8fea-7c373ae2a581)
<br>

--> Admin use case diagram <br>
![image](https://github.com/jianxin21/rental_management_system/assets/141626881/14ed06f7-4594-43e5-b9eb-7280f64628d9)
<br>

--> Possible tenant use case diagram <br>
![image](https://github.com/jianxin21/rental_management_system/assets/141626881/4041eb49-15c6-427e-a5e1-edfccd502683)
<br>

--> User use case diagram <br>
![image](https://github.com/jianxin21/rental_management_system/assets/141626881/40be9e7d-ffa6-4e55-833c-1884d3eaf193)
<br>

## Design pattern
In Cyberjaya Property Management System, the design pattern **Model-View-Controller (MVC)** is implemented to achieve separation of concerns. 
<br><br>
The View component renders the final output based on the data in the Model. The Model performs actions corresponds to the related data. Then, requests are routed to Controller. The Controller acts as an interface between Model and View chooses the view to display. Every user of the system can access to the loginGui after launching the program. Admin can only see the interface of admin page (adminGui) after logged in; possible tenant can view the user interface (userGui), which a user without an account will have zero access to. Admin and tenant will not see the interface design of each other. For instance, a possible tenant class will retrieve, manipulate, and update the possible tenant information from the database. 
