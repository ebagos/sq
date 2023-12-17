#mvn clean verify sonar:sonar \
#  -Dsonar.projectKey=test-java \
#  -Dsonar.projectName='test-java' \
#  -Dsonar.host.url=http://localhost:9000 \
#  -Dsonar.token=sqp_82f9de8e0b43218387ae178130a760ac025abf99

mvn jacoco:prepare-agent test jacoco:report sonar:sonar \
  -Dsonar.projectKey=test-java \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=sqp_82f9de8e0b43218387ae178130a760ac025abf99