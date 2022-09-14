# my-financial-fortress-frontend
Frontend of an application for securing the financial future. It is based on knowledge from Marcin Iwuć's book "Financial Fortress", where the author presented a model portfolio to ensure a secure future for long-term investments such as retirement. The application suggests how to allocate capital to individual financial instruments along with their valuation history. The frontend shows the percentage as well as financial distribution of our portfolio along with deviations from the model portfolio. It makes it easy to check the valuation history of the entire portfolio, as well as its individual components.

The application is currently in a version where, unfortunately, it does not allow automatic portfolio creation/deletion and updating of stock market values. I will work on it! Currently you need to hit the endpoints given below:
localhost:8080/v1/portfolio/{investmentCapital} POST-to create portfolio
localhost:8080/v1/portfolio DELETE- to delete portfolio
localhost:8080/v1/bonds/quoted/value POST
localhost:8080/v1/gold/value POST
localhost:8080/v1/inflation/value POST
localhost:8080/v1/emerging/value POST
localhost:8080/v1/emerging/value POST
localhost:8080/v1/developed/value POST - to update all market values

The main frontend page, from where you need to start the adventure with the application, is at Route("").

Due to the fact that the full functionality, any charts, changes are visible only after some time, I provide my data from the database.

