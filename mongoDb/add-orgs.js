var db = connect('127.0.0.1:27017/organizations-srv');

//create the names collection and add documents to it
db.organizations.insertOne({'name' : 'organization1', '_id' : 1, '_class' : 'com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity'});
db.organizations.insertOne({'name' : 'organization2', '_id' : 2, '_class' : 'com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity'});
db.organizations.insertOne({'name' : 'organization3', '_id' : 3, '_class' : 'com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity'});
db.organizations.insertOne({'name' : 'organization4', '_id' : 4, '_class' : 'com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity'});
