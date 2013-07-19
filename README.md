Sample showing the pathway from Java to Scala
-------------

To run this from the command prompt with maven use:

mvn install exec:java -DskipTests

At the command line the following options are available:
* m 'new message'   #set a new message that is used whenever the count is requested.
* c                 #get a count of how many times the new message has been displayed
* h                 # current history Counting Service
* u Old Person Id New Person Id #update a person id in the count history
* q                 #quit
