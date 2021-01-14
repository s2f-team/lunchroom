## Lunchroom
TopJava internship graduation project.</br>
It's a REST API using Hibernate/Spring/SpringMVC that implements a voting system for deciding where to have lunch.
<br>
<br>

### Common:

_Get All Menus which includes the Restaurant and today's meals:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/restaurants/menu' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_To vote:_
<pre>
curl --location --request POST 'http://localhost:8080/lunchroom/rest/restaurants/votes' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
"restaurantId": 100003,
"menuId": 100008
}'
</pre>

_Delete the vote:_
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/restaurants/votes/100024' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--data-raw ''
</pre>
<br>

### Admin (restaurants)

_Create new restaurant:_
<pre>
curl --location --request POST 'http://localhost:8080/lunchroom/rest/admin/restaurants' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "New",
    "phone": "+7(000)111-22-33",
    "address": "Moscow, Savelki, 15",
    "website": "www.new.ru"
}'
</pre>

_Update restaurant by id:_
<pre>
curl --location --request PUT 'http://localhost:8080/lunchroom/rest/admin/restaurants/100025' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 100025,
    "name": "Hello",
    "phone": "+7(000)111-22-33",
    "address": "Moscow, Savelki, 15",
    "website": "www.new.ru"
}'
</pre>

_Delete restaurant by id:_
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/admin/restaurants/100025' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get restaurant by id:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/100003' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get all restaurants:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get restaurants where menu exists for today (without a menu):_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/menu' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get restaurantId and votes count:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/votes' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>
<br>

### Admin (dishes)

_Create a new dish (no menuId):_
<pre>
curl --location --request POST 'http://localhost:8080/lunchroom/rest/admin/restaurants/100007/menu/0/dishes' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Pepper",
    "price":150
}'
</pre>

_Create a new dish (with menuId):_
<pre>
curl --location --request POST 'http://localhost:8080/lunchroom/rest/admin/restaurants/100007/menu/100026/dishes' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Milk",
    "price":10
}'
</pre>

_Update dish by id:_
<pre>
curl --location --request PUT 'http://localhost:8080/lunchroom/rest/admin/restaurants/100007/menu/100026/dishes/100027' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Chocolate milk",
    "price":200
}'
</pre>

_Delete dish by id:_
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/admin/restaurants/100007/menu/100026/dishes/100027' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get dish by id and menuId:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/100003/menu/100008/dishes/100012' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get all dishes by menuId:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/100003/menu/100008/dishes' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>