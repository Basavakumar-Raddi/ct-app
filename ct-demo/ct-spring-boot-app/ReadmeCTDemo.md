prerequisites for running the app :
    create a database schema with name "A1  " in mysql(5.6) and run the below query to create a table
        CREATE TABLE `stake` (
        	`id` VARCHAR(255) NOT NULL,
        	`created_by` VARCHAR(255) NOT NULL,
        	`created_on` DATETIME NOT NULL,
        	`last_updated_by` VARCHAR(255) NULL DEFAULT NULL,
        	`last_updated_on` DATETIME NULL DEFAULT NULL,
        	`name` VARCHAR(255) NULL DEFAULT NULL,
            `sector` VARCHAR(255) NULL DEFAULT NULL,
        	`initiation_date` BIGINT(20) NULL DEFAULT NULL,
        	`investment_required` DOUBLE(20,2) NULL DEFAULT NULL,
            `stake_offered` DOUBLE(20,2) NULL DEFAULT NULL,
        	PRIMARY KEY (`id`)
        )

    have set the property "spring.jpa.generate-ddl=true" for creating table just in case.
    Requires java-8

Run the app as a spring boot application (on default port - 8080)

The api's exposed are
view points (http-get) -- http://localhost:8080/api/v1/stakes
add point (http-post) -- http://localhost:8080/api/v1/stakes
    sample json : {
                      "name": "Crusher",
                      "sector": "Cement",
                      "initiationDate": "2018-May-18",
                      "investmentRequired": 3523547,
                      "stakeOffered": 12

                   }
delete api (http-delete) -- http://localhost:8080/api/v1/stakes?id=0c157350-aabd-4c55-a42e-6e2de16b195b
    pass the id of the point to be deleted
update api -- http://localhost:8080/api/v1/stakes
    sample json : {
                      "id": "8d439db2-20c2-44a3-93d5-719914ff0b9e",
                          "name": "Clairbot",
                          "sector": "ChatBot",
                          "initiationDate": "2018-Jul-18",
                          "investmentRequired": 2660519,
                          "stakeOffered": 10

                   }

This app also has the junit test cases written for all the above api's, service class and repository class.