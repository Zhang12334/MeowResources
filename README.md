# MeowResources

**MeowResources** 是一个 Minecraft Bukkit 插件，可以强制玩家加载材质包，并在玩家拒绝加载材质包时踢出玩家。  

支持多语言，默认语言为简体中文。  

**MeowResources** is a Minecraft Bukkit plugin that forces players to load resource packs, and kicks players who refuse to load resource packs.

Supports multiple languages, default language is Simplified Chinese.

If you want to use other languages, please change the language code in the configuration file.

## 支持语言列表 / Supported Languages

| 语言 / Language   | 语言代码 / Language code |支持状态 / Support Status |
|--------------------|---------------------------|---------------------------|
| 简体中文 / Simplified Chinese | zh_cn | ✅ 支持 / Supported        |
| 繁体中文 / Traditional Chinese | zh_tc | ✅ 支持 / Supported |
| 英文 / English      | en | ✅ 支持 / Supported        |
| 日语 / Japanese | ja_jp | ❌ 不支持 / Not Supported |

欢迎联系开发者提交其他语言的翻译，您的名字将会被列入感谢列表！

Feel free to contact the developer to submit translations in other languages, and your name will be included in the thanks list!

> **注意**：要更改语言，请参阅配置文件
> **Note**: To change the language, please refer to the configuration file.

---

## 配置文件 / Configuration File

> **此处仅供展示说明，请勿复制使用！请以插件生成的配置文件为准！**  
> **This section is for display purposes only! Please do not copy it!**
> **Please use the configuration file generated by the plugin!**

```yaml

# 配置文件版本
# Config file version
config_version: 1.0

# 语言
# Language
language: zh_cn

# 未加载材质包的玩家被踢出时的提示信息
# Message when a player is kicked out because of not loading the material package
message: '您需要安装服务器材质包才能进入服务器!'

# 材质包下载链接，将会发送给玩家进行下载，需要直链
# Resourcepack download link, which will be sent to the player for download, requires a direct link
url: "https://www.google.com/minecraft.zip"

# 材质包 SHA-1 值
# Resourcepack SHA-1 value
sha1: ""

```

---

## 功能相关 / Feature Related

本插件仅支持 1.20.3 以下版本的协议，在 1.20.3 及以上的 Minecraft 版本上可能无法完全实现功能

本插件支持的 Minecraft 版本:1.17 - 1.20.2

本插件支持的 Java 版本: 17+

This plugin only supports versions of the protocol below 1.20.3, and may not be able to fully implement the function on Minecraft versions above 1.20.3

This plugin supports Minecraft versions: 1.17 - 1.20.2

This plugin supports Java versions: 17+

## 命令 / Commands

- 重载插件：/MeowResources reload
- Reload plugin: /MeowResources reload

## 下载 / Download
本插件为开源项目并免费提供下载使用！

您可以自行编译开发中的源代码或下载 Release 版本使用，出现问题可以提出 Issue！

同时您也可以在爱发电平台上赞助我，并通过加入QQ交流群以获得及时、迅速的技术支持与安装指导！赞助链接：https://afdian.com/a/NachoNeko_/

This plugin is an open-source project and is available for free download and use!

You can compile the source code being developed yourself or download the Release version. If you encounter any issues, feel free to raise an Issue!

You can also support me on the Afdian platform and join the QQ group for timely and efficient technical support and installation guidance. Link (Simplified Chinese only): https://afdian.com/a/NachoNeko_/

## 赞助价格表 / Pricing Table

- ¥25 元：获取插件技术支持 

  ¥25 CNY: Get technical support for the plugin.

- ¥200 元：获取插件技术支持 + 一次定制功能的机会

  ¥200 CNY: Get technical support + one opportunity for a custom feature.

## 兼容性测试 / Compatibility Testing

|        | 1.17 | 1.18 | 1.19 | 1.20 | 1.20.1 | 1.21 |
|--------|------|------|------|------|------|------|
| Purpur | ❓   | ❓  | ❓  | ✅   | ✅  | ❓   |
| Paper  | ❓   | ❓  | ❓  | ✅   | ✅  | ❓   |
| Spigot | ❓   | ❓  | ❓  | ✅   | ✅  | ❓   |
| Bukkit | ❓   | ❓  | ❓  | ✅   | ✅  | ❓   |

欢迎辅助开发者完善兼容性测试列表，您的名字将会被列入感谢列表！

Welcome to assist developers in improving the compatibility testing list, and your name will be included in the thanks list!

## 致谢 / Acknowledgments

| 名称 / Name | 贡献 / Contribution |
|-------------|---------------------|
| Zhang1233   | 开发 / Development   |

This plugin part of the English content is translated by ChatGPT and TONGYI Lingma.

## 许可 / License

请查看LICENSE.md

对于本项目的追加内容：
 - 允许在商业服务器中使用本插件
 - 禁止直接通过本插件及其衍生版本进行盈利（如出售插件本体等）

Please refer to LICENSE.md (Simplified Chinese only).

For additional content regarding this project:
 - Using this plugin on commercial servers is allowed.
 - Directly profiting from this plugin and its derivative versions is prohibited (e.g., selling the plugin itself).