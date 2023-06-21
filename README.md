# autoyours

Save your time from lunch reservation.

**Use this at your own risk.**

## Requirements

JDK (for build) and/or JRE (for run) required.

```bash
sudo apt update && sudo apt install -y openjdk-17-jdk-headless

echo "export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))" >> ~/.bashrc

source ~/.bashrc
```

## How to deploy

For people who want to deploy this tool as a cron job.

```bash
mkdir autoyours && cd autoyours

wget https://github.com/owner203/autoyours/releases/download/latest/autoyours.jar

vi config.toml

(crontab -l 2>/dev/null; echo "1 12 * * 5 cd $PWD && java -jar $PWD/autoyours.jar") | crontab -
```

## How to build

For people who want to compile this tool by themselves.

```bash
git clone https://github.com/owner203/autoyours.git && cd autoyours

chmod +x ./mvnw

./mvnw install

vi config.toml

chmod +x ./autoyours

./autoyours
```

## About config.toml

You need a config.toml file before running.

```toml
[account]
login_id = "auto-yours@example.com"
password = "PASSWORD"
customer_id = "C0012345" # Set your own customer ID here.
customer_company_name = "インターネットグループ"
customer_name = "東京 花子"
customer_email = "auto-yours@example.com"

[setups]
service_id = "S001" # 東京(S001)
service_menu_id = "S0000063" # セルリアン(S0000063) フクラス(S0000064)
next_monday1 = 1230 # Starting time here. (1200/1215/1230/1245/1300/1315/0)
next_tuesday1 = 0 # 0 means no reservations on this day.
next_wednesday1 = 1230
next_thursday1 = 0
next_friday1 = 1230 
next_monday2 = 1230 # The Monday after next. (1200/1215/1230/1245/1300/1315/0)
next_tuesday2 = 0
next_wednesday2 = 1230
next_thursday2 = 0
next_friday2 = 0
```
