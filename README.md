# AggregatebusinesssolutiontaskbackendApplication web application (NUBAN GENERATOR)

# The application was developed using java(JDK 20), spring boot, mysql database and maven.

# Mysql properties configuration i.e url, username and password must be provided in hte application.properties file for the application to run and serve its purpose correctly.

# End-point

# generateAccountNumber
*This end-point creates a new bank account number object with the information provided by the app user
and saves the object in a database. It accepts bank code, serial number, then returns a message.*

# Request
* Url: `https://localhost:8080/api/v1/bank-account-number/generate-account-number`
* Method: POST
* Header :
    * `Content-Type : application/json`
* Body:
```
{
    "bankCode" : "014",
    "serialNumber" : "123456789"
}
```
* Fields :
    * `bankCode`(required,String): *The bank code of the bank generating NUBAN, the bank code must be exactly three characters*
    * `serialNumber`(required,String): *The serial number of the NUBAN to be generated, the serial number must be exactly nine characters*
   
# Response 1
*Successful request*
* Status code : `201 ok`
* Body:
```
{
    "data": {
        "bankCode": "014",
        "serialNumber": "123456789",
        "generatedAccountNumber": "1334567899"
    },
    "timeStamp": 1684597590.601610900,
    "statusCode": 201,
    "httpStatus": "CREATED",
    "successful": true
}
```
* Field:
    * `message`: *Request message*

# Response 2
*Unsuccessful request due to sending a request with the same bank code and serial number that has been used to create an account number*
* Status code : `400 bad request`
* Body:
```
{
    "data": "serial number 123456789 already exist in bank with bank code: 014",
    "timeStamp": 1684653658.691616900,
    "statusCode": 400,
    "httpStatus": "BAD_REQUEST",
    "successful": false
}
```
* Field:
  * `message`: *Request message*

# Response 2
*Unsuccessful request due to sending a request with bank code that is not exactly three characters*
* Status code : `400 bad request`
* Body:
```
{
    "data": "Bank code must be exactly three characters",
    "timeStamp": 1684653862.870396300,
    "statusCode": 400,
    "httpStatus": "BAD_REQUEST",
    "successful": false
}
```
* Field:
  * `message`: *Request message*

# Response 2
*Unsuccessful request due to sending a request with serial number that is not exactly nine characters*
* Status code : `400 bad request`
* Body:
```
{
    "data": "Serial numbermust be exactly nine characters",
    "timeStamp": 1684653931.342034400,
    "statusCode": 400,
    "httpStatus": "BAD_REQUEST",
    "successful": false
}

