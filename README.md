## DB
docker run --name roots-postgresql -p 5432:5432 -e POSTGRES_USER=roots -e POSTGRES_PASSWORD=roots -e POSTGRES_DB=roots_db -d postgres