FROM mongo
USER root
COPY ./add-orgs.js /docker-entrypoint-initdb.d/
EXPOSE 27017/tcp
CMD ["mongod"]