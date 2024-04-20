
## Blog Application 


Clone the project

```bash
git clone -b master https://github.com/aakash250798/blogApp-v2.git

```





Install dependencies

```bash
mvn clean install -U
mvn eclipse:eclipse
mvn eclipse:clean
```

Go to the project directory

```bash
Make changes to the application.properties file in src/main/resources folder like- mysql-username, password, etc.
Also create a database and set the url for the database in properties file 
```


Start the server

```bash
  right click on the project name and select run as -> SpringBootApp and wait for the server to Start
  check the port and open localhost:port in ur browser
  Now you have options like create/view/delete blogs after you signup/login to the application
```

