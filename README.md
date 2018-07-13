# Android-Ser
Version 1.0 13/07/2018

## GENERAL USAGE NOTES
--------------------
Supports android 4.0.3 and later

## DESCRIPTION
------------
This application allows you to receive anonymous assessments of your service or product from customers.

## INSTRUCTIONS FOR USE
---------------------
1. Authorize with your Yaware service account.
2. Enter the code of your point.
3. 
	a) To start the program, click start. Сyclic poll will 	start. To return to your homepage, exit the program  	and open it again. Return to step 1.
	b) To create new question in this point, click create. 
	Enter new question and click create.
	c) To delete some question in this point, click delete.
	Enter number of question in field.
	d) To edit some point, click edit. Enter number of 	question in first field nad new question in second field. 	Click save.

## INFORMATION FOR DEVELOPERS
---------------------------
You need to synchronize the database of the program with your database. The program has a local database:
Tables:
	users
--------------------------
user_id | mail | password|
--------------------------
	points
---------------------------
point_id | user_id | code |
---------------------------
	questions
----------------------------------
question_id | question | point_id|
----------------------------------
	rating
------------------------------
score_id | value | questionid|
------------------------------

## Contact
--------
email: trainee1@yaware.com








