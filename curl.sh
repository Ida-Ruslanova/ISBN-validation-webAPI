echo "Validation of ISBN 13 - should return 'Is Valid'"

curl -X GET "http://localhost:8080/api/v1/isbn/validateisbnnumber/9783161484100"

echo  "Validation of ISBN 10 - should return 'Is Valid'"

curl -X GET "http://localhost:8080/api/v1/isbn/validateisbnnumber/9185057819"

echo "Validation of ISBN 14 - should return 'Is Not Valid'"

curl -X GET "http://localhost:8080/api/v1/isbn/validateisbnnumber/97831614841000"

echo "Validation of ISBN 11 - should return 'Is Not Valid'"

curl -X GET "http://localhost:8080/api/v1/isbn/validateisbnnumber/91850578191"
