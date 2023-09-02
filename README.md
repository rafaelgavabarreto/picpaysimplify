# PicPay Backend Challenge

### Requirements

We have 2 types of users, common users and merchants, both have wallets with money and transfer money between them. Let's pay attention only to the transfer flow between two users.

For both types of user, we need the Full Name, email and Password. E-mails must be unique in the system. Therefore, your system should allow only one registration with the same email address.

Users can send money (make a transfer) to merchants and between users.

Shopkeepers only receive transfers, they do not send money to anyone.

Validate that the user has a balance before the transfer.

Before finalizing the transfer, you must consult an external authorizing service, use this mock to simulate (https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6).

The transfer operation must be a transaction (i.e. reversed in any case of inconsistency) and the money must be returned to the sending user's wallet.

Upon receipt of payment, the user or merchant needs to receive notification (email, sms) sent by a third-party service and eventually this service may be unavailable/unstable. Use this mockup to simulate sending (http://o4d9z.mocklab.io/notify).

This service must be RESTFul.

Create a transaction.
Post: /transactions
```
{
    "receiverId": String,
    "senderId": String,
    "amount": BigDecimal
}
```
Create a user.
Post: /users
```
{
    "firstName": String,
    "lastName": String,
    "email": String,
    "userType": String (userType), ("COMMON", "MERCHANT")
    "password": String,
    "balance": BigDecimal
}
```

Get all users. Get: /users

Get all transactions. Get: /transactions

Get all transactions by user or merchant. Get: /transactions/:id