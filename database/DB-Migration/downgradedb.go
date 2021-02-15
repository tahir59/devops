package main

import (
	"log"
	"github.com/golang-migrate/migrate"
	_ "github.com/golang-migrate/migrate/database/postgres"
	_ "github.com/golang-migrate/migrate/source/file"
)

func main() {
        m, err := migrate.New(
                "file://MIGRATIONDATA",
                "postgres://USERNAME:PASSWORD@DBHOST:DBPORT/DBNAME?sslmode=disable")
        if err != nil {
                log.Fatal(err)
        }
        if err := m.Down(); err != nil {
                log.Fatal(err)
        }
}
