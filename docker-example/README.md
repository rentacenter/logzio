# Docker example
This example shows how to run the [Logz.io Docker container](https://github.com/logzio/logzio-docker) to collect your Docker metrics.

## Manual run

If you want to manually run the Logz.io container, primarily for testing purposes.

Change the **environment**, **application**, and **tier** fields below as appropriate.  While best practices should be to keep *environment* and *application*, *tier* was just an example to add additional fields, add as many that make sense for your use-case.

```shell
$ docker run --name logzio -d --restart=always -v /var/run/docker.sock:/var/run/docker.sock logzio/logzio-docker -t <supply logz.io token here> -a environment=dev -a application=ecommerce -a tier=web
```

## AWS ECS

The best practice for running a specific agent on every EC2 instance in the ECR cluster is to launch a specific task definition with a user data script. (Reference [blog post](https://aws.amazon.com/blogs/compute/running-an-amazon-ecs-task-on-every-instance/).)

TODO:
1. create example TaskDefinition
1. create example IAM role that includes `ecs:StartTask`
1. create example user-data that includes ECS start-task command
```
yum install -y aws-cli jq
instance_arn=$(curl -s http://localhost:51678/v1/metadata | jq -r '. | .ContainerInstanceArn' | awk -F/ '{print $NF}' )
az=$(curl -s http://instance-data/latest/meta-data/placement/availability-zone)
region=${az:0:${#az} - 1}
aws ecs start-task --cluster $cluster --task-definition cadvisor:1 --container-instances $instance_arn --region $region
```
