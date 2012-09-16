#install maven
#downlaod goose and package then run following command
#   #to package go to downloaded directory
#   cd gooseDir
#   # package
#   mvn clean package
#make sure to change the version
mvn install:install-file -Dfile=target/goose-2.1.19.jar -DartifactId=goose -Dversion=2.1.19 -DgroupId=goose -DpomFile=pom.xml
