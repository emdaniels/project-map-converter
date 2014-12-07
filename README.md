Project Map Converter
=====================

This project was made for Tree Canada to display years of planting and sponsor data in a project map on their website (https://treecanada.ca/en/map/) using Google Maps JavaScript API v3. 

The application reads data from a CSV file, converts this data into a JSON structure using GSON, and writes it to a JS file. The converter will accept an object type (Planting or Sponsor) and a language type (English or French) to build the JSON accordingly. The JSON generated can then be manipulated by the mapping.js and mapping.css files and embedded into a website.

License
=======

This application is released under version 2.0 of the Apache License. 
The GSON library and license is viewable here: https://code.google.com/p/google-gson/
