# imageIO-plugins
项目全部源代码来自于：[haraldk/TwelveMonkeys](https://github.com/haraldk/TwelveMonkeys)，本项目只是将原来代码的imageIO扩展部分进行简单的合并。合并的具体模块如下：
>- common-image、common-io、common-lang
- imageio-core、imageio-metadata
- imageio-bmp、imageio-jpeg、imageio-tiff、imageio-iff
- servlet(com.twelvemonkeys.servlet.image.IIOProviderContextListener)
	 
1. *在web系统中使用需要在web.xml中注册IIOProviderContextListener监听器，
2. *本代码需要在JDK　1.6+中运行
