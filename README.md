# whitbread technical test (foursquare integration)

Technology stack used, Java / Spring / Tomcat & Misc Libraries. 

Time Limit 4hrs
breakdown 
1.5 hours setting up & stabilising environment
2 hours coding
0.5 hours researching foursquare integration

Running example can be found at http://incubator.inconnect.co.uk/whitbread-foursquaretest/location/search

Approach, simple spring controller to handle get and post http requests. 
re used a 3rd party library to save integration time with foursquare. controller captures form input and submits to 
Foursquare API leveraging 3rd party library. Results are returned, processed and rendered to the a jsp via a spring 
controller. Map rendering of locations is handled via google maps. 

Due to time constraints after briefly invesigating the complexity of the mature foursquare api I opted to use a library. 
Live API creation & integration can be demonstrated on other online projects on request. 

Challenges
Finding a way of rendering places on a map using open source frameworks, luckilly the google maps API came to the rescue.

Ommisions
Due to the simplistic nature of the app and time constraints this app has been developer tested. JUnit hasn't been implemented. 
Logging framework such as Log4J hasn't been added. 

