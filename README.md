#### Docker

###### installation

* [Windows](https://docs.docker.com/docker-for-windows/install/)
* [Centos](https://docs.docker.com/install/linux/docker-ce/centos/)
* [Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
* [MacOS](https://docs.docker.com/docker-for-mac/install/)

###### docker-compose

* [Docs](https://docs.docker.com/compose/install/)

###### docker repository

* [Hub](https://hub.docker.com/explore/)

#### Git

```bash
# config credential
git config --global credential.helper store

# config merge
git config --global merge.tool vimdiff
git config --global mergetool.prompt false
git config --global mergetool.keepBackup false
```

[Conventional Commits](https://www.conventionalcommits.org/)

```bash
git commit -m '<type>: <subject>'
```

###### type

* feat: add new feature
* fix: fix bugs
* docs: create/update documentations
* refactor: code changes that neither fixes a bug nor adds a feature
* perf: code changes that improves performance
* test: create/update tests
* revert: reverts a previous commit
* chore: create/update build script
* style: changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
* build: changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)
* ci: changes to CI configuration files and scripts (example scopes: Travis, Circle, BrowserStack, SauceLabs)

###### subject

* contains a succinct description of the change
* use the imperative, present tense: "change" not "changed" nor "changes"
* don't capitalize the first letter
* no dot (.) at the end

#### GitBook

Simple writing with [Gitbook Editor](https://legacy.gitbook.com/editor)

```bash
# install gitbook
npm install -g gitbook-cli

# install plugins
gitbook install

# run
gitbook serve --port 4000

# deamon
setsid gitbook serve --no-live --port 4000
```

#### MySQL

```bash
# transaction only supported for InnoDB
SHOW ENGINES;

# create database
DROP DATABASE IF EXISTS demo;
CREATE DATABASE demo;

# reset auto increment
ALTER TABLE table_name AUTO_INCREMENT = 1;
```

#### MongoDB

```bash
# transaction only supported for Replicas
mongod --replSet rs0
mongo --eval "rs.initiate()"
```

#### IDEA Plugins

* IdeaVim
* Lombok
* Markdown Navigator / Markdown support
* YAML/Ansible support
* Python Community Edition

#### Gradle

```bash
# create container
docker run -it --rm --name gradle -v PROJECT_PATH:/home/gradle/workspace gradle bash

# show tasks
gradle tasks

# build executable jar
gradle bootJar

# compile querydsl
gradle compileQuerydsl

# run unittest
gradle test

# generate wrapper
gradle wrapper
```

#### SpringBoot

```bash
# standard
java -jar application-version.jar

# action (command / kafka / cron / help)
java -jar application-version.jar [action] [bean_name]

# debug
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5555 -jar application.jar
```
