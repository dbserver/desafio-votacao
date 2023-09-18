FROM postgres:latest

ENV POSTGRES_USER ${POSTGRES_USER}
ENV POSTGRES_PASSWORD ${POSTGRES_PASSWORD}
ENV POSTGRES_DB ${POSTGRES_DB}

COPY ./database/init.sql:/docker-entrypoint-initdb.d/init.sql

EXPOSE 5432
