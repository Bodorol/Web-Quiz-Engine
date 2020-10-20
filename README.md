# Web-Quiz-Engine
## About:
## How to run:
1. Build the project by running ```gradlew build```.
2. Run the generated .jar file.
## How to use:
### To register an account:
Send a POST request to localhost:8080/api/register with JSON in the body of the request in the following format:
```
{
  "email": "Your email",
  "password": "Your password"
}
```
Your email must be a valid email address\
Your password must be at least 5 characters long.
### To see all quizzes:
Send a GET request to localhost:8080/api/quizzes to see a paginated list of all quizzes.\
#### Optional parameters:
page: sets the page number.\
pageSize: sets the number of quizzes per page.\
sortBy: sorts the quiz by the given parameter (id, title, creator)\
### To see a specific quiz:
Send a GET request to localhost:8080/api/quizzes/{id}, where {id} is the id of the quiz you want to see.
### To see quizzes you have completed:
Send a GET request to localhost:8080/api/quizzes/completed.
### To submit a quiz:
Send a POST request to localhost:8080/api/quizzes with JSON in the body of the request in the following format:
```
{
  "title": "Quiz Title",
  "text": "Quiz Text",
  "options": ["option 1", "option 2", "option 3", "option 4", ...],
  "answer": [correct answer index 1, correct answer index 2, ...]
}
```
The correct answer indices must be numbers.
### To answer a quiz:
Send a POST request to localhost:8080/api/quizzes/{id}/solve, where {id} is the id of the quiz you want to solve.
Include your answer as JSON in the body of the request in the following format:
```
{
    "answer": [answer index 1, answer index 2, ...]
}
```
The answer indices must be numbers.\
If a quiz has multiple correct answers, you must have all of them in your list of answers in order to correctly solve the quiz.
### To delete a quiz you created:
Send a DELETE request to localhost:8080/api/quizzes/{id} where {id} is the id of the quiz you want to delete.\
You cannot delete a quiz created by another user.
