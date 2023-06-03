default: versioncheck

server:
	./gradlew backendRun

client:
	./gradlew frontendRun

yarn-unlock:
	./gradlew kotlinUpgradeYarnLock

clean:
	./gradlew clean

build:
	./gradlew build

versioncheck:
	./gradlew dependencyUpdates

depends:
	./gradlew dependencies

run-docker:
	docker run --rm --env-file=docker_env_vars -p 8080:8080 ${IMAGE_NAME}:${VERSION}

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.1.1 --distribution-type=bin
