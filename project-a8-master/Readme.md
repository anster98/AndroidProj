ITERATION 1:

Anthony Caliri & An Nguyen Report
---------------------------------
Most of the issues we had in the first iteration were centered around placing waypoints on our map. Originally the plan was to place markers on the map with the colors being Green, Yellow, Orange, Red, Gray representing Not Busy, Average, Busy, Very Busy, and Closed. We were not able to get the methods completed to figure out if a location is open or not. 

Also, to place markers on the map, the method requires a LatLng object which is made from a coordinate point. To get the coordinates from a street address we need to use a library that provides the Geocoding functionality. 

- The Google Maps API has this functionality, but we could not get it to work correctly. Sufficient documentation/examples where not available to do this in Android

- OpenStreetMap, an open source mapping service, also has this functionality but requires a network connection. This should not be a problem, but it was still one we decided not to push.

- ArchGIS has the functionality to do Geocoding offline, but we have not explored this route beyond finding the documentation.   

Sabrina Kundu Report
---------------------------------
The issue I had in the first iteration was setting up the RecyclerView to display the list of clubs in Richmond. The program was supposed to read an Excel file and populate an ArrayList with Location objects, which included the location name, description, address, and age.  Then, the RecyclerView would use that data to display the list on the screen. However, we were not able to parse the excel file. So I hardcoded the Location objects into the program to populate the ArrayList. I used a RecyclerView Adapter to provide binding to the data set used in the RecyclerView display. This list has been generated, however, I could not pull data from another file to complete this. That would have been more secure.

Saif Haif Report
---------------------------------
The issue I had in the first iteration was trying to read the excel file using Apache POI method. At the beginning of our first iteration. I was planning to write a code that works with both types of file formats (.xls and .xlsx). However, when I used "WorkbookFactory" method, my code did not work, and I couldn't fix the issue. I ended up using "HSSFWorkbook" method that works only with ( .xls) file format.

ITERATION 2:

DEMO VIDEO: https://www.youtube.com/watch?v=T9EaiB8qs1M&feature=youtu.be

Anthony Caliri & An Nguyen Report
---------------------------------
This time around was much easier since most of the code was in place and we could easily reference the first iteration's code for this iteration. This is especially true for the tests. No new major classes were added this time around. All user stories were completed and one was added to accomodate for the "more information" box in the Google map.

Saif Haif Report
---------------------------------
In the second iteration. I started too early, so I have not encountered any difficulty or issue. I was able to get The webpageHandler and webpagehandlerTest to work before the second iteration even started. 

Sabrina Kundu Report
---------------------------------
For this iteration I continued developing the list activity. I've completed all user stories for price, today's hours, and more information. It was much easier to deal with RecyclerView this time because I had already done the hard parts during the first iteration.   

ITERATION 3:

**You must set the location on your emulator to: Lat = 37.54650485506589 and Long = -77.44719844311476**

DEMO VIDEO: https://www.youtube.com/watch?v=o52wXNQNRWk&feature=youtu.be

An Nguyen Report
---------------------------------
Not too many problems this time around. The framework was all in place and we simply had to add some functionalities.

Saif Haif Report
---------------------------------
In the last iteration. I only had to do three tests, So I did not face any problems during the last iteration 

Sabrina Kundu Report
---------------------------------
Added functionaility that allows users to long click on a location in the ListActivity screen to save as Favorite. The RecyclerView item's background turns orange and a message pops up say the location was added to Favorites. I also added the functionality to unfavorite a location. The RecyclerView item's background color goes back to transparent and a message pops up saying the location was unfavorited. It was hard make both the favorites and webpage re-direction functionalities work because they are using the same TextView. However, I used Click for redirecting the user to a webpage and LongClick for add the location to Favorites. 

Anthony Caliri & Josh Hann Report
---------------------------------
Added "my location" button to find current location as indicated by the phone GPS. Also added "quick buttons" to quickly go to turn-by-turn directions from current location to the desired destination. We also added a blue dot that shows on the map-view the exact location of the phone according to the phone's GPS. We also implemented the function of saving the last known location so that when the user re-opens the app without having GPS enabled, it will show the last known loaction of the phone. 

For Josh Hann's commits, check for the message with "--> COMMITTED BY JOSH HANN <--". This was pre-aproved by Dr. Damevski and is sufficient to be graded by the TA's.

General Style Report
---------------------------------
Fixed styling of methods so they match each other stylistically. Removed extra spaces and fixed the curly brackets.

Lint Warnings Not Fixed:

Android Lint: Correctness
- Obsolete Gradle Dependency: Upgrading to newer version of Gradle caused build issues. Application works fine with current version of Gradle and we did not want to mess with anything that would change that since we are near the end of the iteration.

Android Lint: Performance
- Problems stated by warnings where either irelavent or did not reflect the code (i.e. the unused resources warning).

Android Lint: Security
- AllowBackup/FullBackupContent Problems: Adding fullBackupContent would be unnessary as file backups for our app are not important.

Android Lint: Useablilty
- Missing support for Firebase App Indexing: Our app does not use Firebase and doesnt need to be searched for by other apps

Class Structure:
- Feild can be local - MapUtils: File is not used. Warning would go away if work on the file had continued.

Declaration Redundancy:
- Declaratoin access can be weaker:
  - MapUtils: File is not used.
  - Event: File is not used.
  - Time: Getters need to be public
  - Hours: Getters need to be public
 
 - Unused Declaration:
  - Event: File is not used. (We want to keep it in the project/repo however)
  - MapsActivity: checkGpsCoordination() & getLocation(), left in for furture possible use.
  - Time: Says methods are not used but they are used.
  - Hours: getOpenTime() & getClosedTime(), left in for future possible use.
  - Location: Says methods are not used but they are.
  
Error Handling:
  - Empty 'try' block in ListActivity: left in for possible debuging
  
Java 5:
  - 'StringBuffer' may be 'StringBuilder': Keeping as 'StringBuffer' because the implementation is known to work.
  
Probable bugs:
  - Constant conditions & exeptions - MapsActivity: Says mehtod call may produce NullPointerException but the null is handled later in the program. Willnot cause a problem.
  - Unused Assignment - Event: File is not used, as stated before.

Spelling:
  - Typos where either inside method/vairable names or words it did not recognize.
  
XML:
  - Unbound XML namespace prefix - google_maps_api.xml: This file was not writen by us (auto-generated?), so we did not want to mess with it.
