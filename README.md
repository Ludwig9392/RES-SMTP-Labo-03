# RES-SMTP-Labo-03

> Authors : Loïc Frueh, Dejvid Muaremi

[TOC]

------

This Repo globally implements a Mail Pranks Service

## Description of the project

This project is about sending forged e-mail to multiple defined receivers. A forged e-mail is a mail that appears to be sent by someone but in reality it is sent by offten malicious poeple or little prankers. This project allow you to generate those forged mail automaticaly and sent them to the choosen victims.

## Structure of the Project

In the project folder (mailPranksGenerator) there is two main folder, src/main/java and config.

- config contains all the configurations files that you will interact with in order to set up your prank campaign 
  - config.properties: the main settings of the program
  - victims : a list of all the victims's e-mail.
  - messages: a list of messages you want to send to the victims
- src/main/java contains the project's java sources code files.
  - config (packtage): contains source files that consist in getting the infos of the config files
    - IConfigManager: interface that designs methods for parsing and getting infos from the config files
    - ConfigManager: class that implements the IConfigManager interface and realize the parsing job
  - model (packtage): contains source files that consist in modelizing usefull object for this project
    - Mail(packtage): contains source files that modelize Object directly related with the classic mail concept
      - Person: class that modelize a guy in mail therms (first name, last name, email)
      - Group: class that modelize a group of person
      - Mail: class that modelize a classic mail with all its fields (from, to, cc, etc...)
    - Prank(packtage): contains source files that modelize Object more related with our prank than a classic mail (forged-mail)
  - smtp (packtage): contains source files that consist in implementing the SMTP Protocol
    - ISmtpClient: interface that designs methods for a fonctional SMTP client
    - SmtpClient:  class that implements the ISmtpClient interface and realize the connection with a SMTP server
    - SmtpProtocol: class that define usefull constants for an implementation of the SMTP Protocol
  - mailPranksGenerator (the main class) : the class that generate and send the pranks by e-mail

## Configuration of a prank campaign

In this project you don't have to modify the source code files in order to set up your campaign. 

In fact, you don't have to touch the code at all. All you have to do is to modify the configuration files in order to make them corresponding to your personal prank campaign plans.

### config.properties

In this file you have four settings:

- smtpServer: provide the smtp server you want to contact

- smtpListenPort: provide the port in which the SMTP server is listening
- numberOfGroups: provide the number of victim groups in which you want to play your prank campaign
- witnesses: provide an email that will be put in the CC field of the forged e-mails that are going to be sent

Be careful that each group must at least contains 3 victims (1 sender and 2 receiver), so keep that in mind when you fill the numberOfGroup field.

### victims

In this file, you just need to write the list of the emails you wanted to prank.
It's important that you provide one e-mail per line.
So each line of this file is an e-mail you wanted to play your prank compaign on.

### messages

In this file, you need to write a list of messages you want to send in the pranked e-mails of your prank campaign.
Just be sure to begin all your messages by a subject "<insert the name of your subject>" with two new line caractère.
When you have finished writing a message, add a new line and write "***" to serparate this message from the next message you will add.

For all these cases there is already an example in the configuration files, so feel free to inspire you from that.

## Setting up a mock SMTP server

### What is a SMTP Mock server ?

A Mock SMTP server is a SMTP server that you can use for testing if outgoing mail are sent but without actually sending them.
You can see what the look like and checked if all the e-mail field are correctly filled. It is a good choice for testing e-mail software
or application. Some of them provide you a web interface, others a software interface it depends of which mock server you choose.

### What kind of Mock server do we use in this project ?

In this project we use MockMock which provides a web interface.

### How do WE set up the MockMock server ?

We have created a Dockerfile, which is in the launcher directory. In this Dockerfile, we have added to the base "java" image the .jar launcher of the MockMock server (got on github). Then we have defined an ENTRYPOINT ('java -jar <mockjar>.jar) which allow us then to use the "docker run" command to pass arguments to this ENTRYPOINT command.

Exemple: sudo docker run <name of the docker image> -p 2525 will launch the container and execute 'java -jar <mockjar>.jar -p 2525' with this command we have set up the mock SMTP server to listen on the port 2525 and not the default port (which is 25).

### How do YOU set up the MockMock server ?

Fortunately, you don't have to do all this things to run the mock SMTP server for your tests.
We have done a script ine the launchers directory, and the only thing you have to do is to run this script with an argument.

./mockServerLauncher 2525 ---> run a mock SMTP server in a docker container that listen on the 2525 port.

you need to provide an argument in any case even if you want the default 25 port (with this script anyhow)

## Run your prank campaign

Once you have edited the configuration files as needed and the SMTP server (or mockserver) is running you can begin your campaign.
All you have to do after this is to run the prankCampaignLauncher script and "PAF that make Chocapics" as the French say.

Well that's it, Enjoy !