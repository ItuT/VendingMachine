# VendingMachine

## REQUIREMENTS

 This vending machine which vends products based upon four (4) denominations of coins and returns coins if there is no item.
 
__________________________________________________________________________________________________________________________

## The initial plan

Java SE application
Swing Desktop GUI

Design
State Machine design pattern for different vending machine states

Factory Design pattern to allow different vending machine implementations

TESTING
Junit for testing

DATA RESOURCES
A file based datasource that can be updated via uploading a Json file with available products and coin denominations.

PROBLEMS Identified during the POC

- not up to the latest tech standards, ioT, NFC, Mobile payments etc

## The second plan(smart Vending Machine)

Tech

Java EE 7
Glassfish Server
MySQL DB
JPA
Servlet (API)

Clients
Angular 5 web App
Android native App

THE BIG PICTURE

ioT vending maching
- can auto order products based on availability in the inventory
- location based services and/or
- can be remotely updated with new product prices

__________________________________________________________________________________________________________________________

## SOLUTIONS

### OLD ABONDONED IMPLEMENTATION

This is an exclipse project
Run unit tests to test the system. The GUI was not completed.

https://github.com/ItuT/VendingMachine/tree/master/VendingMachine

### NEW DESIGN

#### BACK-END

 ###### DATASOURCE
 https://github.com/ItuT/VendingMachine/blob/master/db.json

 ###### APPLICATION
 Source Code
 https://github.com/ItuT/VendingMachine/tree/master/VendingMachineApp

 API link
 http://vendingmachineapp-env.g4pqirigky.eu-west-2.elasticbeanstalk.com/


#### CLIENTS

 ##### WEB-APP client
 
 Source Code
 https://github.com/ItuT/VendingMachine/tree/master/smart-vending-machine
 
 Access the running version on this link: 

 http://smartvendingmachine.s3-website-eu-west-1.amazonaws.com/

 ##### ANDROID Client
 
 Not implemented


