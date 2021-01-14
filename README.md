## Lunchroom
TopJava internship graduation project.
It's a REST API using Hibernate/Spring/SpringMVC that implements a voting system for deciding where to have lunch.

###### Common:

###### Get All Menus which includes the Restaurant and today's meals:
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/restaurants/menu' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

###### To vote:
<pre>
curl --location --request POST 'http://localhost:8080/lunchroom/rest/restaurants/votes' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
"restaurantId": 100003,
"menuId": 100008
}'
</pre>

###### Delete vote:
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/restaurants/votes/100024' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--data-raw ''
</pre>