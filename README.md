
# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Create a Todo App Lab

## Opening 

We're going to create a todoApp during this Spring week. You'll continue to add functionality to your app as we learn more about Spring and its modules. 

This will be a fairly open-ended lab. We want you to be able to think through the design decisions and understand the reason why you choose one thing over the other. There's just one main ground rule to follow: Make sure you only work with the technologies/tools we covered during the lesson.

## Deliverables

Collaborate and push your changes to a GitHub repo.

You will maintain a `README` that will include the following:

- A link to the GitHub repo.
- All of your design decisions. 
- The reasons behind each decision.
- What went right.
- Challenges you faced.
- Which part you enjoyed working on the most.

At the end of this lab, after testing is done, we will hear a **five-minute presentation** from each team. Each team will go through its `README`, talking through each point. Be prepared to answer some questions. 

----

## Exercise

We'll build this back-end app incrementally.

### Step 1 (Spring Boot)

Today, you'll just set up your app using Spring Boot CLI, as we did in the lesson earlier. 

- Use Maven to download and build all of the dependencies.
- Create a REST controller.
- Create a `/hello` endpoint that returns a `'Hello World!'` string.
- Use Postman to test the API.

### Step 2 (Spring Profile)

In the next step, you'll use Spring Profile to create a development-specific environment in your app. This is where all of your environment-specific configuration will go.

### Step 3 (Spring Data)

Create `Category` and `Item` model. You'll use PostgreSQL as your database and Spring Data to talk to your database.

This step will be done in two parts. 

#### 3(a)

In this step, you'll only create a `Category` model. 

- The `Category` should have at least two fields: `name` and `description`.
-  You'll create full CRUD endpoints for `Category` controller.

#### 3(b)

In the next step, you'll add the `Item` model and map it with `Category`.

- The `Item` table should have at least three columns: `name` and `description`, and `dueDate`.
- The `Category` and `Item` tables should be mapped to each other.
- You'll create full CRUD endpoints for `Item` controller.

#### 3(c)
In the next step, you'll add the `User` model and map it with `UserProfile`.
- The `User` table should have at least three columns: `userName` and `emailAddress`, and`password`.
- The `UserProfile` table should have at least three columns: `firstName` and `lastName`, and`profileDescription`.
- The `User` and `UserProfile` tables should be mapped to each other (1:1).
-  Under the UserController, you'll create register endpont and block all the other URLs

### Step 4 (Spring Security)

You'll now add user authentication and authorization to your existing app, using JSON Web Tokens (JWT) to authenticate your requests. You already have a `User` model for security purposes.
- Again, make sure to use only JWT for authentication.
- Only login API endpoint will only return a JWT in response.
- All other requests will use that token for authentication. 
