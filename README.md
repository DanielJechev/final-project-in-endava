# final-project-in-endava

DANIEL ZHECHEV

Create a Spring Boot application for processing Supermarket purchases.

We need to create an Application that handles Supermarket purchases based on Spring Boot. The Application will have endpoints and entities as specified below.
 All persisted in the database Entities’ IDs should be generated using UUID.
All bonus requirements will be marked with (bonus requirement) and can be found after the REST Controllers section.


REST Controllers
Item Controller: 
POST Method createItem: 
request parameters:
-	name: String (not blank)
-	price: Double (not null)
-	type: Enumerable (FOOD, DRINKS, TECHNOLOGY, HOUSEHOLD) , cannot be null 
response:
-	Response status “201 CREATED” and the created Item.


Supermarket Controller:
POST Method createSupermarket:
request parameters:
-	name: String (unique, not blank)
-	address: String (not blank)
-	phoneNumber: String
-	workHours: String
response:
-	Response status “201 CREATED” and the created Supermarket.
POST Method addItemsToSupermarket:
request parameters:
-	supermarketId: String (not null)
-	itemIds: List<String> (not empty)
response:
-	Response status “200 OK” and the Supermarket ID with added Item’s names.
GET Method getSupermarketById:
path variables:
-	supermarketId: String (required)
response:
-	supermarket fields (name, address, phoneNuber, workHours)
-	list of items, where each item has (id, name, price, type)


Purchase Controller:
POST Method buyItemsFromSupermarket: 
request parameters:
-	superMarketId: String (not blank)
-	itemIds: List<String> (not empty)
-	paymentType: Enumerable (CASH, CARD), cannot be null
-	cashAmount: Double (nullable, required only if paymentType is CASH)
response:	
-	Response status “201 CREATED” and the created Purchase.
-	The created Purchase should store the total price (calculated from each Item’s specified price), returned change (if any) and time of the executed payment. 
GET Method getAll:
response:
-	List of purchases with the relevant fields 
 
Extra Validations (bonus requirement)

Supermarket:
-	name: max length 64
-	address: max length 128, format – {city}, {street} {street number}
-	phoneNumber: should match 087xxxxxxx, 088xxxxxxx, 089xxxxxxx
-	workHours: 24-hour format, should match {HH:mm}-{HH:mm}

Item:
-	name: max length 64
-	price: min: 0.01, max: 9999,99
-	type: throw custom exception when value is not present in the Enum

Purchase:
-	paymentType: throw custom exception when value is not present in the Enum

Exception handling (bonus requirement)
Exception handling should be done via global ControllerAdvice. There should be explanatory messages and a way to collect all validation errors and return them as a response.

