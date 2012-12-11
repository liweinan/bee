# DATABASE CONFIG IN JBOSS AS 7

	<datasource jndi-name="java:jboss/datasources/bee" pool-name="bee" enabled="true" use-java-context="true">
	    <connection-url>jdbc:postgresql:bee</connection-url>
	    <driver>postgresql-driver</driver>
	    <security>
	        <user-name>weli</user-name>
	    </security>
	</datasource>

	<driver name="postgresql-driver" module="org.postgresql">
	    <driver-class>org.postgresql.Driver</driver-class>
	</driver>

# MODULE FILES

	weli@power:~/projs/jboss-as-7.1.1.Final/modules/org/postgresql/main$ tree
	.
	├── module.xml
	├── postgresql-9.1-903.jdbc4.jar
	└── postgresql-9.1-903.jdbc4.jar.index

	0 directories, 6 files

# MODULE CONFIG

	<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
	   <!-- Load with jboss-cli command:
	        /subsystem=datasources/jdbc-driver=postgresql-driver:add(driver-name=postgresql-driver, driver-class-name=org.postgresql.Driver, driver-module-name=org.postgresql)
	   -->
	   <resources>
	     <resource-root path="postgresql-9.1-903.jdbc4.jar"/>
	   </resources>
	   <dependencies>
	     <module name="javax.api"/>
	     <module name="javax.transaction.api"/>
	   </dependencies>
	</module>