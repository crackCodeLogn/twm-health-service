# twm-health-service

### Tech stack:

<ol>
<li>Java 11 - OpenJDK11</li>
<li>Quarkus <a href="https://quarkus.io/">https://quarkus.io/</a></li>

[comment]: <> (<li>Heroku <a href="https://dashboard.heroku.com/">https://dashboard.heroku.com/</a></li>)
<li>Postgres - for prod</li>
<li>CockroachDB - for local dev. CRDB is built on postgres. <a href="https://www.cockroachlabs.com">https://www.cockroachlabs.com/</a></li>
<li>Swagger - OpenAPI - non prod</li>
<li>Protobuf</li>
<li>Lombok</li>
<li>Apache Commons Lang3</li>
<li>Assertj</li>
</ol> 

### Local Swagger snap:

![alt text](pics/Screenshot%20from%202021-11-12%2017-05-57.png)

### Important notepoints:

<ul>
<li>Database is using timestamptz to store all time in UTC by default. Whenever the java process will read it from DB, it will get the time converted into the JVM time.</li>
</ul> 

## Deployments - Server only (!app):

| Serial | Version | Time | Artifact | Significant changes | 
| ------ | ------- | ---- | -------- | ------------------- |
| 0      | 1.0     | 2021-11-21   | 0.0.1-SNAPSHOT | Base commit |

## Backup option from postgres:-

psql <DATABASE_URL> \copy (select * from <table_name) to '<local_file.csv>' with CSV;

## Backup restore into postgres:-

I had to write customized code to read csv and use the upload endpoint to bulk add.
