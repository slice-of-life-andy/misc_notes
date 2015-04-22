 PageParameters
   org.apache.wicket.request.mapper.parameter.PageParameters.

   **PageParameters and bookmarkable pages**
   when a page is instantiated using a constructor with no argument or using a constructor that accepts only a PageParameters, Wicket will try to generate a static URL for it, with no session-relative informations. This kind of URL is called bookmarkable because it can be saved by the users as a bookmark and accessed at a later time.
   **Indexed parameters**
   Besides named parameters, Wicket also supports indexed parameters. These kinds of parameters are rendered as URL segments placed before named parameters.
   <application path>/foo/bar?1&baz=baz
   Just like named parameters also indexed parameters are handled by the PageParameters class. The methods provided by PageParameters for indexed parameters are set(int index, Object object) (to add/modify a parameter), remove(int index)(to remove a parameter) and get(int index) (to read a parameter).
   **Bookmarkable links**
   A link to a bookmarkable page can be built with the link component org.apache.wicket.markup.html.link.BookmarkablePageLink:
   ```java
   BookmarkablePageLink bpl=new   BookmarkablePageLink(PageWithParameters.class, pageParameters);
 ```
   **Automatically creating bookmarkable links with tag wicket:link**
   Bookmarkable pages can be linked directly inside markup files without writing any Java code. Using `<wicket:link>` tag we ask Wicket to automatically add bookmarkable links for the anchors wrapped inside it.
Please note that any link to the current page (aka self link) is disabled.
   **External links**
   When we need to dynamically generate external anchors, we can use link component org.apache.wicket.markup.html.link.ExternalLink.
   **Stateless links**
   Component Link has a stateful nature, hence it cannot be used with stateless pages. To use links with these kinds of pages Wicket provides the convenience org.apache.wicket.markup.html.link.StatelessLink component which is basically a subtype of Link with the stateless hint set to true.
   **Generating structured and clear URLs**
   - Mounting a single page
	     - mountPage("/pageMount", MountedPage.class);
   - Using parameter placeholders with mounted pages
	    - mountPage("/pageMount/${foo}/otherSegm",    MountedPageWithPlaceholder.class);
	    - Place holders can be declared as optional using the '#' character in place of '$':
  - Mounting a package
          - Wicket allows to mount all of the pages inside a package to a given path. Method mountPackage(String path, Class<T> pageClass) of class WepApplication will mount every page inside pageClass's package to the specified path.
          - mountPackage("/mountPackage", StatefulPackageMount.class);
   - Providing custom mapper context to request mappers
   - Controlling how page parameters are encoded with IPageParametersEncoder
   - Encrypting page URLs
