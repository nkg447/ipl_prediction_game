# Request and Response formats

## Endpoint -
```
endpoint - "https://ipl-prediction-game.herokuapp.com/"
* add sub address written in brackets of APIs to the above endpoint to acess that API.
```

### Register ("/register")

#### Request - 
```
{
  "email": "email",
  "name": "name",
  "password": "password"
}
```
#### Response - 
```
{
    "status": "SUCCESS",
    "data": {
        "email": "nngupta@apostek.com",
        "registered": true
    },
    "error": { ... }
}
```

### Login ("/login")

#### Request - 
```
{
  "email": "email",
  "password": "password"
}
```
#### Response - 
```
{
    "status": "SUCCESS",
    "data": {
        "authenticated": true
    },
    "error": { ... }
}
```

### Questions ("/questions")

#### Request - 
```
N/A
```
#### Response - 
```
{
    "status": "SUCCESS",
    "data": {
        "questions": [
            {
                "id": 0,
                "question": "Question?",
                "options": [
                    "abc",
                    "xyz",
                    .
                    .
                    .
                ]
            },
            .
            .
            .
        ]
    },
    "error": { ... }
}
```

### Prediction ("/prediction")

#### Request - 
```
[
	{
		"id": 1,
		"answer": "Bhuvneshwar Kumar"
	},
	{
		"id": 2,
		"answer": "Imran Tahir"
	},
	{
		"id": 3,
		"answer": "Sam Billings"
	}
]
```
#### Response - 
```
{
    "status": "SUCCESS",
    "data": {
        "success": true
    },
    "error": { ... }
}
```

### Leaderboard ("/leaderboard")

#### Request - 
```
N/A
```
#### Response - 
```
{
    "status": "SUCCESS",
    "data": {
        "leaderboard": [
            {
                "name": "admin",
                "score": 0
            },
            .
            .
            .
            
        ]
    },
    "error": { ... }
}
```