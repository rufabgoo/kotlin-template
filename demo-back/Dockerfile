FROM openjdk:11-jre-slim

RUN set -eux; \
	apt-get update; \
	apt-get install -y --no-install-recommends \
		procps curl \
	; \
	rm -rf /var/lib/apt/lists/*

COPY build/app/ /app/

EXPOSE 8001

WORKDIR /app/

ENTRYPOINT ["/app/bin/app.sh", "start"]