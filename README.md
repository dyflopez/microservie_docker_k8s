docker run --name mysql_semillero --platform linux/x86_64   -e MYSQL_ROOT_PASSWORD=eldany1234 -p 3306:3306/tcp -d mysql:5.7




docker run -d  -p 3306:3306/tcp  --name mysql_semillero --platform linux/x86_64  --network tuto -e MYSQL_ROOT_PASSWORD=eldany1234 mysql:5.7