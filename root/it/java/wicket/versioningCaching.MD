Wicket pages can be divided into two categories: stateful and stateless pages.Stateful pages are those which rely on user session to store their internal state and to keep track of user interaction.

From Wicket's point of view the biggest difference between these two types of page is that stateful pages are versioned, meaning that they will be saved into user session every time their internal state has changed. Wicket automatically assigns a session to the user the first time a stateful page is requested. Page versions are stored into user session using Java Serialization mechanism.

Wicket creates a new page version only if the page is modified before its method onBeforeRender() is invoked. 

---

>
Wicket pages 有stateful和stateless两种状态，它们最大的不同是stateful的page有version,version信息通过java序列化机制存储到用户session里
只有在调用Page的onBeforeRender()方法前修改了page才会创建一个新的version

---

- Using a specific page version with PageReference
```java
//load page version with page id = 3
PageReference pageReference = new PageReference(3);
//load the related page instance
Page page = pageReference.getPage();
```

- Turning off page versioning
```java
Component.setVersioned(false)
```

- Pluggable serialization
可以通过实现org.apache.wicket.serialize.ISerializer接口来定义自己的序列化机制，默认的序列化实现是org.apache.wicket.serialize.java.JavaSerializer;
在Application里设置自定义的Serializer
```java
@Override
public void init()
{
	super.init();
	getFrameworkSettings().setSerializer(yourSerializer);
}
```

- Page caching
session scoped cache(first level),application scoped cache(second level),Persistent store(disk);

The session-scoped cache is faster then the other memory levels but it contains only the pages used to serve the last request. Wicket allows us to set the maximum amount of memory allowed for the application-scoped cache and for the page store file. 

> cache到磁盘和二级cache大小都可以在Application里设置

persistent store size 
```java
@Override
public void init()
{
	super.init();
	getStoreSettings().setMaxSizePerSession(Bytes.kilobytes(500));
}

```

application-scoped cache size 
```java
@Override
public void init()
{
	super.init();
	getStoreSettings().setInmemoryCacheSize(50);
}
``` 

- Page expiration
Page instances are not kept in the user session forever. They can be discarded when the limit set with the StoreSettings.setMaxSizePerSession method is reached or (more often) when user session expires. 


When we ask Wicket for a page id corresponding to a page instance removed from the session, we bump into a PageExpiredException

在Application设置自定义的错误页面
```java
@Override
public void init()
{
	super.init();
	getApplicationSettings().setPageExpiredErrorPage(
				CustomExpiredErrorPage.class);
}
```


**Stateless pages**
In Wicket a page can be stateless only if it satisfies the following requirements:
1. it has been instantiated by Wicket (i.e. we don't create it with operator new) using a constructor with no argument or a constructor that takes as input a single PageParameters argument (class PageParameters will be covered in chapter 8).
2. All its children components (and behaviors) are in turn stateless, which means that their method isStateless must return true.

>Wicket 实例化Pages, 子Components也是stateless的

The first requirement implies that, rather than creating a page by hand, we should rely on Wicket's capability of resolving page instances, like we do when we use method setResponsePage(Class page).

In order to comply with the second requirement it could be helpful to check if all children components of a page are stateless. To do this we can leverage method visitChildren and the visitor pattern to iterate over components and test if their method isStateless actually returns true:
```java
@Override
protected void onInitialize() {
		super.onInitialize();
		visitChildren(new IVisitor<Component, Void>() {
			@Override
			public void component(Component component, IVisit<Void> arg1) {
				if(!component.isStateless())
		  			System.out.println("Component " + component.getId() + " is not stateless");
			}
		});
	}
```



