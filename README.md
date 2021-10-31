# MowerScalaProject
## Description
Automatic lawn mower scala project.

This project has been created using IntelliJ IDEA with SBT.

## SW and dependencies versions:

IntelliJ IDEA --> 2021.2.2

scala sdk --> 2.13.6

JDK --> 1.8

scalatest --> 3.2.10

## How to run
Just open the project using IntellJ and run the Simulate application (MowerScalaProject/src/main/scala/mower/Simulate).

The application will ask you for an input file. You can find an example in the following path "MowerScalaProject/src/main/resources/InputFile.txt"

### TEST
The input file is as follow :

5 5

1 2 N

GAGAGAGAA

3 3 E

AADAADADDA

The result should be as follow (The last position of the mower) :
> Tondeuse 1 : 1 3 N 
> 
> Tondeuse 2 : 5 1 E 


### How to run unit test
Go to the following path "MowerScalaProject/src/test/scala/" and run the test for MowItNowTest class.

