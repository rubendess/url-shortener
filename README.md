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
API
-------------

-------------
> Generate short URL
-------------

> - POST /api/v1/short-urls

JSON body:


    {
        "url": "https://www.github.com/rubendess"
    }


Result: String with the hash URL to access.

-------------
> Retrieving original URL (auto redirect to the origin URL)
-------------

> - GET /api/v1/short-urls/{hashURL}
