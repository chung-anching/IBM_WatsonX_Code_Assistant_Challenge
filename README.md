

架構：
Container1 <Link> Container2/3
	Dockerfile
		HTML
		React.js
Container2 <Link> Container1
	Dockerfile
		Tomcat runtime
		RestAPI func by Java
		dataset.json
Container3 <Link> Container1
	Dockerfile
		FastAPI
		RestAPI func by Python
		dataset.json

  
需要下prompt給WCA@IBM去生成程式碼，以下為prompting:


## Container11(Container1):

### Prompt:

```
Create app.py can run web server service and have one function that can read json file as api call with get method. THis function also can solve CORS issue. Also giva me the requirements.txt content. After that, deploy to Docker containe and show the file structure.
```

### 產出:

**file structure:**
```

```

## my-final(Container2):

### Prompt:

```
I have zero experience in Java. Please tell me the most detail step:
Create a java web application that has one function that can read json file as api call with Get method.
After that, use Maven to build war file then deploy to Docker container using tomcat image. Set sevice port 8080
```

### 產出:
Sure, here's a detailed step-by-step guide on how to create a Java web application that reads a JSON file as an API call with GET method, builds a WAR file using Maven, deploys it to a Docker container using the Tomcat image, and sets the service port to 8080:

1. Install Java Development Kit (JDK) 11 or higher: You need JDK 11 or higher to compile and run your Java code. If you don't have JDK installed, you can download it from the official Oracle website.

2. Install Apache Maven: Apache Maven is a build automation tool used to manage Java projects. It helps to compile, test, package, and deploy Java applications. You can download and install Maven from the official website.

3. Create a new Maven project: Run the following command in your terminal to **create a new Maven project**:
```
mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-webapp
```
4. Navigate to the project directory: Change your current working directory to the project directory:
```
cd my-app
```
5. Add the Jackson library dependency: Open the **pom.xml file** in your favorite text editor and add the Jackson library dependency under the <dependencies> tag:
```xml
<dependency>
 <groupId>com.fasterxml.jackson.core</groupId>
 <artifactId>jackson-databind</artifactId>
 <version>2.13.0</version>
</dependency>
```
6. Create the **JSON file**: Create a new JSON file called data.json in the **src/main/resources** folder with the following content:
```JSON
{
 "message": "Hello, world!"
}
```
7. Create the **Java class**: Create a new Java class called **MyApp.java** in the **src/main/java/com/example** folder with the following content:
```
package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/data")
public class MyApp extends HttpServlet {

   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       String json = new String(Files.readAllBytes(Paths.get("src/main/resources/data.json")));
       ObjectMapper mapper = new ObjectMapper();
       Object obj = mapper.readValue(json, Object.class);
       response.setContentType("application/json");
       response.getWriter().write(mapper.writeValueAsString(obj));
   }
}
```
8. Build the **WAR file**: Run the following command to build the WAR file:
```
mvn package
```
9. Install Docker: Download and install Docker from the official website.

10. Pull the Tomcat image: Pull the latest Tomcat image from Docker Hub using the following command:
```
docker pull tomcat:latest
```
11. Start the Tomcat container: **Start a new Tomcat container** using the following command:
```
docker run -d -p 8080:8080 --name my-tomcat -v "$(pwd)"/target/my-app.war:/usr/local/tomcat/webapps/my-app.war tomcat:latest
```
12. Test the application: Open your web browser and navigate to **http://localhost:8080/my-app/api/data**. You should see the following output:

{"message":"Hello, world!"}

13. Set the service port: By default, the Tomcat container uses port 8080 for HTTP traffic. However, you may want to set a different port for your application. To do this, you can modify the docker run command to map the desired port to port 8080 inside the container:
```
docker run -d -p <desired_port>:8080 --name my-tomcat -v "$(pwd)"/target/my-app.war:/usr/local/tomcat/webapps/my-app.war tomcat:latest
```
In this case, replace <desired_port> with the desired port number. For example, if you want to use port 80 instead, you can run the following command:
```
docker run -d -p 80:8080 --name my-tomcat -v "$(pwd)"/target/my-app.war:/usr/local/tomcat/webapps/my-app.war tomcat:latest
```
That's it! You've created a Java web application that reads a JSON file as an API call with GET method, built a WAR file using Maven, deployed it to a Docker container using the Tomcat image, and set the service port to 8080.


**file structure:**
```

```


## Container3:

### Prompt:

```
Create app.py can run web server service and have one function that can read json file as api call with get method.
This function also can solve CORS issue. Also giva me the requirements.txt content.
After that, deploy to Docker containe and show the file structure.
```

### 產出:
Sure! Here's what I found:

**Python app.py** file:
```python
import json
from flask import Flask, request
app = Flask(__name__)

@app.route('/api/v1/read', methods=['GET'])
def read_json():
    with open('data.json') as f:
        data = json.load(f)
    return data

if __name__ == '__main__':
    app.run()
```

Here's the **requirements.txt** file:
```
flask==2.0.3
json==2.0.9
```

To deploy this application to Docker container, you can follow these steps:
Create a **Dockerfile** with the following contents:
```Dockerfile
FROM python:3.8-slim
WORKDIR /app
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
CMD [ "python", "./app.py" ]
```

**Build** the Docker image:

```
docker build -t my-app:latest .
Run the Docker container:
docker run -d -p 5000:5000 my-app
```

Test the application by making an API call:
```
curl http://localhost:5000/api/v1/read
```
This should return the contents of the data.json file.

Here's the **file structure** of your project:
```
.
├── Dockerfile
├── README.md
├── app.py
├── data.json
└── requirements.txt
```

### 後來修改：

**Requirements.txt** file:
```
Flask==2.0.3
requests==2.27.1
simplejson==3.17.6
Werkzeug==2.0.3
```

**Python app.py** file:
```python
return jsonify(data)  # Convert dictionary to JSON response
```

**file structure:**
```
.
├── Dockerfile
├── css
│   └── style.css
├── index.html
└── js
    └── act.js
```



