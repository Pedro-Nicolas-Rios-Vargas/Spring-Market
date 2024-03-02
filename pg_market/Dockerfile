FROM postgres:latest
LABEL authors="ordep"

COPY ["schema.sql", "/docker-entrypoint-initdb.d/"]
