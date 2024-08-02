# autoyours

Save your time from lunch reservation.

**Use this at your own risk.**

## Requirements

JDK (for build) and/or JRE (for run) is required.

```bash
sudo apt update && sudo apt install -y openjdk-17-jdk-headless

echo "export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))" >> ~/.bashrc

source ~/.bashrc
```

The local timezone must be set to `JST` (`UTC+9`).

```bash
sudo timedatectl set-timezone Asia/Tokyo
```

## About config.toml

You need a config.toml file exists in the current directory before running.

You can find your own customer ID (`会員ID`) on the web interface while booking a menu of any location (`東京`/`宮崎`/`仙台`).

Here is an example of `東京 花子`, who is booking a menu of `セルリアン`, at `12:30-13:00 next Monday`, `12:00-12:30 next Tuesday`, `13:00-13:30 next Wednesday`, `12:15-12:45 on Tuesday in two weeks`, and `13:15-13:45 on Wednesday in two weeks`.

```toml
[account]
login_id = "hanako-tokyo@autoyours.jp"       # Set your login ID (= email address in the most cases) here.
password = "PASSWORD"                        # Set your password here.
customer_id = "C0012345"                     # Set your own customer ID here.
customer_company_name = "オートヨアーズ"       # Set your company name here.
customer_name = "東京 花子"                   # Set your name (Kanji) here.
customer_email = "hanako-tokyo@autoyours.jp" # Set your email address here.

[setups]
service_id = "S001"          # 東京(S001)
service_menu_id = "S0000063" # セルリアン(S0000063) フクラス(S0000064)
next_monday1 = 1230          # Next Monday
next_tuesday1 = 1200         # Next Tuesday
next_wednesday1 = 1300       # Next Wednesday
next_thursday1 = 0           # Next Thursday
next_friday1 = 0             # Next Friday
next_monday2 = 0             # Monday in two weeks
next_tuesday2 = 1215         # Tuesday in two weeks
next_wednesday2 = 1315       # Wednesday in two weeks
next_thursday2 = 0           # Thursday in two weeks
next_friday2 = 0             # Friday in two weeks
```

## How to deploy

You can deploy this tool as a cron job.

Here is an example of running this tool automatically at `12:01` every `Friday`.

You may also need to add some jobs manually for irregular cases.

```bash
mkdir autoyours && cd autoyours

wget https://github.com/owner203/autoyours/releases/latest/download/autoyours.jar

vi config.toml

(crontab -l 2>/dev/null; echo "1 12 * * 5 cd $PWD && java -jar autoyours.jar >> ~/autoyours.log 2>&1") | crontab -
```

## How to build

You can build this tool by yourself.

```bash
git clone https://github.com/owner203/autoyours.git && cd autoyours

chmod +x ./mvnw

./mvnw install

vi config.toml

chmod +x ./autoyours

./autoyours
```
