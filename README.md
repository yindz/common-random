![https://opensource.org/licenses/Apache-2.0](https://img.shields.io/badge/License-Apache_2.0-blue.svg)
![](https://img.shields.io/badge/java-1.8%2B-yellow)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.apifan.common/common-random/badge.svg "Maven Central")](https://mvnrepository.com/artifact/com.apifan.common/common-random)
# 随机数据生成器
## 概述
简单易用的随机数据生成器。一般用于开发和测试阶段的数据填充、模拟、仿真研究、演示等场景。可以集成到各种类型的java项目中使用。

## 优点
- 非常轻量级(不到1M)，容易集成，无需过多第三方依赖
- 简单方便，无需编写冗余代码
- 生成的随机数据比较接近真实数据

## 快速开始
- [如何使用](#如何使用)
- [支持哪些随机数据类型](#支持的随机数据类型)
- [JSON库的相关说明](#JSON库说明)
- [拼音库的相关说明](#拼音库说明)
- [常见问题](#常见问题)
- [注意事项](#注意事项)
- [最近版本历史](#版本历史)

## 支持的随机数据类型

### 日期时间
- [随机日期](#随机日期)
- [随机时间](#随机时间)
- [随机时间戳](#随机时间戳)
- [随机时区名称](#随机时区名称)

### 地理
- [随机省份和城市](#随机地址)
- [随机国家或地区（基于ISO 3166-1标准）](#随机国家或地区)
- [虚拟经纬度](#随机经纬度)
- [随机邮编](#随机地址)
- [虚拟联系地址](#随机地址)
- [随机固话区号](#随机固话)
- [虚拟固话号码](#随机固话)

### 互联网
- [虚拟邮箱地址](#随机邮箱地址)
- [虚拟域名](#随机域名)
- [虚拟静态URL](#随机静态url)
- [虚拟IPv4地址](#随机ipv4地址)
- [虚拟IPv6地址](#随机ipv6地址)
- [虚拟网卡MAC地址](#随机网卡mac地址)
- [随机强密码](#随机强密码)
- [随机User-Agent](#随机useragent)
- [随机端口号](#随机端口号)
- [虚拟app名称](#随机app信息)
- [虚拟app bundle id](#随机app信息)
- [虚拟app版本号](#随机app信息)

### 个人信息
- [虚拟中文人名](#中文名)
- [虚拟英文人名](#英文名)
- [虚拟身份证号码](#随机生成符合规则的虚拟身份证号码)
- [虚拟网络昵称](#随机网络昵称)
- [虚拟拼音网络昵称](#随机网络昵称)
- [虚拟手机号码](#随机中国大陆手机号)
- [虚拟QQ号码](#随机qq信息)
- [虚拟姓名图片文件](#生成姓名头像)
- [虚拟非主流QQ网名](#随机qq信息)
- [随机民族名称](#随机民族名称)

### 教育
- [随机学历](#随机教育背景信息)
- [虚拟小学名称、年级、班级](#随机教育背景信息)
- [虚拟中学名称、年级、班级](#随机教育背景信息)
- [随机高校及专业名称](#随机教育背景信息)

### 金融
- [随机股票名称+股票代码](#股票名称和股票代码)
- [虚拟日K线数据](#日k线数据)
- [随机开放式基金名称+基金代码](#开放式基金名称和基金代码)
- [随机货币信息](#货币)
- [虚拟银行卡（借记卡及信用卡）号码](#随机虚拟银行卡号码)

### 体育
- [随机六大足球联赛球队名称](#足球联赛球队名称)
- [随机篮球联赛球队名称](#篮球联赛球队名称)
- [随机国家及地区的足球代表队名称](#国家及地区的足球代表队)

### 其它
- [随机数字](#随机数字)
- [随机汉字](#随机汉字)
- [随机成语](#随机成语)
- [随机唐诗](#随机唐诗)
- [虚拟车牌号](#随机中国大陆车牌号)
- [随机热门手机型号](#热门手机型号)
- [随机RGB颜色值](#随机颜色值)
- [随机HEX颜色值](#随机颜色值)
- [随机中文短句](#随机中文短句)
- [随机英文文本](#随机英文文本)
- [虚拟企业及部门名称](#随机公司及部门名称)
- [随机营销号文案](#随机营销号)
- [随机ISBN](#随机EAN)
- [随机EAN商品编码](#随机EAN)
- [随机行业分类](#随机行业分类)
- [随机统一社会信用代码](#统一社会信用代码)
- [数据生成工具](#数据生成工具)

## 如何使用
### Java版本要求
1.8或更高

### 配置依赖
已发布到maven中央仓库。
```xml
<dependency>
    <groupId>com.apifan.common</groupId>
    <artifactId>common-random</artifactId>
    <version>1.0.16</version>
</dependency>
```

### 基础用法
#### [随机数字](https://www.scaler.com/topics/random-number-generator-in-c/)
```
//生成1个1~101(不含)之间的随机整数
int a = NumberSource.getInstance().randomInt(1, 101);

//生成8个1~101(不含)之间的随机整数
int[] b = NumberSource.getInstance().randomInt(1, 101, 8);

//生成1个10000000000~20000000001(不含)之间的随机长整数
long c = NumberSource.getInstance().randomLong(10000000000L, 20000000001L);

//生成9个10000000000~20000000001(不含)之间的随机长整数
long[] d = NumberSource.getInstance().randomLong(10000000000L, 20000000001L, 9);

//生成1个0.01~0.51(不含)之间的随机整数
double e = NumberSource.getInstance().randomDouble(0.01D, 0.51D);

//生成8个0.01~0.51(不含)之间的随机整数
double[] f = NumberSource.getInstance().randomDouble(0.01D, 0.51D, 8);

//生成随机百分比
BigDecimal percent = NumberSource.getInstance().randomPercent();
```
#### 随机汉字
```
//生成1个随机汉字
String i = OtherSource.getInstance().randomChinese();

//生成4个随机汉字
String j = OtherSource.getInstance().randomChinese(4);
```
#### 随机成语
```
String idiom = OtherSource.getInstance().randomChineseIdiom();
```
#### 随机唐诗
```
Poem p = OtherSource.getInstance().randomTangPoem();
```
#### 随机人名
##### 中文名
```
//生成1个随机中文人名(性别随机)
String k = PersonInfoSource.getInstance().randomChineseName();

//生成1个随机男性中文人名
String k2 = PersonInfoSource.getInstance().randomMaleChineseName();

//生成1个随机女性中文人名
String k3 = PersonInfoSource.getInstance().randomFemaleChineseName();
```
##### 英文名
```
//生成1个随机英文人名
String l = PersonInfoSource.getInstance().randomEnglishName();
```
##### 生成姓名头像
- 该功能可以根据用户姓名快速生成各类网站、app的用户头像
- 背景颜色随机，数据源取自最近十几年来广受欢迎的颜色，详情参见: [Colors of the Year](https://www.w3schools.com/colors/colors_trends.asp)
- 支持使用自定义TTF字体，但是需要使用者自行保证字体版权合法以避免纠纷
- 该功能不支持在Android运行环境中使用(因为缺少awt)
```
//姓名
String name = PersonInfoSource.getInstance().randomChineseName();
//头像文件保存路径
String targetPath = "/home/user/picture/" + name + ".png;

//使用默认的Dialog字体
PersonInfoSource.getInstance().generateNamePicture(name, targetPath);

//使用自定义的字体
String font = "/home/user/font/SourceHanSansCN-Normal.ttf";
PersonInfoSource.getInstance().generateNamePicture(name, targetPath, font);
```

#### 随机生成符合规则的虚拟身份证号码
```
//生成1个随机的虚拟身份证号码，地区为广西壮族自治区，男性，出生日期在1990年11月11日至1999年12月12日之间
LocalDate beginDate = LocalDate.of(1990,11,11);
LocalDate endDate = LocalDate.of(1999,12,12); 
String id1 = PersonInfoSource.getInstance().randomMaleIdCard("广西壮族自治区", beginDate, endDate);

//生成1个随机的虚拟身份证号码，地区为河北省，女性，出生日期在2001年1月11日至2008年2月22日之间
LocalDate beginDate2 = LocalDate.of(2001,1,11);
LocalDate endDate2 = LocalDate.of(2008,2,22);
String id2 = PersonInfoSource.getInstance().randomFemaleIdCard("河北省", beginDate2, endDate2);

//生成1个随机的虚拟身份证号码，地区为广西壮族自治区，男性，年龄为18岁
String id3 = PersonInfoSource.getInstance().randomMaleIdCard("广西壮族自治区", 18);

//生成1个随机的虚拟身份证号码，地区为河北省，女性，年龄为19岁
String id4 = PersonInfoSource.getInstance().randomFemaleIdCard("河北省", 19);
```
注意：
- 身份证号码前6位地区码数据取自[民政部网站2019年公开数据](http://www.mca.gov.cn/article/sj/xzqh/2019/)
- 随机生成的虚拟身份证号码符合校验规则，但有可能与真实号码相同（纯属巧合）

#### 随机虚拟银行卡号码
```
//生成1个随机虚拟VISA信用卡号码
String cc1 = PersonInfoSource.getInstance().randomCreditCardNo(CreditCardType.Visa);

//生成1个随机虚拟MasterCard信用卡号码
String cc2 = PersonInfoSource.getInstance().randomCreditCardNo(CreditCardType.MasterCard);

//生成1个随机虚拟American Express信用卡号码
String cc3 = PersonInfoSource.getInstance().randomCreditCardNo(CreditCardType.Amex);

//生成1个随机虚拟银联信用卡号码
String cc4 = PersonInfoSource.getInstance().randomCreditCardNo(CreditCardType.UnionPay);

//生成1个随机虚拟JCB信用卡号码
String cc5 = PersonInfoSource.getInstance().randomCreditCardNo(CreditCardType.JCB);

//生成1个随机虚拟借记卡(储蓄卡)号码
String dbc = PersonInfoSource.getInstance().randomDebitCardNo();
```
注意：
- 随机生成的虚拟银行卡号码只是前缀和位数符合规则，不会与现实中的真实卡号产生重合，无法用于支付，仅供模拟测试/仿真/项目演示等用途

#### 随机中国大陆手机号
```
//生成1个随机中国大陆手机号
String m = PersonInfoSource.getInstance().randomChineseMobile();
```
#### 随机邮箱地址
```
//生成1个随机邮箱地址，后缀随机，邮箱用户名最大长度为10
String n1 = InternetSource.getInstance().randomEmail(10);

//生成1个随机邮箱地址，后缀为163.com，邮箱用户名最大长度为10
String n2 = InternetSource.getInstance().randomEmail(10, "163.com");
```
#### 随机域名
```
//生成1个随机域名，域名最大长度为16
String dm = InternetSource.getInstance().randomDomain(16);
```
#### 随机ipv4地址
```
//生成1个随机公网IPv4地址
String pub = InternetSource.getInstance().randomPublicIpv4();

//生成1个随机私有(内网)IPv4地址
String prv = InternetSource.getInstance().randomPrivateIpv4();
```
#### 随机ipv6地址
```
//生成1个随机ipv6地址
String ipv6 = InternetSource.getInstance().randomIpV6();
```
#### 随机端口号
```
//生成1个随机端口号
int port = InternetSource.getInstance().randomPort();
```
#### 随机app信息
```
//生成1个随机App名称
String appName = InternetSource.getInstance().randomAppName();

//生成1个随机App Bundle ID
String appBundleID = InternetSource.getInstance().randomAppBundleId();

//生成1个随机App版本号
String appVersionCode = InternetSource.getInstance().randomAppVersionCode();
```
#### 随机静态url
```
//生成1个随机静态url，后缀为jpg
String url = InternetSource.getInstance().randomStaticUrl("jpg");
```
#### 随机日期
```
//生成1个2020年的随机日期，日期格式为yyyy-MM-dd
String d1 = DateTimeSource.getInstance().randomDate(2020, "yyyy-MM-dd");

//生成1个2020年1月2日之后的随机日期，日期格式为yyyy-MM-dd
String d2 = DateTimeSource.getInstance().randomFutureDate(LocalDate.of(2020,1,2), "yyyy-MM-dd");

//生成1个今天(基于系统时间判断)之后的随机日期，日期格式为yyyy-MM-dd
String d3 = DateTimeSource.getInstance().randomFutureDate("yyyy-MM-dd");

//生成1个2020年1月2日之前1年内的随机日期，日期格式为yyyy-MM-dd
String d4 = DateTimeSource.getInstance().randomPastDate(LocalDate.of(2020,1,2), "yyyy-MM-dd");

//生成1个今天(基于系统时间判断)之前1年内的随机日期，日期格式为yyyy-MM-dd
String d5 = DateTimeSource.getInstance().randomPastDate("yyyy-MM-dd");

//生成1个2020年1月2日之前10年内的随机日期，日期格式为yyyy-MM-dd
String dd = DateTimeSource.getInstance().randomPastDate(LocalDate.of(2020,1,2), 3650, "yyyy-MM-dd");

//生成1个2000年1月11日至2010年2月22日范围之间的随机日期，日期格式为yyyy-MM-dd
LocalDate beginDate = LocalDate.of(2000,1,11);
LocalDate endDate = LocalDate.of(2010,2,22);
String d6 = DateTimeSource.getInstance().randomDate(beginDate, endDate, "yyyy-MM-dd");
```
#### 随机时间
```
//生成过去7天范围内的随机时间
LocalDateTime time1 = DateTimeSource.getInstance().randomPastTime(7);

//生成未来7天范围内的随机时间
LocalDateTime time2 = DateTimeSource.getInstance().randomFutureTime(7);

//生成2020年2月14日当天范围内的随机时间
LocalDateTime time3 = DateTimeSource.getInstance().randomTime(2020, 2, 14);

//生成过去100秒范围内的随机时间
LocalDateTime time4 = DateTimeSource.getInstance().randomPastTime(LocalDateTime.now(), 100);

//生成未来100秒范围内的随机时间
LocalDateTime time5 = DateTimeSource.getInstance().randomFutureTime(LocalDateTime.now(), 100);
```
#### 随机时间戳
```
//生成1个当天范围内的随机时间戳
long ts1 = DateTimeSource.getInstance().randomTimestamp(LocalDate.now());

//生成1个2020年3月6日范围内的随机时间戳
long ts2 = DateTimeSource.getInstance().randomTimestamp(LocalDate.of(2020, 3, 6));

//生成1个介于2020年3月6日12:00:00至2020年3月6日12:30:00之间的随机时间戳
LocalDateTime begin = LocalDateTime.of(2020, 3, 6, 12, 0, 0);
LocalDateTime end = LocalDateTime.of(2020, 3, 6, 12, 30, 0);
long ts3 = DateTimeSource.getInstance().randomTimestamp(begin, end);

//生成1个2020年3月6日12:00:00之后180秒内的随机时间戳
LocalDateTime base1 = LocalDateTime.of(2020, 3, 6, 12, 0, 0);
long ts4 = DateTimeSource.getInstance().randomFutureTimestamp(base1, 180);

//生成1个2020年3月7日13:00:00之前120秒内的随机时间戳
LocalDateTime base2 = LocalDateTime.of(2020, 3, 7, 13, 0, 0);
long ts5 = DateTimeSource.getInstance().randomPastTimestamp(base2, 120);
```
#### 随机时区名称
```
//生成1个随机时区名称
String timezone = DateTimeSource.getInstance().randomTimezoneName();
```

#### 随机强密码
```
//生成1个随机强密码，长度为16，无特殊字符
String pwd1 = PersonInfoSource.getInstance().randomStrongPassword(16, false);

//生成1个随机强密码，长度为16，有特殊字符
String pwd2 = PersonInfoSource.getInstance().randomStrongPassword(16, true);
```

#### 随机地址
```
//随机获取省份
String prv = AreaSource.getInstance().randomProvince();

//随机获取城市(省份+城市，以逗号为分隔符)
String city = AreaSource.getInstance().randomCity(",");

//随机获取邮编
String zipCode = AreaSource.getInstance().randomZipCode();

//生成1个随机中国大陆详细地址
String addr = AreaSource.getInstance().randomAddress();
```

#### 随机国家或地区
```
//随机获取1个编码首字母为b的国家或地区
CountryOrRegionCode code0 = AreaSource.getInstance().randomCountryOrRegionCode("b");

//随机获取1个国家或地区(不限首字母)
CountryOrRegionCode code1 = AreaSource.getInstance().randomCountryOrRegionCode();
```

#### 随机经纬度
```
//随机生成1个纬度
double lat = AreaSource.getInstance().randomLatitude();

//随机生成1个经度
double lng = AreaSource.getInstance().randomLongitude();
```

#### 随机固话
```
//随机固话区号(省级行政区名称不需要包含后缀)
String phoneCode = AreaSource.getInstance().randomPhoneCode("湖南");

//随机固话号码(使用-作为分隔符，默认的分隔符是空格)
String phoneNumber = AreaSource.getInstance().randomPhoneNumber("广东", "-");
```

#### 随机中国大陆车牌号
```
//生成1个随机中国大陆车牌号(新能源车型)
String n1 = OtherSource.getInstance().randomPlateNumber(true);

//生成1个随机中国大陆车牌号(非新能源车型)
String n2 = OtherSource.getInstance().randomPlateNumber();
```
#### 随机网络昵称
```
//生成1个随机英文网络昵称，最大长度为8个字符
String nickName = PersonInfoSource.getInstance().randomNickName(8);

//生成1个随机汉字网络昵称，最大长度为8个汉字
String nickName2 = PersonInfoSource.getInstance().randomChineseNickName(8);

//基于随机汉字网络昵称生成1个拼音网络昵称，最大长度为4个汉字
String nickName3 = PersonInfoSource.getInstance().randomPinyinNickName(4);
```
#### 随机qq信息
```
//生成1个随机QQ号
String qq = PersonInfoSource.getInstance().randomQQAccount();

//生成1个随机非主流QQ网名
String nickName = PersonInfoSource.getInstance().randomQQNickName();
```

#### 随机民族名称
```
String ethnicName = OtherSource.getInstance().randomEthnicName();
```

#### 随机教育背景信息
```
//随机获取学历
String degree = EducationSource.getInstance().randomDegree();

//随机获取本科高校名称
String college = EducationSource.getInstance().randomCollege();

//随机高校专业名称
String majorName = EducationSource.getInstance().randomMajorName();

//随机获取小学名称
String primarySchoolName = EducationSource.getInstance().randomPrimarySchoolName();

//随机获取小学年级
String primarySchoolGrade = EducationSource.getInstance().randomPrimarySchoolGrade();

//随机获取中学名称
String highSchoolName = EducationSource.getInstance().randomHighSchoolName();

//随机获取中学年级
String highSchoolGrade = EducationSource.getInstance().randomHighSchoolGrade();

//随机班级名称
String className = EducationSource.getInstance().randomClassName();
```
#### 随机公司及部门名称
```
//随机生成1个公司名称，地区前缀为北京
String companyName = OtherSource.getInstance().randomCompanyName("北京");

//随机生成1个公司部门名称
String department = OtherSource.getInstance().randomCompanyDepartment();
```

#### 随机中文短句
```
//随机生成1条中文短句
String sentence = OtherSource.getInstance().randomChineseSentence();
```

#### 随机英文文本
```
//随机生成1条英文文本，包含10个单词
String text = OtherSource.getInstance().randomEnglishText(10);
```

#### 随机营销号
```
String title = OtherSource.getInstance().randomNonsenseTitle("星期一", "下雨");
```

```
String content = OtherSource.getInstance().randomNonsense("星期一", "下雨");
```

#### 随机行业分类
```
OtherSource.getInstance().randomEconomicCategory();
```
说明：行业分类编码和名称来自国家统计局发布的公开数据

#### 统一社会信用代码
```
OtherSource.getInstance().randomSocialCreditCode();
```

#### 随机EAN
```
//随机ISBN，返回结果需要分隔符-，格式例如：978-7-XXXX-XXXX-X
String isbn1 = OtherSource.getInstance().randomISBN(true);

//随机ISBN，返回结果不需要分隔符，格式例如：9787XXXXXXXXX
String isbn2 = OtherSource.getInstance().randomISBN(false);

//随机国际商品编码，格式例如：691XXXXXXXXXX
String ean = OtherSource.getInstance().randomEAN();
```
说明：
- 本程序随机生成的EAN编码长度为13位
- 本程序随机生成的EAN编码符合标准校验规则，仅供模拟测试/仿真/项目演示等用途，并不表示现实中真实存在该编码对应的商品（如有雷同纯属巧合）

#### 随机useragent
```
//随机生成1个PC User-Agent
String ua1 = InternetSource.getInstance().randomPCUserAgent();

//随机生成1个Android User-Agent
String ua2 = InternetSource.getInstance().randomAndroidUserAgent();

//随机生成1个iOS User-Agent
String ua3 = InternetSource.getInstance().randomIOSUserAgent();
```
#### 随机网卡mac地址
```
//随机生成1个网卡MAC地址，使用:作为分隔符
String mac = InternetSource.getInstance().randomMacAddress(":");
```
#### 随机颜色值
```
//随机生成1个RGB颜色值
int[] rgb = OtherSource.getInstance().randomRgbColor();

//随机生成1个16进制(HEX)颜色值
String hex = OtherSource.getInstance().randomHexColor();
```
#### 股票名称和股票代码
```
//随机股票信息(沪A+深A+创业板+科创版)
String[] stock = FinancialSource.getInstance().randomStock();
String stockName = stock[0];
String stockCode = stock[1];

//随机股票信息(港股)
String[] hkStock = FinancialSource.getInstance().randomHKStock();
String hkStockName = hkStock[0];
String hkStockCode = hkStock[1];

//随机股票信息(新三板)
String[] xsbStock = FinancialSource.getInstance().randomXsbStock();
String xsbStockName = xsbStock[0];
String xsbStockCode = xsbStock[1];

//随机股票信息(北交所)
String[] bseStock = FinancialSource.getInstance().randomBseStock();
String bseStockName = bseStock[0];
String bseStockCode = bseStock[1];
```
#### 开放式基金名称和基金代码
```
String[] fund = FinancialSource.getInstance().randomFund();
String fundName = fund[0];
String fundCode = fund[1];
```
#### 日k线数据
```
//随机生成20210201~20210228日期范围内的K线数据，起始价格为100，单日最大涨幅10%，单日最大跌幅-10%
List<KChartData> kList = FinancialSource.getInstance().randomDailyKChartData(100, 0.1, -0.1, "20210201", "20210228");
```
#### 货币
```
//随机获取一种货币信息
CurrencyInfo ci = FinancialSource.getInstance().randomCurrencyInfo();
```
#### 足球联赛球队名称
```
//英超
SportSource.getInstance().randomFootballTeam(CompetitionType.PREMIER_LEAGUE);

//西甲
SportSource.getInstance().randomFootballTeam(CompetitionType.LA_LIGA);

//德甲
SportSource.getInstance().randomFootballTeam(CompetitionType.BUNDESLIGA);

//意甲
SportSource.getInstance().randomFootballTeam(CompetitionType.SERIE_A);

//法甲
SportSource.getInstance().randomFootballTeam(CompetitionType.LIGUE_1);

//荷甲
SportSource.getInstance().randomFootballTeam(CompetitionType.EREDIVISIE);
```
#### 篮球联赛球队名称
```
//CBA
SportSource.getInstance().randomBasketballTeam(CompetitionType.CBA);

//NBA
SportSource.getInstance().randomBasketballTeam(CompetitionType.NBA);
```
#### 国家及地区的足球代表队
```
//(亚洲足联范围内)随机足球队名称
SportSource.getInstance().randomFootballTeam(FootballConfederation.AFC);

//(欧洲足联范围内)随机足球队名称
SportSource.getInstance().randomFootballTeam(FootballConfederation.UEFA);

//随机足球队名称(不限足球联合会)
SportSource.getInstance().randomFootballTeam();
```
访问 [Wikipedia网页](https://en.wikipedia.org/wiki/Category:FIFA_confederations) 可以查看完整的足球联合会列表信息。

#### 热门手机型号
```
OtherSource.getInstance().randomMobileModel();
```

## 数据生成工具
此工具类支持自定义生成符合业务需求的随机数据，示例如下：
```
//准备字段定义
//字段name：随机姓名
DataField df1 = new DataField("name", () -> PersonInfoSource.getInstance().randomChineseName());
//字段birthDate：随机日期
DataField df2 = new DataField("birthDate", () -> DateTimeSource.getInstance().randomPastDate("yyyy-MM-dd"));
//字段salary：随机数字
DataField df3 = new DataField("salary", () -> NumberSource.getInstance().randomInt(5000, 18000));
List<DataField> fieldList = Lists.newArrayList(df1, df2, df3);

//设置数量
int total = 10;

//生成JSON
String json = DataUtils.generateJson(fieldList, total); 

//生成CSV 
String csv = DataUtils.generateCsv(fieldList, total); 

//生成SQL之前先要指定表名
String tableName = "user";
//生成SQL插入语句
String sql = DataUtils.generateJson(fieldList, tableName, total);     
```

## JSON库说明
### 主要变化
考虑到某些项目可能使用其它JSON依赖库，**从 1.0.16 版起**，本程序就不再强依赖 jackson，更加灵活：
- jackson（默认）
- fastjson
- gson

### 依赖坐标
由于不再强依赖 jackson，因此您需要在您的项目中手动添加相关依赖。三选一：
1. jackson
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
</dependency>
```
版本号仅供参考，最新版本请[查看这里](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

2. fastjson
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.83</version>
</dependency>
```
版本号仅供参考，最新版本请[查看这里](https://mvnrepository.com/artifact/com.alibaba/fastjson)

3. gson
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.9.0</version>
</dependency>
```
版本号仅供参考，最新版本请[查看这里](https://mvnrepository.com/artifact/com.google.code.gson/gson)

## 拼音库说明
### 主要变化
考虑到某些项目可能使用 pinyin4j，**从 1.0.16 版起**，本程序就不再强依赖 tinypinyin，更加灵活：
- tinypinyin（默认）
- pinyin4j

### 如何设置
由于不再强依赖 tinypinyin，因此您需要在您的项目中手动添加相关依赖。二选一：
1. tinypinyin
```xml
<dependency>
    <groupId>io.github.biezhi</groupId>
    <artifactId>TinyPinyin</artifactId>
    <version>2.0.3.RELEASE</version>
</dependency>
```
版本号仅供参考，最新版本请[查看这里](https://mvnrepository.com/artifact/io.github.biezhi/TinyPinyin)

2. pinyin4j
```xml
<dependency>
    <groupId>com.belerweb</groupId>
    <artifactId>pinyin4j</artifactId>
    <version>2.5.1</version>
</dependency>
```
版本号仅供参考，最新版本请[查看这里](https://mvnrepository.com/artifact/com.belerweb/pinyin4j)

## 常见问题
| 报错信息                                                                                     | 原因及解决方法               |
|------------------------------------------------------------------------------------------|-----------------------|
| ```java.lang.NoClassDefFoundError: com/fasterxml/jackson/core/JsonProcessingException``` | 缺少jackson依赖，手动添加即可    |
| ```java.lang.NoClassDefFoundError: com/alibaba/fastjson/TypeReference```                 | 缺少fastjson依赖，手动添加即可   |
| ```java.lang.NoClassDefFoundError: com/google/gson/reflect/TypeToken```                  | 缺少gson依赖，手动添加即可       |
| ```java.lang.NoClassDefFoundError: com/github/promeg/pinyinhelper/Pinyin```                  | 缺少tinypinyin依赖，手动添加即可 |
| ```java.lang.NoClassDefFoundError: net/sourceforge/pinyin4j/format/exception/BadHanyuPinyinOutputFormatCombination```                  | 缺少pinyin4j依赖，手动添加即可   |

## 版本历史
### v1.0.16
- 不再强依赖jackson，支持jackson/fastjson/gson三种不同的json库
- 不再强依赖tinypinyin，支持tinypinyin/pinyin4j两种不同的拼音库

### v1.0.15
- 新增支持生成虚拟借记卡号;
- 新增支持生成英文文本;
- 新增支持生成虚拟统一社会信用代码;
- 修订一些User-Agent数据;
- 优化重构随机信用卡号生成逻辑，生成的卡号符合Luhn算法校验规则;
- 升级依赖版本;

### v1.0.14
- 修订常见的地名用字，优化详细地址的随机性
- 优化数字工具类 by @changhe626
- 优化随机元素工具类 by @changhe626
- 支持生成随机百分比
- 升级依赖

### v1.0.13
- 日期时间数据源支持返回 java.util.Date 类型以方便使用
- 新增支持随机大学专业名称
- 升级依赖

## 注意事项
- 随机数据由程序随机生成，不存在真实性，但有可能与真实数据雷同（纯属巧合）。
- 程序中使用了小部分真实数据（包括但不限于：行政区划名称、邮编、区号、高校名称、股票代码及名称等）作为基础数据源，全部来自于各个合法运营的网站上的可公开访问的网页；其中涉及的著作权或商标的所有权属于各自合法的拥有者。
- 禁止利用本程序实施任何具有恶意、违反相关法律法规政策或损害他人合法权益的行为。
- 本程序基于Apache协议开源，请使用者在遵守Apache协议和相关法律法规政策的前提下合理使用；使用本程序所存在的风险以及因使用本程序而产生的一切后果由使用者自己承担，程序作者不承担任何责任。

## 感谢 JetBrains 免费的开源授权
<a href="https://www.jetbrains.com/?from=common-random" target="_blank">
<img src="https://i.loli.net/2021/06/22/Kpd28P9GwhUlrck.png" height="200"/></a>
