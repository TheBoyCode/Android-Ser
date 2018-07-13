# Yaware Service for Android
Version 1.0 13/07/2018

## GENERAL USAGE NOTES
--------------------
Supports android 4.0.3 and later
#### DESCRIPTION
This application allows you to receive anonymous assessments of your service or product from customers.

## INSTRUCTIONS FOR USE
---------------------
1. Authorize with your [Yaware service](https://service.yaware.com.ua/) account.
2. Enter the code of your point.
3. 
	a) To start the program, click start. Сyclic poll will 	start. To return to your homepage, exit the program  	and open it 		again. Return to step 1.
	
	b) To create new question in this point, click create. 
	Enter new question and click create.
	
	c) To delete some question in this point, click delete.
	Enter number of question in field.
	
	d) To edit some point, click edit. Enter number of 	question in first field nad new question in second field. 	Click save.

## INFORMATION FOR DEVELOPERS
---------------------------
You need to synchronize the database of the program with your database. The program has a local database "YService" :


#### Tables: 
##### users (user_id text| mail text| password text)
##### points (point_id text| user_id text| code text)
##### questions (question_id text| question text| point_id text)
##### rating (score_id text| value text| questionid text)

The program contains methods for testing the program(You can delete them):
- Autocomplete tables
- Update tables
- Update tables to remove previous information

## Contact
--------
email: trainee1@yaware.com








