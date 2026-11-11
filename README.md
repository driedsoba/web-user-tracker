# web-user-tracker

A Java web application for a small online game shop, built with Servlets, JSP and JDBC on MySQL.

## Features

- User registration and login, with an authentication filter protecting pages
- Admin view to list, add, update and delete users
- Product listing and a shopping cart

## Stack

- Java Servlets and JSP (JSTL)
- MySQL via JDBC, using a Tomcat connection pool (`WebContent/META-INF/context.xml`)
- Eclipse Dynamic Web Project layout

## Running

1. Import the project into Eclipse as an existing Dynamic Web Project.
2. Create a MySQL database named `web_gameshop` and update the credentials in `WebContent/META-INF/context.xml`.
3. Deploy to an Apache Tomcat server from Eclipse and open `login.jsp`.

## Layout

| Path | Purpose |
| --- | --- |
| `src/com/eshop/web/jdbc` | Servlets, models and database utilities |
| `WebContent` | JSP pages, styles and images |
| `WebContent/WEB-INF/lib` | JSTL and MySQL connector libraries |
