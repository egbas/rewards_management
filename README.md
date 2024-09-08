# rewards_management

#### Program setup
To set up and run the project kindly clone the project on intelliJ or any IDE that can run a spring boot project.
Also set up the environment variables of username and password as postgres database was used for the set up.
Swagger API documentation was also used so testing the project has been made relatively easy.

#### API Endpoints
###### Customer Controller
The first controller has the Auth endpoint of Register and login which was done to ensure security of endpoints using JWT

###### Register Endpoint
{
email	string
totalCashBack	number
currentBalance	number
password	string
}
Sample of request body... The total cashback and current balance are both initialized to Zero.

###### Login Endpoint
{
email	string
password	string
}
Sample of Login request body

###### Rewards Controller
This controller has four endpoints which describes the operation of a cashback reward system.

###### Transaction Endpoint
For a cashback to be gotten a transaction must take place and this is the reason for this endpoint
{
  "transactionDate": "2024-09-08",
  "transactionAmount": 9000,
  "description": "vista"
} request body
The earned amount is missing here because it is decided by an algorithm written in the code which gives 20% of amount below 10000 and 10% on amounts above 10000
{
  "message": "Transaction successful",
  "status": "success",
  "statusCode": "CREATED"
} response body

###### History Endpoint
this endpoint takes only a customer ID
[
  {
    "transactionID": 1,
    "transactionDate": "2024-09-08",
    "amountEarned": 2000,
    "description": "trial"
  },
  {
    "transactionID": 2,
    "transactionDate": "2024-09-08",
    "amountEarned": 1800,
    "description": "vista"
  }
] It returns a list of all transactions and the amount earned from them

###### Balance Endpoint
This endpoint takes a customer Id as a path variable. It is needed to view the balance of total cashback and current balance at any time.
{
  "status": "success",
  "data": {
    "customerID": 1,
    "totalCashBack": 3800,
    "currentBalance": 1800
  },
  "statusCode": "OK"
} Response body

###### Withdrawal Endpoint
Takes in a customer ID and withdrawal amount. This endpoint is necessary to be able to withdraw accrued cashback dividends else the current balance and total cashback would always be the same.

{
  "message": "Withdrawal successful",
  "status": "success",
  "data": {
    "customerID": 1,
    "totalCashBack": 3800,
    "currentBalance": 1800
  },
  "statusCode": "OK"
}Response body
