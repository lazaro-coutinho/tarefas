test:
	@ ./mvnw test

package: test
	@ ./mvnw clean package -DskipTests
	
build: package
	@ docker build -t lvc/tarefas-api .

run: build
	@ docker-compose up -d

stop: 
	@ docker-compose down -v

deploy: build
	@ docker login --username=_ --password=$$(heroku auth:token) registry.heroku.com
	@ docker image tag lvc/tarefas-api:latest registry.heroku.com/lvc-tarefas-api/web:1
	@ docker image push registry.heroku.com/lvc-tarefas-api/web:1
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/lvc-tarefas-api/web:1 -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
			-H "Authorization: Bearer $$(heroku auth:token)" \
			-H "Content-Type: application/json" \
			-H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
			-d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
			https://api.heroku.com/apps/lvc-tarefas-api/formation