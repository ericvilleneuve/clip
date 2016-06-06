# clip
![clip!](http://findicons.com/files/icons/196/office_tools/128/clip.png)

A simple URL shortener. This is currently deployed and running at https://cl-ip.herokuapp.com/.

The shortened URL is shown in the text field with an imaginary `http://cl.ip/` domain.
A test link also appears underneath which allows to recover the original URL. 
For demonstration purposes, that page doesn't actually redirect but simply shows the expanded URL. 

## Prerequisites
Java 8

## Running the app

### Running it locally
* `./gradlew run`
* When started, you simply hit [http://localhost:7575](http://localhost:7575).

### Running Java tests
`./gradlew test`

### Running the Jasmine tests
`./gradlew npm_test`

## Running in IntelliJ 16
1. Import as a Gradle project with the following options:
   * ![Import Gradle!](http://i.imgur.com/sIblijN.png)
2. Run `main()` in `com.evil.clip.Clip`
3. Hit [http://localhost:7575](http://localhost:7575)
