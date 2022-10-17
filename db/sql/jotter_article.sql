create table jotter_article
(
    id                   int auto_increment
        primary key,
    article_title        varchar(255) null,
    article_content_html longtext     null,
    article_content_md   longtext     null,
    article_abstract     varchar(255) null,
    article_cover        varchar(255) null,
    article_date         datetime     null
)
    charset = utf8mb4;

INSERT INTO wj.jotter_article (id, article_title, article_content_html, article_content_md, article_abstract, article_cover, article_date) VALUES (1, '凉风有兴', '凉风有兴，秋月无边，而我思乡的情绪好比度日如年。虽然我风流倜傥玉树临风，但我还是有聪明的头脑和强健的臂腕。', '凉风有兴，秋月无边，而我思乡的情绪好比度日如年。虽然我风流倜傥玉树临风，但我还是有聪明的头脑和强健的臂腕。', '凉风有兴，秋月无边，而我思乡的情绪好比度日如年。', 'https://i.loli.net/2020/01/16/d2ZlKI1WRE4p7XB.png', '2020-01-13 21:14:27');
INSERT INTO wj.jotter_article (id, article_title, article_content_html, article_content_md, article_abstract, article_cover, article_date) VALUES (2, '爱你一万年', '<p>曾经有份真挚的爱情摆在我的面前，我没有珍惜，等到失去的时候才后悔莫急，人世间最痛苦的事莫过余此，如果上天在给我一次机会，我会对那个女孩说我爱你，如果要在这份爱上加个期限，我希望是一万年。</p>
', '曾经有份真挚的爱情摆在我的面前，我没有珍惜，等到失去的时候才后悔莫急，人世间最痛苦的事莫过余此，如果上天在给我一次机会，我会对那个女孩说我爱你，如果要在这份爱上加个期限，我希望是一万年。', '曾经有份真挚的爱情摆在我的面前，我没有珍惜，等到失去的时候才后悔莫及，人世间最痛苦的事莫过于此。', 'https://i.loli.net/2020/01/16/DdGBk1R3mj5er6v.png', '2020-01-16 00:00:00');
INSERT INTO wj.jotter_article (id, article_title, article_content_html, article_content_md, article_abstract, article_cover, article_date) VALUES (3, '《白卷》项目简介', '<p>这是一个简单的前后端分离项目，主要采用 Vue.js + SpringBoot 技术栈开发。</p>
<p>除了用作入门练习，我还希望该项目可以作为一些常见 Web 项目的脚手架，帮助大家简化搭建网站的流程。之所以叫白卷，是因为它从 0 开始，会随着时间的推移逐渐完善。</p>
<h1><a id="_6"></a>整体效果</h1>
<h2><a id="1_8"></a>1.首页</h2>
<p>作为展示页面，包括开发这个项目的主要参考资料、近期更新和 Slogan</p>
<p><img src="https://img-blog.csdnimg.cn/20190403215932913.png" alt="首页" /></p>
<h2><a id="2_14"></a>2.图书馆</h2>
<p>提供图书信息展示功能</p>
<p><img src="https://i.loli.net/2019/12/03/AGLbIupct68ThBD.png" alt="图书馆" /></p>
<h2><a id="3_20"></a>3.登录页面</h2>
<p><img src="https://i.loli.net/2019/04/14/5cb2fd5b78ae7.png" alt="登录页面" /></p>
<h2><a id="4_24"></a>4.后台管理</h2>
<p>包含 dashboard、内容管理、用户及权限管理等</p>
<p><img src="https://img-blog.csdnimg.cn/20191202200516251.png" alt="后台" /></p>
<h1><a id="_30"></a>技术栈</h1>
<h2><a id="1_32"></a>1.前端技术栈</h2>
<p>1.Vue<br />
2.ElementUI<br />
3.axios</p>
<h2><a id="2_38"></a>2.后端技术栈</h2>
<p>1.Spring Boot<br />
2.Spring Data + JPA<br />
3.MySQL<br />
4.Shiro</p>
<h1><a id="_45"></a>部署方法</h1>
<p>1.clone 项目到本地</p>
<p><code>git clone https://github.com/Antabot/White-Jotter</code></p>
<p>2.数据库脚本放在 <code>wj</code> 项目的根目录下，在MySQL中执行数据库脚本</p>
<p>3.数据库配置在 <code>wj</code> 项目的 <code>src\\main\\resources</code> 目录下的<code>application.properties</code> 文件中，mysql 版本为 8.0.15</p>
<p>4.在IntelliJ IDEA中运行 <code>wj</code> 项目，为了保证项目成功运行，可以右键点击 <code>pom.xml</code> 选择 maven -&gt; reimport ，并重启项目</p>
<p>至此，服务端就启动成功了，同时，运行 <code>wj-vue</code> 项目，访问 <code>http://localhost:8080</code> ，即可进入登录页面，默认账号是 <code>admin</code>，密码是 <code>123</code></p>
<p>如果要做二次开发，请继续看第五、六步。</p>
<p>5.进入到 <code>wj-vue</code> 目录中，在命令行依次输入如下命令：</p>
<pre><code class="lang-"># 安装依赖
npm install

# 在 localhost:8080 启动项目
npm run dev

</code></pre>
<p>由于在 <code>wj-vue</code> 项目中已经配置了端口转发，将数据转发到SpringBoot上，因此项目启动之后，在浏览器中输入 <code>http://localhost:8080</code> 就可以访问我们的前端项目了，所有的请求通过端口转发将数据传到 SpringBoot 中（注意此时不要关闭 SpringBoot 项目）。</p>
<p>6.最后可以用 <code>WebStorm</code> 等工具打开 <code>wj-vue</code>项目，继续开发，开发完成后，当项目要上线时，依然进入到 <code>wj-vue</code> 目录，然后执行如下命令：</p>
<pre><code class="lang-">npm run build
</code></pre>
<p>该命令执行成功之后， <code>wj-vue</code> 目录下生成一个 <code>dist</code> 文件夹，可以将该文件夹中的两个文件 <code>static</code> 和 <code>index.html</code> 拷贝到 <code>wj</code> 项目中 <code>resources/static/</code> 目录下，然后直接运行 <code>wj</code> 项目，访问 <code>http://localhost:8443</code> ，实际上是把前端打包后作为静态文件，但不推荐使用这种方式。</p>
<h1><a id="_82"></a>教程</h1>
<p>我在 CSDN 上分享了开发这个项目的教程，有兴趣的小伙伴可以点击下面的链接查看。</p>
<p>1.<a href="https://blog.csdn.net/Neuf_Soleil/article/details/88925013" target="_blank">项目简介</a></p>
<p>2.<a href="https://blog.csdn.net/Neuf_Soleil/article/details/88926242" target="_blank">使用 CLI 搭建 Vue.js 项目</a></p>
<p>3.<a href="https://blog.csdn.net/Neuf_Soleil/article/details/88955387" target="_blank">前后端结合测试（登录页面开发）</a></p>
<p>4.<a href="https://blog.csdn.net/Neuf_Soleil/article/details/89294300" target="_blank">数据库的引入</a></p>
<p>5.<a href="https://blog.csdn.net/Neuf_Soleil/article/details/89298717" target="_blank">使用 Element 辅助前端开发</a></p>
<p>6.<a href="https://learner.blog.csdn.net/article/details/89422585" target="_blank">前端路由与登录拦截器</a></p>
<p>7.<a href="https://learner.blog.csdn.net/article/details/89853305" target="_blank">导航栏与图书页面设计</a></p>
<p>8.<a href="https://learner.blog.csdn.net/article/details/92413933" target="_blank">数据库设计与增删改查</a></p>
<p>9.<a href="https://learner.blog.csdn.net/article/details/95310666" target="_blank">核心功能的前端实现</a></p>
<p>10.<a href="https://learner.blog.csdn.net/article/details/97619312" target="_blank">图片上传与项目的打包部署</a></p>
<p>11.<a href="https://learner.blog.csdn.net/article/details/100849732" target="_blank">用户角色权限管理模块设计</a></p>
<p>12.<a href="https://learner.blog.csdn.net/article/details/101121899" target="_blank">访问控制及其实现思路</a></p>
<p>13.<a href="https://learner.blog.csdn.net/article/details/102690035" target="_blank">使用 Shiro 实现用户信息加密与登录认证</a></p>
<p>14.<a href="https://learner.blog.csdn.net/article/details/102788866" target="_blank">用户认证方案与完善的访问拦截</a></p>
<p>15.<a href="https://learner.blog.csdn.net/article/details/103114893" target="_blank">动态加载后台菜单</a></p>
<p>16.<a href="https://learner.blog.csdn.net/article/details/103250775" target="_blank">功能级访问控制的实现</a></p>
<p>(持续更新中)</p>
<h1><a id="_120"></a>近期更新</h1>
<p>12-01 实现功能级权限控制<br />
11-30 利用 vue-elment-admin 项目完善后台界面设计<br />
11-17 重构项目，完成搭建后台基础界面，实现按角色加载菜单，取消前台访问限制<br />
04-27 使用前端拦截器，数据库迁移至 mysql 8.0.15，后台管理页面初始化<br />
04-13 完成图片的上传功能<br />
04-11 完成图书分类功能<br />
04-08 完成图书分页功能<br />
04-06 完成图书查询功能<br />
04-05 完成图书修改功能<br />
04-04 完成图书删除功能<br />
04-03 完成图书新增功能</p>
', '这是一个简单的前后端分离项目，主要采用 Vue.js + SpringBoot 技术栈开发。

除了用作入门练习，我还希望该项目可以作为一些常见 Web 项目的脚手架，帮助大家简化搭建网站的流程。之所以叫白卷，是因为它从 0 开始，会随着时间的推移逐渐完善。



# 整体效果

## 1.首页

作为展示页面，包括开发这个项目的主要参考资料、近期更新和 Slogan

![首页](https://img-blog.csdnimg.cn/20190403215932913.png)

## 2.图书馆

提供图书信息展示功能

![图书馆](https://i.loli.net/2019/12/03/AGLbIupct68ThBD.png)

## 3.登录页面

![登录页面](https://i.loli.net/2019/04/14/5cb2fd5b78ae7.png)

## 4.后台管理

包含 dashboard、内容管理、用户及权限管理等

![后台](https://img-blog.csdnimg.cn/20191202200516251.png)

# 技术栈

## 1.前端技术栈

1.Vue  
2.ElementUI  
3.axios   

## 2.后端技术栈

1.Spring Boot  
2.Spring Data + JPA 
3.MySQL  
4.Shiro

# 部署方法

1.clone 项目到本地

`git clone https://github.com/Antabot/White-Jotter`

2.数据库脚本放在 `wj` 项目的根目录下，在MySQL中执行数据库脚本  

3.数据库配置在 `wj` 项目的 `src\\main\\resources` 目录下的`application.properties` 文件中，mysql 版本为 8.0.15   

4.在IntelliJ IDEA中运行 `wj` 项目，为了保证项目成功运行，可以右键点击 `pom.xml` 选择 maven -> reimport ，并重启项目

至此，服务端就启动成功了，同时，运行 `wj-vue` 项目，访问 `http://localhost:8080` ，即可进入登录页面，默认账号是 `admin`，密码是 `123`

如果要做二次开发，请继续看第五、六步。

5.进入到 `wj-vue` 目录中，在命令行依次输入如下命令：  

```
# 安装依赖
npm install

# 在 localhost:8080 启动项目
npm run dev

```  

由于在 `wj-vue` 项目中已经配置了端口转发，将数据转发到SpringBoot上，因此项目启动之后，在浏览器中输入 `http://localhost:8080` 就可以访问我们的前端项目了，所有的请求通过端口转发将数据传到 SpringBoot 中（注意此时不要关闭 SpringBoot 项目）。

6.最后可以用 `WebStorm` 等工具打开 `wj-vue`项目，继续开发，开发完成后，当项目要上线时，依然进入到 `wj-vue` 目录，然后执行如下命令：  

```
npm run build
```  

该命令执行成功之后， `wj-vue` 目录下生成一个 `dist` 文件夹，可以将该文件夹中的两个文件 `static` 和 `index.html` 拷贝到 `wj` 项目中 `resources/static/` 目录下，然后直接运行 `wj` 项目，访问 `http://localhost:8443` ，实际上是把前端打包后作为静态文件，但不推荐使用这种方式。

# 教程

我在 CSDN 上分享了开发这个项目的教程，有兴趣的小伙伴可以点击下面的链接查看。  

1.[项目简介](https://blog.csdn.net/Neuf_Soleil/article/details/88925013)

2.[使用 CLI 搭建 Vue.js 项目](https://blog.csdn.net/Neuf_Soleil/article/details/88926242)

3.[前后端结合测试（登录页面开发）](https://blog.csdn.net/Neuf_Soleil/article/details/88955387)

4.[数据库的引入](https://blog.csdn.net/Neuf_Soleil/article/details/89294300)

5.[使用 Element 辅助前端开发](https://blog.csdn.net/Neuf_Soleil/article/details/89298717)

6.[前端路由与登录拦截器](https://learner.blog.csdn.net/article/details/89422585)

7.[导航栏与图书页面设计](https://learner.blog.csdn.net/article/details/89853305)

8.[数据库设计与增删改查](https://learner.blog.csdn.net/article/details/92413933)

9.[核心功能的前端实现](https://learner.blog.csdn.net/article/details/95310666)

10.[图片上传与项目的打包部署](https://learner.blog.csdn.net/article/details/97619312)

11.[用户角色权限管理模块设计](https://learner.blog.csdn.net/article/details/100849732)

12.[访问控制及其实现思路](https://learner.blog.csdn.net/article/details/101121899)

13.[使用 Shiro 实现用户信息加密与登录认证](https://learner.blog.csdn.net/article/details/102690035)

14.[用户认证方案与完善的访问拦截](https://learner.blog.csdn.net/article/details/102788866)

15.[动态加载后台菜单](https://learner.blog.csdn.net/article/details/103114893)

16.[功能级访问控制的实现](https://learner.blog.csdn.net/article/details/103250775)

(持续更新中)

# 近期更新
 
12-01 实现功能级权限控制  
11-30 利用 vue-elment-admin 项目完善后台界面设计  
11-17 重构项目，完成搭建后台基础界面，实现按角色加载菜单，取消前台访问限制  
04-27 使用前端拦截器，数据库迁移至 mysql 8.0.15，后台管理页面初始化  
04-13 完成图片的上传功能  
04-11 完成图书分类功能  
04-08 完成图书分页功能  
04-06 完成图书查询功能  
04-05 完成图书修改功能  
04-04 完成图书删除功能  
04-03 完成图书新增功能
', '白卷是一个简单的前后端分离项目，主要采用 Vue.js + SpringBoot 技术栈开发。', 'https://i.loli.net/2020/01/19/egDEfu5jXlJ6r3a.png', '2020-01-19 00:00:00');
