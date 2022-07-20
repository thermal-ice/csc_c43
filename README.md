# csc_c43
Project for C43

## Project Setup

#### Frameworks and tools used
Everything is in java, obviously. We're using maven as our package manager,
Springboot as our backend and API router, and mySQL as our database. MySQL can be run
via a docker container, but it doesn't need to be.


#### Maven 
You need to have maven installed on your machine to run this application. 


#### dotenv file
We need to create a .env file to store our environment variables for connecting to the database.
To do so, create a file called `.env` in the root directory. Then add the following fields to it:
```dotenv
CONNECTION_STRING=myConnectionString
USERNAME=myUsername
PASSWORD=myPassword
```
For example, some default values might look like:
```dotenv
CONNECTION_STRING=jdbc:mysql://localhost:3306/c43_database
USERNAME=root
PASSWORD=root
```
This will obviously differ based on how you set up your database.

#### Docker
This is entirely optional. You would need to install docker, and then run:
`docker-compose up` in the root directory. This will create a docker container for mysql,
running on host port 3307, and with `username=root` and `password=root`. It will also create a database
called `test_db`