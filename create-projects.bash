#!/usr/bin/env bash

mkdir ted-micro-service
cd ted-micro-service

spring init \
  --boot-version=2.7.2 \
  --build=gradle \
  --java-version=11 \
  --packaging=jar \
  --name=product-server \
  --package-name=com.ted.micro.product \
  --groupId=com.ted.micro.product \
  --dependencies=actuator,webflux \
  --version=0.0.1-SNAPSHOT \
  product-server

spring init \
  --boot-version=2.7.2 \
  --build=gradle \
  --java-version=11 \
  --packaging=jar \
  --name=review-server \
  --package-name=com.ted.micro.review \
  --groupId=com.ted.micro.review \
  --dependencies=actuator,webflux \
  --version=0.0.1-SNAPSHOT \
  review-server

spring init \
  --boot-version=2.7.2 \
  --build=gradle \
  --java-version=11 \
  --packaging=jar \
  --name=recommend-server \
  --package-name=com.ted.micro.recommend \
  --groupId=com.ted.micro.recommend \
  --dependencies=actuator,webflux \
  --version=0.0.1-SNAPSHOT \
  recommend-server

spring init \
  --boot-version=2.7.2 \
  --build=gradle \
  --java-version=11 \
  --packaging=jar \
  --name=facade-server \
  --package-name=com.ted.micro.facade \
  --groupId=com.ted.micro.facade \
  --dependencies=actuator,webflux \
  --version=0.0.1-SNAPSHOT \
  facade-server

cd ..
