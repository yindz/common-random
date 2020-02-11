# 随机数据生成器
## 概述
简单易用的随机数据生成器。一般用于开发和测试阶段的数据填充模拟。可以集成到各种类型的java项目中使用。

## 优点
- 非常轻量级，容易集成，无需过多第三方依赖
- 简单方便，无需编写冗余代码
- 生成的随机数据比较接近真实数据

## 支持的随机数据类型
- 数字(int/long/double)
- 汉字(简体)
- 邮箱地址
- 中文人名(简体)
- 英文人名
- 手机号码(中国大陆)
- 省份和城市(中国大陆)
- 邮编(中国大陆)
- 联系地址(中国大陆)
- 车牌号(中国大陆，包括新能源车型)
- 域名
- 静态URL
- 日期(特定日期之前/特定日期之后)
- 强密码
- 网络昵称(登录名)
- QQ号码
- 学历
- 高校名称(数据取自教育部网站)

## 如何使用
### 配置仓库
暂时尚未进入maven中央仓库，因此请安装到本地仓库目录：
```
mvn install
```
### 引入依赖
```xml
<dependency>
    <groupId>com.apifan.common</groupId>
    <artifactId>common-random</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 基础用法
#### 获取实例(单例，线程安全)
```
RandomGenerator generator = RandomGenerator.getInstance();
```
#### 随机数字
```
//生成1个1~101(不含)之间的随机整数
int a = generator.randomInt(1, 101);

//生成8个1~101(不含)之间的随机整数
int[] b = generator.randomInt(1, 101, 8);

//生成1个10000000000~20000000001(不含)之间的随机长整数
long c = generator.randomLong(10000000000L, 20000000001L);

//生成9个10000000000~20000000001(不含)之间的随机长整数
long[] d = generator.randomLong(10000000000L, 20000000001L, 9);

//生成1个0.01~0.51(不含)之间的随机整数
double e = generator.randomDouble(0.01D, 0.51D);

//生成8个0.01~0.51(不含)之间的随机整数
double[] f = generator.randomDouble(0.01D, 0.51D, 8);
```
#### 随机汉字
```
//生成1个随机汉字
String i = generator.randomChinese();

//生成4个随机汉字
String j = generator.randomChinese(4);
```
#### 随机人名
##### 中文名
```
//生成1个随机中文人名
String k = generator.randomChineseName();
```
##### 英文名
```
//生成1个随机英文人名
String l = generator.randomEnglishName();
```
##### 生成姓名头像
- 该功能可以根据用户姓名快速生成各类网站、app的用户头像
- 背景颜色随机，数据源取自最近十几年来广受欢迎的颜色，详情参见: [Colors of the Year](https://www.w3schools.com/colors/colors_trends.asp)
- 支持使用自定义字体，但是需要使用者自行保证字体版权合法以避免纠纷
```
//姓名
String name = generator.randomChineseName();
//头像文件保存路径
String targetPath = "/home/user/picture/" + name + ".png;

//使用默认的Dialog字体
generator.generateNamePicture(name, targetPath);

//使用自定义的字体
String font = "/home/user/font/SourceHanSansCN-Normal.ttf";
generator.generateNamePicture(name, targetPath, font);
```

#### 随机中国大陆手机号
```
//生成1个随机中国大陆手机号
String m = generator.randomChineseMobile();
```
#### 随机邮箱地址
```
//生成1个随机邮箱地址，邮箱用户名最大长度为10
String n = generator.randomEmail(10);
```
#### 随机域名
```
//生成1个随机域名，域名最大长度为16
String dm = generator.randomDomain(16);
```
#### 随机静态URL
```
//生成1个随机静态URL，后缀为jpg
String url = generator.randomStaticUrl("jpg");
```
#### 随机日期
```
//生成1个2020年的随机日期，日期格式为yyyy-MM-dd
String d1 = generator.randomDate(2020, "yyyy-MM-dd");

//生成1个2020年1月2日之后的随机日期，日期格式为yyyy-MM-dd
String d2 = generator.randomFutureDate(LocalDate.of(2020,1,2), "yyyy-MM-dd");

//生成1个今天(基于系统时间判断)之后的随机日期，日期格式为yyyy-MM-dd
String d3 = generator.randomFutureDate("yyyy-MM-dd");

//生成1个2020年1月2日之前的随机日期，日期格式为yyyy-MM-dd
String d4 = generator.randomPastDate(LocalDate.of(2020,1,2), "yyyy-MM-dd");

//生成1个今天(基于系统时间判断)之前的随机日期，日期格式为yyyy-MM-dd
String d5 = generator.randomPastDate("yyyy-MM-dd");
```

#### 随机强密码
```
//生成1个随机强密码，长度为16，无特殊字符
String pwd1 = generator.randomStrongPassword(16, false);

//生成1个随机强密码，长度为16，有特殊字符
String pwd2 = generator.randomStrongPassword(16, true);
```

#### 随机地址
```
//随机获取省份
String prv = generator.randomProvince();

//随机获取城市(以逗号为分隔符)
String city = generator.randomCity(",");

//随机获取邮编
String zipCode = generator.randomZipCode();

//生成1个随机中国大陆详细地址
String addr = generator.randomAddress();
```

#### 随机中国大陆车牌号
```
//生成1个随机中国大陆车牌号(新能源车型)
String n1 = generator.randomPlateNumber(true);

//生成1个随机中国大陆车牌号(非新能源车型)
String n2 = generator.randomPlateNumber(false);
```
#### 随机网络昵称
```
//生成1个随机网络昵称，最大长度为8个字符
String nickName = generator.randomNickName(8);
```
#### 随机QQ号
```
//生成1个随机QQ号
String nickName = generator.randomQQAccount();
```
#### 随机学历
```
//随机获取学历
String degree = generator.randomDegree();

//随机获取高校名称
String college = generator.randomCollege();
```