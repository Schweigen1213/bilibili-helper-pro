<div align="center">
<h1 align="center">
bilibili-helper-pro
</h1>

[![GitHub stars](https://img.shields.io/github/stars/gitldy1013/bilibili-helper-pro?style=flat-square)](https://github.com/gitldy1013/bilibili-helper-pro/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/gitldy1013/bilibili-helper-pro?style=flat-square)](https://github.com/gitldy1013/bilibili-helper-pro/network)
[![GitHub issues](https://img.shields.io/github/issues/gitldy1013/bilibili-helper-pro?style=flat-square)](https://github.com/gitldy1013/bilibili-helper-pro/issues)
[![GitHub license](https://img.shields.io/github/license/gitldy1013/bilibili-helper-pro?style=flat-square)](https://github.com/gitldy1013/bilibili-helper-pro/blob/main/LICENSE) 
[![GitHub All Releases](https://img.shields.io/github/downloads/gitldy1013/bilibili-helper-pro/total?style=flat-square)](https://github.com/gitldy1013/bilibili-helper-pro/releases)
[![GitHub contributors](https://img.shields.io/github/contributors/gitldy1013/bilibili-helper-pro?style=flat-square)](https://github.com/gitldy1013/bilibili-helper-pro/graphs/contributors)
![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/gitldy1013/bilibili-helper-pro?style=flat-square)

</div>

# 工具简介

这是一个利用 GitHub Action 定时任务实现哔哩哔哩（Bilibili）每日自动投币，点赞，分享视频，直播签到，银瓜子兑换硬币，漫画每日签到，简单配置即可每日轻松获取 65 经验值，快来和我一起成为 Lv6 吧~~~~

## 功能列表

**本项目不会增加类似于自动转发抽奖，秒杀，下载版权受限视频等侵犯UP主/B站权益的功能，开发这个应用的目的是单纯的技术分享。下游分支开发者/使用者也请不要滥用相关功能。**

**本项目欢迎其他开发者参与贡献，基于本工具的二次开发，使用其他语言重写都没有什么问题，能在技术上给你带来帮助和收获就很好**

**请不要滥用相关API，让我们一起爱护B站 ❤**

* [x] 每天上午 9 点 10 分自动开始任务。*【运行时间可自定义】*
* [x] 哔哩哔哩漫画每日自动签到，自动阅读1章节 。
* [x] 每日自动从热门视频中随机观看 1 个视频，分享一个视频。
* [x] 每日从热门视频中选取 5 个进行智能投币 *【如果投币不能获得经验了，则不会投币】*
* [x] 投币支持下次一定啦，可自定义每日投币数量。*【如果检测到你已经投过币了，则不会投币】*
* [x] 大会员月底使用快到期的 B币券，给自己充电，一点也不会浪费哦，默认开启。*【已支持给指定UP充电】*
* [x] 大会员月初 1 号自动领取每月 5 张 B 币券和福利。
* [x] 每日哔哩哔哩直播自动签到，领取签到奖励。*【直播你可以不看，但是奖励咱们一定要领】*
* [x] 通过server酱推送执行结果到微信。
* [x] Linux用户支持自定义配置了。
* [x] 投币策略更新可配置投币喜好。*【可配置优先给关注的up投币】*
* [x] 自动送出即将过期的礼物。 *【默认开启，未更新到新版本的用户默认关闭】*
	  
[点此查看更新日志][2]

[点击快速开始使用](#使用说明)

# 目录

- [工具简介](#工具简介)
  - [功能列表](#功能列表)
- [目录](#目录)
- [使用说明](#使用说明)
  - [一、Actions 方式](#一actions-方式)
    - [配置自定义功能](#配置自定义功能)
  - [二、使用 Linux Crontab 方式](#二使用-linux-crontab-方式)
    - [步骤](#步骤)
    - [运行效果](#运行效果)
  - [三、使用 Windows10](#三使用-windows10)
    - [步骤](#步骤-1)
  - [四、使用 Docker](#四使用-docker)
- [微信订阅通知](#微信订阅通知)
  - [订阅执行结果](#订阅执行结果)
- [快速更新](#快速更新)
  - [使用 Github Actions 自动同步源仓库代码](#使用-github-actions-自动同步源仓库代码)
  - [手动拉取最新代码](#手动拉取最新代码)
- [免责声明](#免责声明)
- [应用趋势](#应用趋势)
- [API 参考列表](#api-参考列表)

# 使用说明

## 一、Actions 方式

1. **Fork 本项目**
2. **获取 Bilibili Cookies**
- 浏览器打开并登录 [bilibili 网站](https://www.bilibili.com/)
- 按 F12 打开 「开发者工具」 找到 应用程序/Application -> 存储 -> Cookies
- 找到 `bili_jct` `SESSDATA` `DEDEUSERID` 三项，并复制值，创建对应的 GitHub Secrets。

<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/20201012001307.png" alt="app"></p>

3. **点击项目 Settings -> Secrets -> New Secrets 添加以下 3 个 Secrets。**

| Name       | Value            |
| ---------- | ---------------- |
| DEDEUSERID | 从 Cookie 中获取 |
| SESSDATA   | 从 Cookie 中获取 |
| BILI_JCT   | 从 Cookie 中获取 |

<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/20201013210000.png" alt="app"></p>

4. **开启 Actions 并触发每日自动执行**

**Github Actions 默认处于关闭状态，还大家请手动开启 Actions ，执行一次工作流，验证是否可以正常工作。**

<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/workflow_dispatch.png" alt="app"></p>

**Fork 仓库后，GitHub 默认不自动执行 Actions 任务，请修改 `.github/trigger.json` 文件,将 `trigger` 的值改为 `1`，这样每天就会自动执行定时任务了。**

```patch
{
- "trigger": 0
+ "trigger": 1
}
```

如果需要修改每日任务执行的时间，请修改 `.github/workflows/auto_task_bilili.yml`，在第 12 行左右位置找到下如下配置。

```yml
  schedule:
    - cron: '30 10 * * *'
    # cron表达式，Actions时区是UTC时间，所以下午18点要往前推8个小时。
    # 示例： 每天晚上22点30执行 '30 14 * * *'
```

本工具的 Actions 自动构建配置了缓存，平均运行时间在 20s 左右。

*如果收到了 GitHub Action 的错误邮件，请检查 Cookies 是不是失效了，用户主动清除浏览器缓存，会导致 `BILI_JCT` 和 `DEDEUSERID` 失效*

**请各位使用 Actions 时务必遵守Github条款。不要滥用Actions服务。**

**Please be sure to abide by the Github terms when using Actions. Do not abuse the Actions service.**

### 配置自定义功能

**配置文件位于 `src/main/resources/config.json`**

参数示意

| Key                | Value         | 说明                                                                                                          |
| ------------------ | ------------- | ------------------------------------------------------------------------------------------------------------- |
| numberOfCoins      | [0,5]         | 每日投币数量,默认 5                                                                                           |
| selectLike         | [0,1]         | 投币时是否点赞，默认 0, 0：否 1：是                                                                           |
| ~~watchAndShare~~  | ~~[0,1]~~     | ~~观看时是否分享~~                                                                                            |
| monthEndAutoCharge | [false,true]  | 年度大会员月底是否用 B币券 给自己充电，默认 `true`                                                            |
| devicePlatform     | [ios,android] | 手机端漫画签到时的平台，建议选择你设备的平台 ，默认 `ios`                                                     |
| coinAddPriority    | [0,1]         | 0：优先给热榜视频投币，1：优先给关注的up投币                                                                  |
| userAgent          | 浏览器UA      | 用户可根据部署平台配置，可根据userAgent参数列表自由选取，如果触发了HTTP/1.1 412 Precondition Failed也请修改UA |
| skipDailyTask      | [0,1]         | 是否跳过每日任务，如果需要临时关闭每日任务，此项改为1即可，开启则改为0即可                                    |

userAgent可选参数列表
| 平台      | 浏览器         | userAgent                                                                                                                           |
| --------- | -------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| Windows10 | EDGE(chromium) | Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36 Edg/86.0.622.69 |
| Windows10 | Chrome         | Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36                 |
| masOS     | safari         | Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Safari/605.1.15               |
| macOS     | Firefox        | Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:65.0) Gecko/20100101 Firefox/65.0                                                  |
| macOS     | Chrome         | Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36            |

*ps：如果尝试给关注的 up 投币十次后（保不准你关注的是年更up主），还没完成每日投币任务，则切换成热榜模式，给热榜视频投币*

*投币数量代码做了处理，如果本日投币不能获得经验了，则不会投币，每天只投能获得经验的硬币。假设你设置每日投币 3 个，早上 7 点你自己投了 2 个硬币，则十点半时，程序只会投 1 个）*

## 二、使用 Linux Crontab 方式

### 步骤

1. 在linux shell环境执行以下命令，并按照提示输入SESSDATA，DEDEUSERID，BILI_JCT，SCKEY四个参数
```
wget https://raw.githubusercontent.com/gitldy1013/bilibili-helper-pro/main/setup.sh && chmod +x ./setup.sh && sudo ./setup.sh
```

**Linux用户使用jar包时如果需要自定义配置，请[点此下载](https://github.com/gitldy1013/bilibili-helper-pro/blob/main/src/main/resources/config.json)配置文件，将其到和jar包同一目录即可，执行时优先加载外部配置文件**

```
bilibili-helper-pro.jar
config.json
```

除此之外，也可以通过点击 [bilibili-helper-pro/release](https://github.com/gitldy1013/bilibili-helper-pro/releases)，下载已发布的版本，解压后将jar包手动上传到Linux服务器，使用crontab完成定时执行。

**命令格式解释：**

`30 10 * * * java -jar /home/bilibili-helper-pro.jar DEDEUSERID SESSDATA BILI_JCT SCKEY>/var/log/cron.log &`

| args                               | 说明                                                                                                       |
| ---------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| 30 10 * * *                        | cron 定时时间                                                                                              |
| java -jar                          | 执行jar包                                                                                                  |
| /home/BILIBILI-HELP.jar            | jar包路径                                                                                                  |
| DEDEUSERID SESSDATA BILI_JCT SCKEY | 传入参数的顺序，参数含义请见上文,SCKEY可为空（用于server酱推送日志，等同actions任务配置中的SERVERPUSHKEY） |
| >/var/log/cron.log &               | 日志写入的路径                                                                                             |


**命令示例：**

```shell
# *如果Cookies参数中包含特殊字符，例如`%`请使用`\`转义*
# m h  dom mon dow   command
30 10 * * * java -jar /home/BILIBILI-HELP.jar DEDEUSERID SESSDATA BILI_JCT >/var/log/cron.log &
```

### 运行效果

<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/liunxImg.png" alt="app"></p>

## 三、使用 Windows10

### 步骤

1. 点击 [bilibili-helper-pro/release](https://github.com/gitldy1013/bilibili-helper-pro/releases)，下载已发布的版本。解压，在解压后的目录打开 `Powershell` 需要装有 Java 运行环境。
   
**Windows用户使用jar包时如果需要自定义配置，请[点此下载](https://github.com/gitldy1013/bilibili-helper-pro/blob/main/src/main/resources/config.json)配置文件，将其到和jar包同一目录即可，执行时优先加载外部配置文件**

1. 执行 `java -jar /home/bilibili-helper-pro.jar DEDEUSERID SESSDATA BILI_JCT `

<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/powershell.png" alt="app"></p>

# 微信订阅通知

## 订阅执行结果

1. 前往 [sc.ftqq.com](http://sc.ftqq.com/3.version) 点击登入，创建账号（建议使用 GitHub 登录）。
2. 点击点[发送消息](http://sc.ftqq.com/?c=code) ，生成一个 Key。将其增加到 Github Secrets 中，变量名为 `SERVERPUSHKEY`
3. [绑定微信账号](http://sc.ftqq.com/?c=wechat&a=bind) ，开启微信推送。
<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/serverpush.png" alt="app"></p>
4. 推送效果展示
<p align="center"><img src="https://cdn.jsdelivr.net/gh/gitldy1013/bilibili-helper-pro@main/docs/IMG/wechatMsgPush.png" alt="app"></p>

# 快速更新

## 使用 Github Actions 自动同步源仓库代码

该方案来自 `@happy888888` `#PR6` ，由于源仓库 `config.json` 文件的更改会覆盖用户自己的 `config.json` 配置文件，所以暂时没有合并到 `main` 分支。

**使用自定义功能的朋友慎用该方法，建议使用手动拉取的方式，手动解决代码冲突**

在 `./github/workflows` 目录下创建 `auto_merge.yml` 文件，内容如下

```yml
name: auto_merge

on:
  workflow_dispatch:
  schedule:
    - cron: 0 2 * * fri
    # cron表达式,每周五10点执行一次，UTC时间，使用北京时间请+8可按照需求自定义。  

jobs:
  merge:
    runs-on: ubuntu-latest
    steps:
    - name: Checkout
      uses: actions/checkout@v2
      with:
        ref: main
        fetch-depth: 0
        lfs: true

    - name: Set git identity
      run : |
        git config --global user.email "41898282+github-actions[bot]@users.noreply.github.com"
        git config --global user.name "github-actions[bot]"
    - name: Load upstream commits
      run: |
        git update-index --assume-unchanged ./src/main/resources/config.json
        git pull https://github.com/gitldy1013/bilibili-helper-pro.git --log --no-commit
    - name: Apply commit changes
      run: |
        if [ -f ./.git/MERGE_MSG ]; then
        mkdir ./tmp && cp ./.git/MERGE_MSG ./tmp/message
        sed -i "1c [bot] AutoMerging: merge all upstream's changes:" ./tmp/message
        sed -i '/^\#.*/d' ./tmp/message
        git commit --file="./tmp/message"
        else
        echo "There is no merge commits."
        fi
    - name: Push Commits
      env:
        DOWNSTREAM_BRANCH: main
        TZ: Asia/Shanghai
      run: git push origin $DOWNSTREAM_BRANCH
```

## 手动拉取最新代码

1. 通过 `git remote -v` 查看是否有源头仓库的别名和地址。

例如这里 `origin` 就是你自己的仓库，`upstream` 是你 `fork` 的源头仓库。

```bash
$ git remote -v
origin  https://github.com/JunzhouLiu/cxmooc-tools.git (fetch)
origin  https://github.com/JunzhouLiu/cxmooc-tools.git (push)
upstream        https://github.com/CodFrm/cxmooc-tools.git (fetch)
upstream        https://github.com/CodFrm/cxmooc-tools.git (push)

```

2. fork 仓库后，将你的仓库拉到本地，如果没有源头仓库，则添加源头仓库

```bash
git remote add upstream https://github.com/gitldy1013/bilibili-helper-pro.git
```

3. 更新上游仓库 main 分支的代码（pull 操作实际上是 `fetch+merge`）

```bash
git pull upstream main
```

4. 将从源头仓库更新后的代码推送到你自己的 GitHub 仓库

```bash
git push origin main
```

5. 这样你就能快速的从我的仓库拉取最新的代码，并更新到你自己的仓库里了。自定义配置的同学，要注意 `config.json` 不要被我的文件覆盖了。

# 免责声明

1. 本工具不会记录你的任何敏感信息，也不会上传到任何服务器上。（例如用户的cookies数据，cookies数据均存在Actions Secrets中或者用户自己的设备上）
2. 本工具不会记录任何执行过程中来自b站的数据信息，也不会上传到任何服务器上。（例如av号，bv号，用户uid等）。
3. 本工具执行过程中产生的日志，仅会在使用者自行配置推送渠道后进行推送。日志中不包含任何用户敏感信息。

# 应用趋势

<p align="center"><img src="https://starchart.cc/gitldy1013/bilibili-helper-pro.svg" alt="app"></p>

# API 参考列表

- [JunzhouLiu/BILIBILI-HELPER](https://github.com/JunzhouLiu/BILIBILI-HELPER)
- [SocialSisterYi/bilibili-API-collect](https://github.com/SocialSisterYi/bilibili-API-collect)
- [happy888888/BiliExp](https://github.com/happy888888/BiliExp)
