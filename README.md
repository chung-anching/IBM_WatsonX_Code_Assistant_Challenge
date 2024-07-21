

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


## Container11:
```

```

## my-final:
```

```


## Container3:

### Prompt:

```
Create app.py can run web server service and have one function that can read json file as api call with get method. THis function also can solve CORS issue. Also giva me the requirements.txt content. After that, deploy to Docker containe and show the file structure.
```

### 產出:
Sure! Here's what I found:

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



