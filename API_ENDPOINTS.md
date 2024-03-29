# Documentation of the API endpoints

## Status codes

- 200 OK = Operation success
- 201 CREATED = Resource created successfully
- 401 Unauthorized = User not authenticated, and the operation requires authentication
- 403 Forbidden = User is authenticated but lacks the permision to perform that operation

## Permited Operations
- Admin
  1. GET/POST/PUT/DELETE Quote
  2. GET/POST/PUT/DELETE Topic
  3. POST USER (Register)
  4. GET/POST/PUT Image (Put with no image is delete)


## Resources

| Resource        | URL           | Operations  |
| --------------- |---------------| ------------|
| Quote           | https://localhost:8443/api/quotes/?page=pageNumber | GET |
| Quote           | https://localhost:8443/api/quotes/id      |   GET/PUT/DELETE |
| Quote           | https://localhost:8443/api/quotes/ |    POST |
| Topic           | https://localhost:8443/api/topics/?page=pageNumber | GET |
| Topic           | https://localhost:8443/api/topics/id      |   GET/PUT/DELETE |
| Topic           | https://localhost:8443/api/topics/ |    POST |
| Topic           | https://localhost:8443/api/topics/pdf/id | GET |
| Image           | https://localhost:8443/api/images/quoteId      |   POST/GET |
| Graph           | https://localhost:8443/api/graph | GET |
| Login           | https://localhost:8443/api/login/     |   GET/POST|
| Logout          | https://localhost:8443/api/logout/ |    GET/POST |

## JSON EXAMPLES

### Resource

`https://localhost:8443/api/quotes/`

### GET

- URL

`https://localhost:8443/api/quotes?page=0`

- Body
 Empty for the get request

### Result


 `"content": [
 
        {
	    "id": 1,	    
            "text": "Did I ever tell you the definition of insanity?..",	    
            "author": "Vaas Montenegro",	    
            "book": "Far Cry 3",
            "imageId": null	    
        },
	
        {	
            "id": 2,	    
            "text": "Hola, me llamo Íñigo Montoya, tu mataste a mi padre, preparate a morir",    
            "author": "Íñigo Montoya",	    
            "book": "The Princess Bride",
            "imageId": null    
        },
	
        {	
            "id": 3,    
            "text": "Que va si no estaba durmiendo, solo estaba mirando pa´dentro",	    
            "author": "Tragabuche",	    
            "book": "Bandolero",	    
            "imageId": null	    
        },
	
        {	
            "id": 4,    
            "text": "Self discipline is self love",
            "author": "Will Smith",
            "book": "Video",
            "imageId": null
        },
        {
            "id": 5,
            "text": "Get busy living or get busy dying",
            "author": "Stephen King",
            "book": "The Shawshank Redemption",
            "imageId": null
        },
        {
            "id": 6,
            "text": "Experience is merely the name men gave to their mistakes",
            "author": "Oscar Wilde",
            "book": "The Picture of Dorian Gray",
            "imageId": null
        },
        {
            "id": 7,
            "text": "All men dream: but not equally. Those who dream by night in the dusty recesses of their minds wake in the day to find that it was vanity: but the dreamers of the day are dangerous men, for they may act their dreams with open eyes, to make it possible. This I did",
            "author": "Lawrence of Arabia",
            "book": "Seven Pilars of Wisdom",
            "imageId": null
        },
        {
            "id": 8,
            "text": "Allé voy",
            "author": "Cloud",
            "book": "Final Fantasy VII",
            "imageId": null
        },
	
        {
            "id": 9,    
            "text": "Did I ever tell you the definition of insanity?..",    
            "author": "Vaas Montenegro",    
            "book": "Far Cry 3",    
            "imageId": null    
        },
	
        {
            "id": 10,    
            "text": "Did I ever tell you the definition of insanity?..",    
            "author": "Vaas Montenegro",    
            "book": "Far Cry 3",    
            "imageId": null
	    
        }
	
    ],
    
    "pageable": {
    
        "sort": {
	    "sorted`
            
### POST

- URL

`https://localhost:8443/api/quotes`
- Body

`{

    "text": "Did I ever tell you the definition of insanity?..",
	"author": "Vaas Montenegro",
    "book": "Far Cry 3"
}`

### Result

`{

    "id": 21,
    "text": "Did I ever tell you the definition of insanity?..",
    "author": "Vaas Montenegro",
    "book": "Far Cry 3",
    "imageId": null
}`

### Delete

- URL

`https://localhost:8443/api/quotes/21`
- Body
Empty For delete request

### Result

`{

    "id": 21,
    "text": "Did I ever tell you the definition of insanity?..",
    "author": "Vaas Montenegro",
    "book": "Far Cry 3",
    "imageId": null
}`

### Resource

`https://localhost:8443/api/topics/`

### GET

- URL

`https://localhost:8443/api/topics?page=0`

- Body
 Empty for the get request

### Result


 `{
 
    "content": [  
   
        {
            "id": 15,
            "name": "Test",
            "nQuotes": 0,
            "quoteIds": [],
            "texts": []
        },
        {
            "id": 16,
            "name": "Test2",
            "nQuotes": 3,
            "quoteIds": [
                2,
                3,
                4
            ],
            "texts": [
                "Esto es un texto1",
                "Esto es un texto2",
                "Esto es un texto3",
                "Esto es un texto4"
            ]
        },
        {
            "id": 17,
            "name": "Test3",
            "nQuotes": 5,
            "quoteIds": [
                2,
                3,
                4,
                5,
                6
            ],
            "texts": [
                "Esto es un texto1",
                "Esto es un texto2",
                "Esto es un texto3",
                "Esto es un texto4",
                "Esto es un texto5",
                "Esto es un texto6"
            ]
        },
        {
            "id": 18,
            "name": "Test4",
            "nQuotes": 2,
            "quoteIds": [
                5,
                6
            ],
            "texts": [
                "Esto es un texto2",
                "Esto es un texto3"
            ]
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "totalPages": 1,
    "totalElements": 4,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 4,
    "first": true,
    "empty": false
    
}`
            
### POST

- URL

`https://localhost:8443/api/topics`
- Body

`{

            "name": "Test API",
            "quoteIds": [
                2,
                3,
                4
            ],
            "texts": [
                "Esto es un texto1",
                "Esto es un texto2",
                "Esto es un texto3",
                "Esto es un texto4"
            ]

}`

### Result

`{
            "id": 27,
            "name": "Test API",
            "nQuotes": 3,
            "quoteIds": [
                2,
                3,
                4
            ],
            "texts": [
                "Esto es un texto1",
                "Esto es un texto2",
                "Esto es un texto3",
                "Esto es un texto4"
            ]
        },`

### Delete

- URL

`https://localhost:8443/api/topics/16`
- Body
Empty For delete request

### Result

`{

    "id": 16,
    "name": "Test2",
    "nQuotes": 3,
    "quoteIds": [
        2,
        3,
        4
    ],
    "texts": [
        "Esto es un texto1",
        "Esto es un texto2",
        "Esto es un texto3",
        "Esto es un texto4"
    ]
    
}`







