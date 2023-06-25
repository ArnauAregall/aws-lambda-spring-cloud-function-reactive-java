# aws-lambda-spring-cloud-function-reactive-java

Demo application to test Reactive Spring Cloud Functions on AWS Lambdas using a native runtime.

Requirements:

- Docker
- Java +17 GraalVM
- SAM

## Building the native AWS Lambda artifact with SAM

**Linux**:

````bash
sam build
````

**macOS**:

AWS Lambda requires native applications to be built in an Amazon Linux compatible OS, so on macOS we need to run the build
in a compatible Docker container.

1. Build the Docker image compatible with AWS Linux:

    ````bash
    ./aws-image/build-aws-image.sh
    ````

2. Run `sam build` specifying the Docker image:

    ````bash
    sam build --use-container --build-image tech.aaregall.lab/amazonlinux-graalvm:latest
    ````

## Deploy the application as an AWS Lambda using SAM

Define the following environment variables on the deployment host.

````bash
export AWS_REGION=...
````

### Deploy the Lambda application

````bash
sam deploy --region $AWS_REGION
````

### See CloudWatch logs

````bash
sam logs -n SpringCloudFunctionLambda --stack-name aws-lambda-spring-cloud-function-reactive-java
````

### Delete the Lambda application

````bash
sam delete SpringCloudFunctionLambda
````
