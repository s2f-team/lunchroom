## Lunchroom
TopJava internship graduation project.<br>
It's a REST API using Hibernate/Spring/SpringMVC that implements a voting system for deciding where to have lunch.
<br>
<br>
Api Documentation<br>
http://localhost:8080/lunchroom/swagger-ui.html
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
<br>

### Admin (menus)

_Delete a menu with dishes:_
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/admin/restaurants/menu/100008' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get Menu with dishes by restaurantId:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/100006/menu' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get all Menu with dishes and restaurants by date:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/restaurants/menu/by?date=2021-01-14' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>
<br>

### Admin (users)

_Enable/disable user:_
<pre>
curl --location --request PATCH 'http://localhost:8080/lunchroom/rest/admin/users/100000?enabled=false' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Delete user:_
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/admin/users/100000' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>

_Get all users:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/admin/users' \
--header 'Authorization: Basic M0BtYWlsLnJ1OnBhc3N3b3Jk'
</pre>
<br>

### User (profile)

_Register new user:_
<pre>
curl --location --request POST 'http://localhost:8080/lunchroom/rest/profile/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Test",
    "email": "test@mail.ru",
    "password": "test1"
}'
</pre>

_Get profile:_
<pre>
curl --location --request GET 'http://localhost:8080/lunchroom/rest/profile' \
--header 'Authorization: Basic dGVzdEBtYWlsLnJ1OnRlc3Qx'
</pre>

_Delete profile:_
<pre>
curl --location --request DELETE 'http://localhost:8080/lunchroom/rest/profile' \
--header 'Authorization: Basic MUBtYWlsLnJ1OnBhc3N3b3Jk'
</pre>