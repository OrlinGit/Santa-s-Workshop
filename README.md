A small app to help Santa streamline his production capacity.

<<<<<<< Updated upstream
In order to srat the app you will ned some set up in advance :) 
Teh application works with PostgreSQL Database and is set to communicate with one.
In order to do so you need to have a PostgreSQL installed on your computer.
Second yuo need to start the pgAdmin and create in one of your servers database that is named: "santa_workshop"
After that you need to go to the application properties of the database and file in the username and password of your server.
Please change ${DB_USERNAME} with your username and ${DB_PASSWORD} with your password in order the DB to work.
If you have already set up your OS variables to work your credentials please ignore this step :) 
I have tried to to it in this way in order to avoid leaking credentials in public app (and because I found the settings while checking ho to set my database :) )
Also I have set the server port to 8081 because port 8080 on my machine is always busy with something and I do not know with what :)
