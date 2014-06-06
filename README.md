eScheduler
==========
Manage and Share events online, vote for desired dates.

Installation
==========

Tomcat has to be installed before building with the ant build file. The role manager-script has to be set in tomcat 7.
This Project has only been tested wit tomcat 7.

Configuration of the ant file
==========
Your choosen Password and Username for the role manager-script has to bet set in the "build.properties".

1	# Settings for automatic build	
2	project.name=eScheduler
3	builder=AyvazyanBobekFreudensprungWillinger
4	tomcat.manager.url=http://127.0.0.1:8080/manager/text
5	tomcat.manager.username=user
6	tomcat.manager.password=pass
