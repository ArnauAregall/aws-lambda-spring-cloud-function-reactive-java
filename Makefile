build-SpringCloudFunctionLambda:
	./gradlew --no-daemon clean nativeCompile
	echo '#!/bin/sh' > ./build/bootstrap
	echo 'set -euo pipefail' >> ./build/bootstrap
	echo './aws-lambda-spring-cloud-function-reactive-java' >> ./build/bootstrap
	chmod +x ./build/bootstrap
	cp ./build/bootstrap $(ARTIFACTS_DIR)
	cp ./build/native/nativeCompile/aws-lambda-spring-cloud-function-reactive-java $(ARTIFACTS_DIR)