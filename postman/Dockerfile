FROM postman/newman

USER root
ADD ./wait-for .
COPY Organizations-srv.postman_collection.json /etc/postman/Organizations-srv.collection.json

ENTRYPOINT [ "./wait-for", "organizationsservicetest:8080", "--", "newman", "run", "/etc/postman/Organizations-srv.collection.json", "--disable-unicode"]