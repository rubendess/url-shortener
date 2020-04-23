# URL Shortener

Most of us are familiar with seeing URLs like bit.ly or t.co on our Twitter or Facebook feeds. These are examples of shortened URLs, which are a short alias or pointer to a longer page link.


-------------
Technologies
-------------
> - Java 8
> - Spring Boot
> - JPA
> - Gradle
> - Junit
> - Docker


-------------
Variables
-------------
> - server.port
> - spring.jpa.hibernate.ddl-auto
> - spring.datasource.url
> - spring.datasource.username
> - spring.datasource.password
> - url-shortener.host-redirect


-------------
Running the app using docker and docker-compose
-------------
1) Clone this repository: `git clone git@github.com:rubendess/url-shortener.git`
2) Go to the project directory: `cd url-shortener`
3) Execute the following command: `docker-compose up mysql app` or `docker-compose up`

It will start the app on `localhost:8080`


-------------
API
-------------

#### Generate short URL

> - POST /api/v1/short-urls

JSON body:


    {
        "url": "https://www.github.com/rubendess"
    }


Result: String with the hash URL to access.
<br>

#### Retrieving original URL (auto redirect to the original URL)

> - GET /api/v1/short-urls/{hashURL}
