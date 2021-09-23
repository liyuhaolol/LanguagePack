# 本框架可以实现不重启App，包括不startActivity或者recreate来实现App语言的国家化切换。

## 实现方法为修改语言后，广播通知到具体界面实现语言切换逻辑。

## 1.0.2更新

- 修改广播名称，避免出现错误接收，demo改为解耦模式

## 1.0.1更新

- 修复一个因为比对语言造成项目不能正常切换语言的问题

## 框架引用方法

- 在gradle中:
```gradle
    implementation 'spa.lyh.cn:languagepack:1.0.2'
```

## 具体暴露的方法请去看LanguagePack类，有具体注解

## 国际化的通用实现集成方法

### 1，在项目中的assets文件中添加一个名为：Locales.xml的文件

### 文件结构为

```xml
<languages>
    <item name="simplified_chinese" language="zh" country="CN">中文(简体)</item>
    <item name="traditional_chinese" language="zh" country="TW">中文(繁體)</item>
    <item name="english" language="en">English</item>
    <item name="russian" language="ru">русский язык</item>
    <item name="japanese" language="ja">日本語</item>
    <item name="korean" language="ko">한국어.</item>
</languages>
```
### name为语种键，language为语言，country为国家或地区，已经对应语言的翻译

### 在对应的需要显示选择语言的界面上通过方法获取对应的List<LanguageInfo>语言列表数据

### ```List<LanguageInfo> list = LanguagesPack.getLanguageList(this);```

### 选择对应的语言后，通过对应的方法，切换对应的App语言。

### ```LanguagesPack.setLanguage(contenxt,languageInfo);```

### 具体参考LocalActivity类

### 2，具体界面集成逻辑

#### 什么是存在可能改变语言的界面？解释：就是当触发语言改变时，在栈中可能存活的的界面才需要做对应的适配。

## 2.1Application中加入init的方法和attachBaseContext的重写

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguagesPack.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(base));
    }
}
```

## 2.2所有的Activity需要加入attachBaseContext的重写，推荐放到Base里
```java
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(newBase));
    }
}
```

## 2.3可能会动态改变语言的Activity和Fragment实现LanguageReceiver.Message的方法
## 可能会动态改变语言的Acitiviy和Fragment进行LanguageReceiver的注册和注销
## 当语种发生改变时，广播会通知到onLanguageChange()方法，在这个方法中进行具体的语言切换

```java
public class MainActivity extends AppCompatActivity implements LanguageReceiver.Message {
    LanguageReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            LanguageReceiver.unregister(this,receiver);//注销广播
        }

    }

    private void initView(){
        receiver = new LanguageReceiver(this);//初始化广播
        LanguageReceiver.register(this,receiver);//注册广播到当前位置
    }

    private void loadData(){
        //加载对应的文本或图片资源
    }



    @Override
    public void onLanguageChange() {
        loadData();
    }
}
```
## 如果页面不会动态切换文本和图片等资源，则不需要进行2.3的操作，也不需要使用工具来获取文本和图片资源

## 3，动态获得资源的方法

### 3.1获取当前选择语言的文本资源

### ```LanguagesPack.getString(context,id)```

### 3.2获取当前选择语言的Resources对象

### ```LanguagesPack.getResources(context)```

# 注意！当前方法一般使用在会动态切换语言的界面，只走初始化的界面，可以直接使用系统自带的方法。国际化依然生效。

# 具体LanguagesPack还有很多方法，这里不具体赘述了，请去直接看那个类，有详细的注释。
