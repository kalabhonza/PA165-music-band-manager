# Music band manager REST API
REST API for Musician band manager. In order to test this API, test date need to be inserted and you have to log in then as well.

## Insert test data
`curl -i -X POST http://localhost:8080/pa165/rest/populate`

## Login to system as musician
`curl -i -X GET http://localhost:8080/pa165/rest/user-login/{name}/password/{pass}`

## Login to system as manager
`curl -i -X GET http://localhost:8080/pa165/rest/manager-login/{name}/password/{pass}`

## Get all musicians withou band
`curl -i -X GET http://localhost:8080/pa165/rest/musicians/free`

# Send invite to band as Manager
`curl -i -X POST http://localhost:8080/pa165/rest/managers/{bandId}/offers/{musicianId}`

## Accept invitation to band 
`curl -i -X POST http://localhost:8080/pa165/rest/musicians/{name}/{musicianID}/offers/{bandID}`

## Decline invitation to band 
`curl -i -X PUT http://localhost:8080/pa165/rest/musicians/{name}/{musicianID}/offers/{bandID}`
