# roomie backend

[![CircleCI](https://circleci.com/gh/heroku/java-getting-started.svg?style=svg)](https://circleci.com/gh/heroku/java-getting-started)

A barebones Java app, which can easily be deployed to Heroku.

This application supports the [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) article - check it out.

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/heroku/java-getting-started.git
$ cd java-getting-started
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
DATABASE_URL=postgres://localhost:5432/java_database_name
```

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)


## Requests

#### Booking

Sample request:

echo $(curl -L -w "%{http_code} %{url_effective}\\n" -H "Content-Type: application/json" --data '{"roomId": "E2.04", "day": "1", "month": "1", "year": "2017", "fromTime": "1300", "toTime": "1400", "userId": "hbabii", "title": "JavaSeminar"}' http://roomie-server.herokuapp.com/api/room/book)

#### Cancelling

Sample request:

echo $(curl -L -w "%{http_code} %{url_effective}\\n" -X DELETE -H "Content-Type: application/json" --data '1' http://roomie-server.herokuapp.com/api/room/cancel)


#### User Registration http://roomie-server.herokuapp.com/api/user/register METHOD PUT

Sample request:
echo $(curl -L -w "%{http_code} %{url_effective}\\n" -X DELETE -H "Content-Type: application/json" --data '{"email": "john@unibz.it", "password": "mypass", "first": "john", "last": "doe", "phone": "39600202020", "faculty": "Computer Science", "picture": "http://dev.testwpinstall.com.php56-12.phx1-2.websitetestlink.com/wp-content/uploads/2016/02/osmosis-team-01-560x560-150x150.jpg"}' http://roomie-server.herokuapp.com/api/room/book)