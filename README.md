# parcel-app

Spring boot service for pushing Parcels objects via user input CLI into local apllication storage and writing once per minute grouped weights by postal codes to the user output console.
How to build > Clone project sources , clean and build using gradle tool.
How to run > After gradle build task completed, the target jar created in the destination path ./build/libs/parcel-app-0.0.1-SNAPSHOT.jar. 
In order to run the app - just execute following commands in CLI: CD ./build/libs  java -jar parcel-app-0.0.1-SNAPSHOT.jar
If application runs succesfully, the log messages, user can add parcels data into the local storage by executing commands in following format
<weight as decimal digits XXX.XXX><space><postal code positive digits of fixed 5 digits length XXXXX > then press enter.
First param is weigth value , space as separator, second param is psotal code value. 
Example of input : 
1.5 14001
2.0 15001
3.7 19001
42.55 20010

Once per minute user gets info of parcels weights grouped by postal codes.
Validation info corrects the user in case of wrong input format.
