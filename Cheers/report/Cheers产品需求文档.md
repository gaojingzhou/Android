# 产品需求文档

@[toc]
| 文件名称   | Cheers需求说明书 |
|--------|------------|
| 编写人   | 高泾洲           |
| 版    本 | V1.0       |

## 一、产品介绍
### 1. 产品定位
Cheers是一款仅面向大学校园内部的互动交友软件，提供一个安全可靠的社交平台，供校内同学之间进行交流互动，同时也包含一些校园服务和信息咨询。

### 2. 产品综述
| 产品名称 | Cheers |
|--|--|
| 产品定位 |校园社交软件  |
| 产品标语 |  认识擦肩而过的你|
| 特色功能 | - 发现附近同学 <br>- 校内同学动态<br>- 实时聊天交友<br>- 校园信息咨询<br>- 树洞和提问板块 |
| 目标用户 | 社交愿望较强且希望拥有良好交友环境的的在校大学生（18-25岁） |




## 二、需求概述
### 1. 需求分析





![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017145505572.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)



（数据来源：QuestMobile）
截止2019年4月，在校大学生人均App使用数量达到27.1个，同比去年上升1.9个；其中移动社交软件的人均使用数量达3个，仅次于移动视频和系统工具App。
上述数据说明了当代在校大学生对App软件的需求和依赖在不断地增加，并且社交软件已经成为他们手机上必不可少的应用。




![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017150055878.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)





（数据来源：QuestMobile）
截止2019年4月，在校大学生在移动社交类App的平均使用时长达近3000分钟，远超其他行业的人均使用时长。
该数据反映了大学生群体的社交愿望普遍比较强烈，会花大量的时间在聊天交友上。

通过上述数据可以看到，在校大学生普遍拥有着较为强烈的社交愿望，在该类软件上花费的时间也比较多。
另一方面，目前软件市场可靠的校园社交软件较少，社交类软件大多面向的是社会群体（探探、陌陌、积目等），且存在着管理不规范、人员较为杂乱、虚假信息较多等问题。
目前很多大学都会推出自己的校园应用，但功能大多也只局限在成绩课表查询、学校相关新闻等模块，很少有应用提供给学生一个友好的交友平台。

基于以上的问题，我决定设计一款仅面向大学校园内部的互动交友软件，提供一个安全可靠的社交平台，供校内同学之间进行交流互动。相较于面向社会群体的社交软件，该款应用仅面向校内学生，用户类型更为单一，因此无论是认证或管理起来都比较方便，同时也更为安全，出现欺诈、散播虚假消息用户的几率也会大大减小。在以社交为主要功能的同时，该软件也会同时保留学校的相关信息以及校园新闻模块，方便学生更好的了解学校。

### 2. 需求汇总
| 使用场景 | 问题需求|
| ---- | ---- | 
|   觉得其他社交软件用户较杂，希望拥有一个相对小范围、更纯净交友环境   | 需要一个固定范围、有认证机制的交友平台     |      
|  结识本校同学，扩大自己的交际圈    |  需要一个按学校来划分的交友平台    |      
|  与陌生的本校同学聊天交友    |    能够与他人实时聊天通讯、查看动态及喜好  |      
|   想要查看学校的一些即时资讯   |   需要自己学校的新闻和动态列表   |      
|   遇到困扰，想要匿名倾诉   | 通过匿名的“树洞”来与其他同学进行交流     |    
|在学习或生活上遇到问题 | 需要向其他人提问，并获得解决的办法或建议 |





## 三、产品说明
### 1. 产品功能结构图







![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017170545358.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)









### 2. 产品信息结构图







![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017192909824.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)







## 3. 总体流程图







![在这里插入图片描述](https://img-blog.csdnimg.cn/20201017193203440.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)








## 四、产品功能明细
### 1. 功能列表


|序号| 模块 |功能|需求说明|
|--|--|--|--|
| 1 | 同学模块 |1、查看附近同学<br>2、树洞功能<br>3、提问功能<br>4、测试功能  | 1、发现周围的同学，并可查看他们的个人主页<br>2、使用树洞进行匿名倾诉、吐槽等<br>3、使用提问功能对生活、学习问题进行提问，获得他人建议<br>4、使用测试功能进行性格测试，发现与你相似的同学 |
| 2 | 学校模块 | 1、查看学校基本信息<br>2、查看校园概览<br>3、浏览校园新闻 |1、展示用户学校的基本概况 <br>2、校园新闻包括了最新资讯、相关通知等 |
|3  | 动态模块 | 1、浏览同学动态，进行一系列相关操作<br>2、发布自己的动态 |1、可对动态进行点赞、评论和分享操作<br>2、点击用户头像可查看个人主页<br>3、发布的动态可以是文字、图片或视频  |
|  4| 信息模块 | 与好友进行聊天 | 可像其他即时通讯软件一样与好友进行聊天 |
| 5 | 个人信息模块 |1、查看基本信息（头像、用户名等）<br>2、查看和编辑学校信息<br>3、查看个人主页<br>3、查看和编辑爱好标签<br>4、进行设置  | 1、在学校设置中可加入认证模块<br>2、设置模块包括软件设置、偏好设置以及其他设置 |
| 6 | 个人主页模块 | 1、浏览用户基本信息<br>2、查看相册<br>3、查看爱好标签<br>4、查看动态<br>5、添加好友 |1、动态和其他信息分别在两个子页面进行展示<br> 2、爱好标签以球形浮动组件的方式来呈现 |

### 2. 产品界面逻辑图





![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018164702964.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)








### 3. 原型界面
#### 学校界面





![在这里插入图片描述](https://img-blog.csdnimg.cn/2020101822351858.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)








#### 动态界面







![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018223619603.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)









#### 消息界面






![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018223632152.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)










#### 个人界面






![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018223650944.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)








#### 个人主页界面







![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018223704852.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)









### 4. 流程图
#### 登录注册流程图







![在这里插入图片描述](https://img-blog.csdnimg.cn/2020101823220636.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)









#### 添加好友流程图









![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018233015466.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)










### 5. 用例图







![在这里插入图片描述](https://img-blog.csdnimg.cn/20201018234104921.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70#pic_center)









## 五. 总结
Cheers作为一款仅面向大学校园的交友软件，为那些对目前市场社交软件可靠性和真实性抱有疑问，同时又渴望结交同龄好友的在校大学生提供了一个安全和友好的平台，不仅满足了他们的社交需求，同时还提供了所在学校的相关讯息和即时资讯，让用户在结交校内好友的同时，也对自己的学校有一个更加深刻和全面的了解。
由于该项目从规划、设计和开发都只由我一个人负责，时间与精力都有限，因此在功能的实现上可能不能完成所有设想的功能，如登录注册模块、用户学校认证模块等可能会将其简化或省略，底层数据库也可能从简设计。但今后若有时间，将会不断对软件进行完善，不断地学习改进之前的不足之处。
