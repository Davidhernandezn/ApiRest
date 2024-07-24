@echo off
set p02= -Dsonar.projectName=training-ts-demo-base-v1
set p03= -Dsonar.projectKey=training-ts-demo-base-v1
set p04= -Dsonar.sources=src/main
set p05= -Dsonar.java.binaries=src/main
set p07= -Dsonar.java.libraries=E:/Users/coem/.m2/repository/org/projectlombok/lombok/1.18.30/lombok-1.18.30.jar
set p08= -Dsonar.exclusions=src/test,**/*AESLlaves.java,**/*Application.java,**/*MongoDatasource.java,**/*Main,**/HeadersAspectService.java
set p09= -Dsonar.javascript.lcov.reportPaths=/target/site/jacoco/jacoco.xml
set p11= -Dsonar.coverage.exclusions=src/**/constant/**,src/**/constants/**,src/**/registry/**,src/**/documents/**,src/**/document/**,src/**/dtos/**,src/**/dto/**,src/**/models/**,src/**/model/**,src/**/config/**,src/**/auth/**,src/**/algoritm/**,src/**/crypto/**,src/**/util/**,src/**/utils/**,src/**/aspect/**,src/**/aspects/**
set p12= -Dsonar.sourceEncoding=UTF-8
set p13= -Dsonar.host.url=http://10.57.113.151:9000/
set p14= -Dsonar.login=TOKENSONAR 

"E:\Users\coem\sonar-scanner\bin\sonar-scanner.bat" %p02% %p03% %p04% %p05% %p06% %p07% %p08% %p09% %p10% %p11% %p12% %p13% %p14%