# Spring cloud function and cloud lambda demo project:
Project contains three modules:
- core-config: contains a repository, model and few functions that represent some functional api;
- cloud-function: contains function web dependency. After start makes rest api from core-config functions on a local server;
- cloud-lambda: aws lambda dependencies and plugins (fat jar). There is function name property in application.yml that cloud be overridden by FUNCTION_NAME env.

## run on aws instruction:
+ in aws console (lambda service) upload a fat jar;
+ set FUNCTION_NAME env;
+ set org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest as request handler
+ test function;
