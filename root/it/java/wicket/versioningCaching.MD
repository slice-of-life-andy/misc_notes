Wicket pages can be divided into two categories: stateful and stateless pages.Stateful pages are those which rely on user session to store their internal state and to keep track of user interaction.
From Wicket's point of view the biggest difference between these two types of page is that stateful pages are versioned, meaning that they will be saved into user session every time their internal state has changed. Wicket automatically assigns a session to the user the first time a stateful page is requested. Page versions are stored into user session using Java Serialization mechanism.
Wicket creates a new page version only if the page is modified before its method onBeforeRender() is invoked. 

---
>Wicket pages 有stateful和stateless两种状态，它们最大的不同是stateful的page有version,version信息通过java序列化机制存储到用户session里
只有在调用Page的onBeforeRender()方法前修改了page才会创建一个新的version
---

- Using a specific page version with PageReference
```java
//load page version with page id = 3
PageReference pageReference = new PageReference(3);
//load the related page instance
Page page = pageReference.getPage();
```

-Turning off page versioning
```java
Component.setVersioned(false)
```
