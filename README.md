# parcel-app

Spring boot service for pushing Parcels objects via user input CLI into local application storage and writing once per minute grouped weights by postal codes to the user output console.
How to build > Clone project sources , clean and build using gradle tool.<BR/>
How to run > After gradle build task completed, the target jar created in the destination path ./build/libs/parcel-app-0.0.1-SNAPSHOT.jar <BR/>
In order to run the app - just execute following commands in CLI: CD ./build/libs  java -jar parcel-app-0.0.1-SNAPSHOT.jar <BR/>
If application runs succesfully, the user can add parcels data into the local storage by executing commands in following format: <BR/>
( weight as decimal digits XXX.XXX ) ( space )  ( postal code positive digits of fixed 5 digits length XXXXX ) then press enter. <BR/>
First param is weight value, then goes space as separator, and after that is second param as postal code value. <BR/>
Example of input : <BR/>
1.5 14001 <BR/>
2.0 15001 <BR/>
3.7 19001 <BR/>
42.55 20010 <BR/>
<BR/>
Once per minute user gets info of parcels weights grouped by postal codes. <BR/>
Validation info corrects the user in case of wrong input format.<BR/>
To stop the applciation run is enough to type "quit" without quotes and press enter.
