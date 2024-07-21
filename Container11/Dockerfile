# Finally, I generated a Dockerfile to package the HTML, CSS, and JavaScript files into a Docker image. The Dockerfile installs Nginx and copies the files into the correct directories. The Dockerfile also sets the default command to start Nginx.

FROM nginx:latest
COPY ./index.html /usr/share/nginx/html
COPY ./css /usr/share/nginx/html/css
COPY ./js /usr/share/nginx/html/js
CMD ["nginx", "-g", "daemon off;"]

# I built the Docker image using the following command:
# docker build -t watsonx/track3-code-assistant:latest .
# And started the container using the following command:
# docker run -d -p 8080:80 watsonx/track3-code-assistant:latest


