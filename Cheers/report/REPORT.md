# 校园互动交友App——Cheers

## 成员信息

高泾洲	17343029

## 简介


经过调查发现，目前软件市场可靠的校园社交软件较少，社交类软件大多面向的是社会群体，且存在着管理不规范、人员较为杂乱、虚假信息较多等问题。另一方面，大学生群体作为各类社交软件的主要用户，普遍拥有着较强的社交愿望，但同时缺乏一个友好的平台，同一学校学生的校内社交大多只能通过微信群、QQ群和校园墙等来实现。并没有一个较为集中和完善的软件来满足学生们的社交需求。学校提供的校园应用功能大多也只局限在成绩课表查询、学校相关新闻等模块，很少有应用提供给学生一个友好的交友平台。
基于以上的问题，我决定设计一款仅面向大学校园内部的互动交友软件，提供一个安全可靠的社交平台，供校内同学之间进行交流互动。相较于面向社会群体的社交软件，该款应用仅面向校内学生，人员类型更为单一，因此无论是认证或管理起来都比较方便，同时也更为安全，出现欺诈、散播虚假消息用户的几率也会大大减小。在以社交为主要功能的同时，该软件也会同时保留学校的相关信息以及校园新闻模块，方便学生更好的了解学校。
决定App主要功能和进行了基本的构思后，首先采用了“墨刀”工具进行了应用的原型设计，绘制了App的主要界面：



