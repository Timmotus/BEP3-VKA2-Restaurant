PORTS:
Delivery: 8080
Inventory: 8081
Kitchen: 8082
Menu: 8083
Order: 8084
Payment: 8085

# BEP3-VKA2-Restaurant

Inventory Service

Get all Items Request:
GET -> /inventory

Get a single Item by ID Request:
GET -> /inventory/{id}

Get a list of Items by ProductName Request:
GET -> /inventory?productName={name}

Register a new Item Request:
POST -> /inventory
```json
{
  "productName": "Salami Slice",
  "unit": "GRAMS",
  "capacity": 3000,
  "purchaseCapacity": 1000,
  "sellCapacity": 25,
  "purchasePrice": 1.50,
  "sellPrice": 10
}
```

Update an existing Item Request:
PUT -> /inventory/{id}
```json
{
  "productName": "Salami Slice",
  "stock": 10,
  "unit": "GRAMS",
  "capacity": 3000,
  "purchaseCapacity": 1000,
  "sellCapacity": 25,
  "purchasePrice": 1.50,
  "sellPrice": 10
}
```

Buy stock for an Item by ID Request:
POST -> /inventory/{id}/stock

Delete an Item by ID Request:
DELETE -> /inventory/{id}

- Sell an Item