TwilioSpringExample
===================

Stand-alone Spring application with Twilio that allows you to send SMS messages.

You will need to create a twilio.properties file in both the src/main/java/resources/META-INF and src/test/java/resources folders in order for Twilio to be correctly configured with your own Twilio account details.
There is an example properties file called twilio-example.properties in both directories.

This project contains integration and unit tests for sending SMS messages and also contains a basic main class that creates the Spring context and sends an SMS message.