![](https://img-blog.csdnimg.cn/20200702125506163.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)





![](https://img-blog.csdnimg.cn/20200702125553242.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)



以上的界面都只是初步设计时的构想，在实际操作过程中对不少部分进行了美化和优化，并且也增加了其他不少的界面，成品设计请参照演示视频。



## 开发环境

- **操作系统**：Windows
- **IDE**：Android Studio, GoLand, MySQL

## 成员分工


**高泾洲：**

- App功能构想及设计
- App UI设计
- 所有界面的代码编写
- 服务端及数据库代码编写
- App功能测试




## 重点&难点


#### 1、滑动冲突问题的解决
在进行个人主页部分编写时，为了使界面更加美观和优化用户体验，我使用了ScrollView+TabLayout+ViewPager的方式构造了页面，而该布局中“动态”子页面的Fragment包含了一个ListView。在进行调试时发现，这样的嵌套布局会发生滑动冲突问题，要么只有ListView可滑动，要么整个页面都无法滑动。为了解决这个问题，我在网上搜集到了三种解决的方法：
**1、外部拦截法**
外部拦截法需要在父容器（ScrollView）中重写onInterceptTouchEvent方法，根据具体的需要判断何时父容器需要对该事件进行拦截，若拦截，则之后由onTouchEvent处理；若不拦截，则交给子View处理。
具体到应用中，我们想实现的效果是先让ScrollView滑动一定距离，当ListView到顶部时再让ListView滑动，并且同时TabLayout也能自由地进行左右滑动。
于是，在ScrollView的onInterceptTouchEvent方法中，对左右滑动事件，我们不给予拦截，然后上下滑动事件在判断ListView到达顶部时不再进行拦截。

**2、内部拦截法**
内部拦截法和外部拦截法相反，父容器默认不拦截任何事件，而把事件的拦截判断交给子容器来处理。子View根据实际情况，若该事件需要子容器处理，则自己消耗处理，若不需要则通知父容器来进行拦截。需要注意的是，该方法也需要重写父容器onInterceptTouchEvent方法，同时，也需要确保MotionEvent.ACTION_DOWN时不拦截。

**3、重写ListView的onMeasure方法**
在onMeasure中重新计算ListView的整体高度，而不用重写父容器和子容器的代码：

```java
 @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

```
这样的方法十分简单和直接，但同时会使ListView失去复用机制，导致更新数据时卡顿。
但由于本应用中在该部分并未涉及数据的更新，因此我便使用了最后一种方法来解决了滑动冲突问题。



#### 2、网络请求数据
**使用网络请求框架：OkHttp**
在消息页面和个人主页，需要和网络接口进行通信来获取或上传数据。这里使用到了OkHttp的网络框架，同时在消息页面和个人主页分别使用了GET和POST请求。
**存在的问题：**
在安卓程序运行时会自动开启一个主线程，用于处理UI事件，我们只有在UI线程中才能进行对UI的各种操作，如果在非UI线程中直接对界面元素进行操作则会报错。在实际操作过程中，网络请求这类耗时的操作需要放到子线程中进行，不过OkHttp中的异步请求十分方便，不需要我们自己创建子线程，但是对于UI的更新仍需要在UI主线程中去处理。于是在这里我使用了Handler来实现子线程与UI线程之间的通信。
**Handler：**
通过使用Handler，我们可以实现其他线程和UI主线程之间的数据通信和消息传递。
在该消息界面，我使用了handler来接收异步GET请求传过来的消息，并对聊天列表的ListView进行加载，这一过程可分为以下几个步骤：
1、向服务端发送GET请求
2、等待返回数据，并对数据进行解析和保存
3、将数据更新到UI线程中
首先定义Handler：

```java
Handler handler = new Handler(){

    public void handleMessage(android.os.Message msg) {
        switch (msg.what) {
            case 1:
                Log.d("list", list.toString());
                MessageAdapter adapter = new MessageAdapter(getActivity(), R.layout.message_item, list);
                listView.setAdapter(adapter);
                break;
        }
    }
};
```

msg.what可以让Handler识别出该消息来自哪一个子线程，在接收到相应线程发送的消息后，会对ListView进行加载。
在子线程中，通过以下方法向Handler发送消息：

```java
android.os.Message message = new android.os.Message();
message.what = 1;
handler.sendMessage(message);
```
这样就可以实现子线程与UI线程之间的通信了。

**异步GET请求**

接着，具体对GET请求和数据解析进行分析：

```java
private void getDataFromHttp() {

    OkHttpClient okHttpClient = new OkHttpClient();
    final Request request = new Request.Builder().url(url).get().build();

    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            //Toast.makeText(getActivity(), "GET failed", Toast.LENGTH_SHORT).show();
            Log.d("Http GET", "GET FALIED");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                JSONObject json = new JSONObject(response.body().string());
                JSONArray res = json.getJSONArray("chat_list");
                //JSONArray len = json.getJSONArray("chat_num");
                for (int i = 0; i < res.length(); i ++) {
                    JSONObject val = res.getJSONObject(i);
                    String name = val.getString("ChatName");
                    int avatar = val.getInt("ImgUrl");
                    String info = val.getString("ChatInfo");
                    String time = val.getString("Time");

                    Log.d("name", name);
                    Log.d("avatar", avatar+"");
                    Log.d("info", info);
                    Log.d("time", time);

                    Message m = new Message(name, avatar, info, time);
                    list.add(m);


                }

                android.os.Message message = new android.os.Message();
                message.what = 1;
                handler.sendMessage(message);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    });


}
```


首先创建一个OkHttpClient对象，该对象通过newCall()传入Request对象获取一个Call对象，接着调用enqueue()方法通过callback形式返回Response对象。而对Response数据的解析具体放在onResponse()中进行。
对于数据的解析，采用了JSONObject格式，根据服务端定义的JSON数据格式取得想要的项，这里的”chat_list“项存放了聊天列表的具体数据，然后再根据具体的key取得相应的数据，构造Message类，再存放到list列表中，该列表与聊天列表的ListAdapter绑定，当Handler对UI线程进行通知后就可以加载对应的数据了。

**异步POST请求**
在个人主页中，我们希望用户点击右上角”+“按钮后可以把对应用户加到聊天列表中。于是这里就需要用到POST请求，将用户相关信息进行上传。异步POST请求格式如下：

```java
private void postDataToHttp() {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("user_name", "Jimmy")
                .add("chat_name", username)
                .add("img_url", String.valueOf(imgId))
                .add("chat_info", "cheers bro!")
                .add("time", "10:00")
                .add("chat_id", String.valueOf((listLength + 1)))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("POST FAILED", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Response", "onResponse: " + response.body().string());
            }
        });
    }

}
```
POST请求的总体结构与GET请求相差不大，主要区别在Request.Bulider的post()方法，需要一个RequestBody，而这个对象就包括了我们需要上传的参数。RequestBody通过FormBody.Bulider()进行实例化，并通过add()添加我们需要上传的键值对。
另外，由于在数据库中我们需要chat_id作为主键，因此该元素数值不能重复，这里采用的构造方法是先进行一次GET请求获取到chat_list的长度，然后每次递增1作为该项的chat_id。



#### 3、“动态”页面的点赞、评论操作
对于该页面动态的点赞和评论操作，我们希望用户在进行点赞或评论后可以动态地添加视图：用户在点赞后，会添加一个提示点赞成功的TextVIew，若取消点赞，则该TextView也会相应消失；用户在评论后，会将评论内容以TextView的形式添加到视图中。
由于”动态“页面主体是一个ListView，因此这些操作应该在对应的Adapter中进行。
首先，对于点赞TextView的添加，采用如下方法：

```java
private void addLikeView(ViewHolder holder){
    LikeView = new TextView(mContext);
    //construct layout
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
    lp.setMargins(0,15,0,15);
    LikeView.setTextSize(14);
    LikeView.setText("Jimmy 点赞了");
    //add layout
    holder.mContainer.addView(LikeView,3,lp);
}
```
动态加载页面主要就是需要以代码的方式来构造布局，先构造一个LinearLayout和TextView，再通过addView()方法进行添加即可。该函数第二个参数是添加的位置，也就是该视图在父容器中的索引，该页面的动态item布局如下：




![](https://img-blog.csdnimg.cn/20200702165532558.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)




我们希望把点赞的TextView加载图片下方，因此可以得出index=3.
对于对应的移除操作，则直接调用removeViewAt(3)即可。
评论的添加也大同小异，我们希望将TextView加载评论区域上方，对于索引位置的确定，由于点赞的TextView会影响索引的数值，因此这里采用先获取子View总数，然后把View总数-1作为评论TextView的索引位置：

```java
private void addView(ViewHolder holder, String string) {
    TextView textView = new TextView(mContext);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 60);
    layoutParams.setMargins(0,15,0,15);
    textView.setTextSize(14);
    textView.setText("Jimmy:"+string);
    int index = holder.mContainer.getChildCount();
    holder.mContainer.addView(textView,index - 1,layoutParams);

}
```




## 功能信息

（确定APP的所需要实现的功能，此内容将作为检查APP是否功能完善的重要依据）

- 实现页面：欢迎页面、学校页面（同学、学校子页面）、动态页面、信息页面（聊天子页面）、个人页面（我的学习、个人主页、标签、设置子页面）、个人主页页面（信息、动态子页面）
- 同学子页面和动态页面中点击用户跳转到对应个人主页操作
- 聊天子页面实现信息发送和自动回复操作
- 动态页面进行点赞、取消点赞以及评论操作
- 信息页面聊天列表数据的数据库存储
- 服务端和数据库端聊天列表部分获取和添加功能的实现
- 个人主页点击添加按钮将用户添加至信息界面聊天列表功能的实现


## 实现方法

（下面内容属于第三次考核，依个人写作习惯和项目情况，自己组织语言和结构。需要包括**需求分析（用例图），设计（类图，流程图）等**，完整展示APP的实现方法。此内容将作为提问环节的重要依据。）

**需求分析（用例图）**




![](https://img-blog.csdnimg.cn/20200702175300529.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)




**设计（类图、流程图）**

类图





![](https://img-blog.csdnimg.cn/20200703132528577.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)





流程图






![](https://img-blog.csdnimg.cn/20200703142242448.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)






**项目结构**
java：

```
├───Activity
│   │   ChatActivity.java
│   │   MainActivity.java
│   │   SplashActivity.java
│   │   
│   └───PersonActivity
│           MyLabelActivity.java
│           MySchoolActivity.java
│           MySettingActivity.java
│           PersonalPageActivity.java
│           
├───Adapter
│       ActionAdapter.java
│       ChatAdapter.java
│       ImageAdapter.java
│       MessageAdapter.java
│       NewsAdapter.java
│       PersonalFragmentPagerAdapter.java
│       SchoolFragmentPagerAdapter.java
│       StudentInfoAdapter.java
│       TextTagAdapter.java
│       
├───Fragment
│   │   ActionFragment.java
│   │   MessageFragment.java
│   │   PersonFragment.java
│   │   SchoolFragment.java
│   │   
│   └───ChildFragment
│           CampusFragment.java
│           PersonalActionFragment.java
│           PersonalStatusFragment.java
│           StudentListFragment.java
│           
├───Infos
│       ActionInfo.java
│       ChatInfo.java
│       Message.java
│       News.java
│       StudentInfo.java
│       
└───MyView
        FlowLayout.java
        MyScrollView.java
        MyViewPager.java
        ScrollListView.java
```
Layout：

```
    action_item.xml
    activity_chat.xml
    activity_main.xml
    activity_my_label.xml
    activity_my_school.xml
    activity_my_setting.xml
    activity_personal_page.xml
    activity_splash.xml
    fragment_action.xml
    fragment_campus.xml
    fragment_message.xml
    fragment_person.xml
    fragment_personal_action.xml
    fragment_personal_status.xml
    fragment_school.xml
    fragment_student.xml
    left_chat_item.xml
    message_item.xml
    news_item.xml
    right_chat_item.xml
    student_info_item.xml
```
服务端：

```
│   main.go
│   
├───.idea
│       cheersServer.iml
│       misc.xml
│       modules.xml
│       workspace.xml
│       
├───dal
│   ├───db
│   │       db.go
│   │       
│   └───model
│           chat_list.go
│           
└───views
        add_chat_list.go
        get_chat_list.go
```


**主要模块分析**

**MainActivity**
构成：Fragments + BottomNavigationBar

MainActivity是整个应用的主体，它的结构也决定了其他页面大部分都将以Fragment的形式来进行构造。对于底部的导航栏，可通过addItem来添加图标，并且通过重写onTabSelected来实现不同Fragment之间的切换：

```java
@Override
public void onTabSelected(int position) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    switch(position){
        case 0:
            switchFragment(schoolFragment);
            break;
        case 1:
            switchFragment(actionFragment);
            break;
        case 2:
            switchFragment(messageFragment);
            break;
        case 3:
            switchFragment(personFragment);
            break;
        default:
            switchFragment(schoolFragment);
            break;
    }
    transaction.commit();
}
```

fragment切换的具体实现要通过FragmentManager来实现，若目标fragment还未被加入视图中，则隐藏当前fragment，并将目标fragment通过FragmentManager加入视图中；若目标fragment已显示，则无需进行添加：

```java
private void switchFragment(Fragment fragment) {

    if(mFragment != fragment) {
        if (!fragment.isAdded()) { //if the fragment is not added
            getSupportFragmentManager().beginTransaction().hide(mFragment) //hide current fragment
                    .add(R.id.fragment_container,fragment).commit(); //add next fragment
        } else { //if added
            getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
        }
        mFragment = fragment;
    }
}
```


**学校页面**
构成：TabLayout + ViewPager

该页面包括两个子页面：**同学页面**和**学校页面**。利用了顶部的两个Tabs和包含fragment的ViewPager来实现了两个子页面之间的左右切换。这里要为ViewPager定义一个FragmentPagerAdapter来绑定和管理页面数据：

```java
public class SchoolFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"同学", "学校"};

    public SchoolFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return new CampusFragment();
        }
        return new StudentListFragment();

    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
```
getItem()通过判断当前位置来决定加载哪一个子页面，getPageTitle()绑定了每个子页面的Tab标题。

在主体的SchoolFragment中，需要先绑定fragment与ViewPager，再绑定ViewPager与TabLayout：

```java
//bind viewpager with fragment
mViewPager= (ViewPager) view.findViewById(R.id.viewPager);
myFragmentPagerAdapter = new SchoolFragmentPagerAdapter(getChildFragmentManager());
mViewPager.setAdapter(myFragmentPagerAdapter);

//bind viewpager with tablayout
mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
mTabLayout.setupWithViewPager(mViewPager);
```
需要注意的是，由于该页面属于Fragment嵌套Fragment的结构，所以在获取子页面的FragmentManager时要使用getChildFragmentManager()方法。

**同学子页面**
构成：MyScrollView + ScrollingImageView + ImageView + ScrollListView

在写该页面时，一开始只写了一个ListView，但发现这样过于单调，于是为了使界面更加美观，在页面顶部加了一个背景可循环滚动的图片：




![](https://img-blog.csdnimg.cn/20200703150500550.png)





其实就是一个可以循环滚动的ScrollingImageView叠加上一个静止的ImageView，实现很简单，但效果还不错。

该页面主体是一个ScrollView，然后用之前介绍过的解决ScrollView嵌套ListView时产生滑动冲突问题的方法重写了一个ScrollListView。
该应用中多次运用到了ListView，而它们的构造方法都一致，所以在这里统一进行称述，之后涉及到ListView的地方就不再做赘述了。基本方法都是底层数据类+ListAdapter+实例化ListView并绑定Adapter；布局文件的话都是主页面中一个ListView+单独的List_item。
底层的数据类根据item中涉及到的元素具体进行设计，并且要提供构造函数和各个变量的get方法，以便在Adapter中进行调用。该页面中用到的底层数据类为StudentInfo，定义了以下5个变量并且实现了构造函数和各自的get方法：

```java
private String name; //name
private int img_id; //portrait
private int age; // age
private int sex_id; // gender
private int distance; //diastance
```
接下来就是要在Adapter中管理具体的数据和list_item中的组件了。主要的实现是通过重写getView()方法来实现。在大部分ListAdapter中，我都构造了一个ViewHolder来统一管理组件。这样的优势是可以让ListView的滚动更加顺畅，并且不用每次都重新创建对象，提高了ListView的性能。通过将convertView的tag设置为ViewHolder，在加载ListView时，会先判断convertView是否为空，若为空则加载组件，不为空则直接重用之前的组件：

```java
if (convertView == null) {
    view= LayoutInflater.from(getContext()).inflate(resourceId, null);
    viewHolder = new ViewHolder();
    viewHolder.img = (ImageView) view.findViewById(R.id.img);
    viewHolder.name = (TextView) view.findViewById(R.id.name);
    viewHolder.age = (TextView) view.findViewById(R.id.age);
    viewHolder.sex = (ImageView) view.findViewById(R.id.sex);
    viewHolder.distance = (TextView) view.findViewById(R.id.distance);
    view.setTag(viewHolder);
}
else {
    view = convertView;
    viewHolder = (ViewHolder) convertView.getTag();
}
```
这之后，再通过调用数据类中各项的get方法为viewHolder中各个组件设置具体的值即可：

```java
viewHolder.img.setImageResource(studentInfo.getImg_id());
viewHolder.name.setText(studentInfo.getName());
viewHolder.age.setText(studentInfo.getAge() + " years old");
viewHolder.sex.setImageResource(studentInfo.getSex());
viewHolder.distance.setText(studentInfo.getDistance() + "m");
```
接着，在fragment或者activity中，实例化ListView，并设置对应adapter即可：

```java
ScrollListView listView = (ScrollListView) view.findViewById(R.id.stu_list);
StudentInfoAdapter adapter = new StudentInfoAdapter(getActivity(), R.layout.student_info_item, list);
listView.setAdapter(adapter);
```
最后，由于adapter需要一个list来进行具体数据的绑定，所以还需要写一个list的初始化函数，并在一开始调用。若要对各个变量进行数据库的储存，则最好每个变量都定义一个list来统一储存，由于这里没有进行后台的编写，所以就直接在构造数据类时定义变量了：

```java
private void Studentinit() {

    StudentInfo s1 = new StudentInfo("小明", R.drawable.img1, 20, R.drawable.ic_male, 100);
    list.add(s1);
    StudentInfo s2 = new StudentInfo("小王", R.drawable.img2, 21, R.drawable.ic_male, 200);
    list.add(s2);
    ...
```

此外，这里还对list条目做了点击事件的响应，会跳转到对应同学的个人页面。这里需要进行参数的传递。传递的方式很简单，在点击时通过bundle传入对应的键值对：

```java
//on item click event (jump to personal page)
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StudentInfo studentInfo = list.get(position);
        Bundle bundle = new Bundle();

        bundle.putString("name", studentInfo.getName());
        bundle.putInt("age", studentInfo.getAge());
        bundle.putInt("avatar", studentInfo.getImg_id());
        bundle.putInt("sex", studentInfo.getSex());

        Intent intent = new Intent(getActivity(), PersonalPageActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
});
```
然后在个人主页页面接收相应的参数即可：

```java
// receive parameters
Bundle bundle = getIntent().getExtras();

username = bundle.getString("name");
imgId = bundle.getInt("avatar"); 
...

```
在动态页面和个人页面的跳转个人主页的写法也与这里相同，只不过在动态页面我选择的是点击用户头像进行跳转，而不是list的条目，因此，具体的点击相应应该在Adapter中去书写：

```java
//click avatar
holder.actionAvatar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ActionInfo actionInfo = aData.get(position);
        Bundle bundle = new Bundle();
        ...
```

**学校子页面**
构造：LinearLayout + VideoView + ImageView + ListView
该页面构造较为简单，主要是VideoView的部分，用一个按钮来让视频播放，然后用mediaController来具体地控制视频。

**动态页面**
构造：ListView

该页面主体就是一个listView，主要重点在于List_item的布局以及点赞和评论操作，这两点在之前“重点和难点”中已经进行了陈述，故这里不再重复。这里给出底层数据类包含的变量：

```java
private int avatarId;
private String username;
private String date;
private int actionImageId;
private String actionDescripation;
private String position;
private int age;
private int sex;
```

**消息页面**
构造：ListView

该页面同样是由一个ListView组成，其item布局与同学列表的相似。点击条目后会进入聊天界面（ChatActivity），这里同样需要传递用户名和头像的参数。
此外，之前在“重点和难点”中讲过个人主页中有一个将用户添加至聊天列表中的操作。在编写时一开始发现若进行多次操作，会发现返回该页面时列表没有得到及时的更新。这个问题与Fragment的生命周期有关，之前在MainActivity中Fragment切换时会隐藏当前的fragment，再次加载时再对fragment进行显示。由于一开始向网络接口获取数据的操作只写在onCreateView()函数中，因此当我们添加后再切回该页面的话就不会被进行调用，聊天列表也自然不会更新。解决该问题的方法是在onHiddenChanged()中再次向网络接口请求一次数据，这样每次切回该页面时都会对数据进行刷新：

```java
@Override
public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!hidden) {
        list.clear();
        getDataFromHttp();
    }
}
```
不过这样的方法也不是很好，网络请求毕竟是一项相对耗时的操作，因此在之后的改进中可以考虑通过别的方法进行优化。

**聊天页面**
构造：ListView + EditText + Button

该页面ListView的构造要稍微不同于其他的。由于一般的聊天界面可以分为自己发送的消息和对方发送的消息，并且它们各自的位置和气泡颜色都不相同，因此在构建list_item时需要对它们分别进行构造，这里我分别构建了right_chat_item和left_chat_item，而它们之间的区分通过底层数据类的一个isSend变量来实现：

```java
private int id; //chat id
private String name; //name
private String chatMessage; //send message
private boolean isSend; //id send by user
```
在Adapter的getView()中，通过对isSend的判断来决定当前要构造的是自己的item还是对方的item：

```java
if (isMeSend) {
    arg1 = View.inflate(mContext, R.layout.right_chat_item, null);
    holderView.left_message = (TextView) arg1.findViewById(R.id.chat_me_message);
    holderView.left_message.setText(entity.getChatMessage());
} else {
    arg1 = View.inflate(mContext, R.layout.left_chat_item, null);
    holderView.avatar_left = (CircleImageView) arg1.findViewById(R.id.chat_avatar_left);
    holderView.avatar_left.setImageResource(mContext.getAvatarData());
    holderView.right_message = (TextView) arg1.findViewById(R.id.chat_left_message);
    holderView.right_message.setText(entity.getChatMessage());

}
```
在Activity文件中，需要实现点击按钮后将editText中内容发送的功能，需要在按钮的onClick()函数中进行如下的书写：

```java
ChatInfo personChat = new ChatInfo();
//sent by user
personChat.setSend(true);
personChat.setChatMessage(chat_message.getText().toString());
//add to list
personChats.add(personChat);
//clear edit text
chat_message.setText("");

//update listview
chatAdapter.notifyDataSetChanged();
handler.sendEmptyMessage(1);
```
构造一个数据类，将isSend设为true，然后获取EditText中的内容并通过数据类中的set方法将发送内容进行设置，之后将数据类加入列表中，并清空EditText，最后通过notifyDataSetChanged()方法通知Adapter进行更新，并用一个handler来保证每次更新的item都添加在ListView的尾部。
这里我还设置了简单的自动回复功能，方法与上面基本一致，只需将isSend设为false，接下就交给Adapter来处理：

```java
//auto respond
ChatInfo personChat2 = new ChatInfo();
personChat2.setSend(false);
...
```


**个人页面**
构造：ImageView + RelativeLayout

该页面为了美观一些，使用了Glide图片框架，效果如下：




![](https://img-blog.csdnimg.cn/20200703164214362.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)




分别对图像做了圆形处理和模糊处理。glide的使用也十分方便，而且效果很不错：

```java
//blur image
Glide.with(getActivity())
        .load(R.drawable.head)
        .bitmapTransform(new BlurTransformation(getActivity(), 10), new CenterCrop(getActivity()))
        .into(blurImageView);
//circle image
Glide.with(getActivity())
        .load(R.drawable.head)
        .bitmapTransform(new CropCircleTransformation(getActivity()))
        .into(avatarImageView);
```

接下来就是点击各项跳转到相应的页面了，这些页面分别是：**我的学校页面**、**个人主页页面**、**我的标签页面**和**设置页面**。
除了个人主页页面，其他页面的布局都比较简单，并且没有做太多具体功能的实现，因此在这里就不一一进行介绍了。唯一需要提的就是在“我的标签页面”中，使用到了FlowLayout实现了一个较为美观的流式布局。


**个人主页页面**
构造：ScrollView + ImageView + TabLayout + ViewPager

该页面上半部分的构造和个人页面是一样的，同样也是使用glide对图片进行了美观。下半部分是tabLayout和viewPager。包含两个子页面：**个人信息页面**和**个人动态页面**。个人动态页面和动态页面使用的是相同的结构，而会产生的滑动冲突在之前也进行了分析，因此在这里不多加叙述。

**个人信息页面**
构造：LinearLayout + GridView + TagCloudView

该页面的重点是一个GridView布局的相册概览和一个TagCloudView布局的爱好标签。
GridView需要用到一个ImageAdapter来进行管理。其基本结构也和之前的ListAdapter相似，这里定义了一个数组来存放需要加载的图片，然后在getView()中进行具体的构造：

```java
    //image resource
    private Integer[] mThumbIds = {

            R.drawable.img1, R.drawable.img2,
            R.drawable.img3,R.drawable.img4,
            R.drawable.img5,R.drawable.img1,
    };
}
```

```java
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub

    ImageView imageview;
    if(convertView == null)
    {
        imageview = new ImageView(mContext);
        imageview.setLayoutParams(new GridView.LayoutParams(350, 350));
        imageview.setPadding(1,1,1,1);
    }
    else
    {
        imageview = (ImageView) convertView;
    }
    imageview.setImageResource(mThumbIds[position]);
    return imageview;
}
```
另外，该页面有一个效果十分炫酷的爱好标签，效果如下：




![](https://img-blog.csdnimg.cn/20200703172911194.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)




它的实现通过一个第三方的TagCloudView组件。该组件同样需要一个Adapter，在getView()中进行标签的定义以及每一项tag的TextView的构造：

```java
public View getView(final Context context, final int position, ViewGroup parent) {
    String[] name = {"电影", "小说", "摇滚", "抽象", "cosplay", "篮球", "动漫", "海贼王", "发呆", "美食", "旅行"};
    Random rand = new Random();
    int randNum = rand.nextInt(9);
    TextView tv = new TextView(context);
    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(100, 100);
    tv.setLayoutParams(lp);
    tv.setText(name[randNum]);
    tv.setGravity(Gravity.CENTER);
    tv.setTypeface(Typeface.defaultFromStyle((Typeface.BOLD)));
    return tv;
}
```
需要做到tag的字体颜色有一个渐变的效果，在xml中进行如下定义：

```java
app:darkColor="@color/cornflowerblue"
app:lightColor="@color/lightcoral"
```

**服务端及数据库**
**数据库**
数据库端聊天列表table创建如下：





![](https://img-blog.csdnimg.cn/20200703202800936.png)




table具体条目如下：





![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703202850283.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)






**服务端**
该应用服务端现在只搭建了聊天列表部分，提供了GET和POST的相应接口。对于其他的数据，例如同学信息、动态的feed流、学校基本信息等，其服务端编写方法也和这个大致相同，大体结构上都是一致的。由于时间的关系这次只搭建了聊天列表部分，之后若有时间会努力对后台部分进行完善。


model层数据结构ChatList的构建：

```java
type ChatList struct {
   UserName string `gorm:"column:user_name"`
   ChatName string `gorm:"column:chat_name"`
   ImgUrl int `gorm:"column:img_url"`
   ChatInfo string `gorm:"column:chat_info"`
   Time string `gorm:"column:time"`
   ChatId int `gorm:"column:chat_id"`
}

func (ChatList) TableName() string {
   return "chatList"
}
```
对应着客户端聊天列表的元素，除此之外，还加入了当前登录用户名和chat_id项，这是为了方便数据库的构建。

db层获取聊天列表：

```java
func GetChatList(username string) ([]*model.ChatList, int, error) {
   db, err := gorm.Open("mysql", "root:1234@/userdb?charset=utf8&parseTime=True&loc=Local")
   if err != nil {
      return nil, 0, errors.New("connect to database failed.")
   }
   defer db.Close()
   chat_list := make([]*model.ChatList, 0)

   db.Where("user_name = ?", username).Find(&chat_list)

   return chat_list, len(chat_list), nil
}
```
利用gorm框架与数据库进行连接，然后根据当前用户的所有聊天列表记录，返回值有3个：聊天列表记录，列表长度和错误信息。列表长度主要是为了在客户端添加聊天时方便对chat_id进行赋值。


添加聊天列表：

```java
func AddChatLilst(username, chatname string, imgurl int, chatinfo, time string, chatid int) error {

...
 new_chat := &model.ChatList{UserName:username, ChatName:chatname,  ImgUrl:imgurl, ChatInfo:chatinfo, Time:time, ChatId:chatid}
   db.Create(&new_chat)
   return nil
}
```
之前的操作都与Get方法相同，添加的方法是根据对于model层数据结构来进行新的聊天列表项构造。构造完成后将其加入数据库。


views层获取聊天列表：

```java
func GetChatList(c *gin.Context) {
   user_name := c.DefaultQuery("user_name", "")

   chats, num, err := db.GetChatList(user_name)
   if err != nil {
      c.JSON(200, gin.H{
         "message":       "error",
         "error_message": err.Error(),
      })
      return
   }

   res := gin.H{
      "message":       "success",
      "error_message": "",
   }
   res["chat_list"] = chats
   res["chat_num"] = num
   c.JSON(200, res)
}
```
在该层的操作应该定义JSON的格式，可以看到成功返回的JSON格式可分为四项：操作结果、错误信息、聊天列表及聊天列表长度。在客户端进行GET网络请求时应该根据该格式对数据进行解析。


添加聊天列表：

```java
func AddChatList(c *gin.Context) {
   username := c.PostForm("user_name")
   chatname := c.PostForm("chat_name")
   imgurlstr := c.PostForm("img_url")
   chatinfo := c.PostForm("chat_info")
   time := c.PostForm("time")
   chatidstr := c.PostForm("chat_id")

   imgurl, err := strconv.ParseInt(imgurlstr, 10, 64)
   chatid, err := strconv.ParseInt(chatidstr, 10, 64)
   if err != nil {
      c.JSON(200, gin.H{
         "message":       "error",
         "error_message": "invalid friendid.",
      })
      return
   }


   err1 := db.AddChatLilst(username, chatname, int(imgurl), chatinfo, time , int(chatid))

   if err1 != nil {
      c.JSON(200, gin.H{
         "message":       "error",
         "error_message": err1.Error(),
      })
      return
   }

   res := gin.H{
      "message":       "success",
      "error_message": "",
   }
   c.JSON(200, res)
}
```
在接收到相应参数后会调用数据库层的相关方法进行记录的添加。操作成功的返回JSON数据包括操作结果及错误信息两项。


main函数定义网络接口：

```java
r.POST("/chat_list", views.AddChatList)
r.GET("/chat_list", views.GetChatList)


r.Run(":8081")
```

编写完成后运行main.go文件，然后查看对应的网络接口，可得到如下结果：





![](https://img-blog.csdnimg.cn/20200703202459709.png)





在服务端进行网络请求时，可看到服务端有相应的记录：






![](https://img-blog.csdnimg.cn/20200703202553984.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3N0amltbXkyMzI0,size_16,color_FFFFFF,t_70)




以上就是该应用所有主要模块的具体分析。后续才存在着许多改进的地方，例如完善后台服务，增加用户登录注册功能，增加动态的编写和发布功能，实现按学校来筛选同学等。希望在以后的应用开发中，我可以做出功能更加完善、界面更加美观、交互更加友好的软件。
