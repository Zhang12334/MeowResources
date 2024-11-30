package com.meow.meowresources;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.plugin.java.JavaPlugin;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class MeowResources extends JavaPlugin implements Listener {

    // 一堆存储消息的变量
    private String startupMessage;
    private String shutdownMessage;
    private String nowusingversionMessage;
    private String checkingupdateMessage;
    private String checkfailedMessage;
    private String updateavailableMessage;
    private String updateurlMessage;
    private String oldversionmaycauseproblemMessage;
    private String nowusinglatestversionMessage;
    private String reloadedMessage;
    private String nopermissionMessage;
    private String declinedMessage;
    private String discardedMessage;
    private String faileddownloadMessage;
    private String failedreloadMessage;
    private String invalidurlMessage;
    private String kickMessage;
    @Override
    public void onEnable() {
        //bstats
        int pluginId = 24039;
        Metrics metrics = new Metrics(this, pluginId);
        // 首次加载进行英文提示
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getLogger().warning("The default language is Simplified Chinese. If you need English, you can configure it in the config.yml.");
        }
        saveDefaultConfig();
        loadLanguage(); // 加载语言配置
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(startupMessage);
        String currentVersion = getDescription().getVersion();
        getLogger().info(nowusingversionMessage + " v" + currentVersion);
        getLogger().info(checkingupdateMessage);
        // 异步更新检查
        new BukkitRunnable() {
            @Override
            public void run() {
                check_update();
            }
        }.runTaskAsynchronously(this);
    }

    private void loadLanguage() {
        FileConfiguration config = getConfig();
        String language = config.getString("language", "zh_cn");
        kickMessage = config.getString("message", "您需要安装服务器材质包才能进入服务器!");

        if ("zh_cn".equalsIgnoreCase(language)) {
            // 中文消息
            startupMessage = "MeowResources 已加载！";
            shutdownMessage = "MeowResources 已卸载！";
            nowusingversionMessage = "当前使用版本:";
            checkingupdateMessage = "正在检查更新...";
            checkfailedMessage = "检查更新失败，请检查你的网络状况！";
            updateavailableMessage = "发现新版本:";
            updateurlMessage = "新版本下载地址:";
            oldversionmaycauseproblemMessage = "旧版本可能会导致问题，请尽快更新！";
            nowusinglatestversionMessage = "您正在使用最新版本！";
            reloadedMessage = "配置文件已重载！";
            nopermissionMessage = "你没有权限执行此命令！";
            declinedMessage = "被踢出服务器, 原因: 玩家拒绝加载材质包";
            discardedMessage = "被踢出服务器, 原因: 资源包被玩家客户端丢弃";
            faileddownloadMessage = "被踢出服务器, 原因: 玩家下载资源包失败";
            failedreloadMessage = "被踢出服务器, 原因: 玩家资源包重载失败";
            invalidurlMessage = "被踢出服务器, 原因: 资源包下载地址无效";
        } else if("zh_tc".equalsIgnoreCase(language)) {
            // 繁体中文消息
            startupMessage = "MeowResources 已載入！";
            shutdownMessage = "MeowResources 已卸載！";
            nowusingversionMessage = "當前使用版本:";
            checkingupdateMessage = "正在檢查更新...";
            checkfailedMessage = "檢查更新失敗，請檢查你的網絡狀況！";
            updateavailableMessage = "發現新版本:";
            updateurlMessage = "下載更新地址:";
            oldversionmaycauseproblemMessage = "舊版本可能會導致問題，請盡快更新！";
            nowusinglatestversionMessage = "您正在使用最新版本！";
            reloadedMessage = "配置文件已重載！";
            nopermissionMessage = "你沒有權限執行此命令！";
            declinedMessage = "被踢出伺服器，原因: 玩家拒絕加載材質包";
            discardedMessage = "被踢出伺服器，原因: 材質包被玩家客戶端丟棄";
            faileddownloadMessage = "被踢出伺服器，原因: 玩家下載材質包失敗";
            failedreloadMessage = "被踢出伺服器，原因: 玩家材質包重載失敗";
            invalidurlMessage = "被踢出伺服器，原因: 材質包下載地址無效";
        } else if("en".equalsIgnoreCase(language)) {
            // English message
            startupMessage = "MeowResources has been loaded!";
            shutdownMessage = "MeowResources has been disabled!";
            nowusingversionMessage = "Currently using version:";
            checkingupdateMessage = "Checking for updates...";
            checkfailedMessage = "Update check failed, please check your network!";
            updateavailableMessage = "A new version is available:";
            updateurlMessage = "Download update at:";
            oldversionmaycauseproblemMessage = "Old versions may cause problems!";
            nowusinglatestversionMessage = "You are using the latest version!";
            reloadedMessage = "Configuration file has been reloaded!";
            nopermissionMessage = "You do not have permission to execute this command!";
            declinedMessage = "Kicked from the server, reason: Player declined to load the resource pack";
            discardedMessage = "Kicked from the server, reason: Resource pack was discarded by the player's client";
            faileddownloadMessage = "Kicked from the server, reason: Player failed to download the resource pack";
            failedreloadMessage = "Kicked from the server, reason: Player failed to reload the resource pack";
            invalidurlMessage = "Kicked from the server, reason: Invalid resource pack download URL";
        } else {
            getLogger().warning("Invalid language setting, using default language: zh_cn");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {         
            suggestions.add("reload");
        }
        return suggestions;
    }

    @EventHandler
    public void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent event) {
        // 获取玩家和资源包加载状态
        if (event.getStatus() == Status.DECLINED) {
            // 拒绝加载
            event.getPlayer().sendMessage(kickMessage);
            getLogger().info(event.getPlayer().getName() + declinedMessage);
        } else if (event.getStatus() == Status.DISCARDED) {
            // 被丢弃
            event.getPlayer().kickPlayer(kickMessage);
            getLogger().info(event.getPlayer().getName() + discardedMessage);
        } else if (event.getStatus() == Status.FAILED_DOWNLOAD) {
            // 资源包下载失败
            event.getPlayer().kickPlayer(kickMessage);
            getLogger().info(event.getPlayer().getName() + faileddownloadMessage);
        } else if (event.getStatus() == Status.FAILED_RELOAD) {
            // 资源包重载失败
            event.getPlayer().kickPlayer(kickMessage);
            getLogger().info(event.getPlayer().getName() + failedreloadMessage);
        } else if (event.getStatus() == Status.INVALID_URL) {
            // 资源包链接不合法
            event.getPlayer().kickPlayer(kickMessage);
            getLogger().info(event.getPlayer().getName() + invalidurlMessage);
        }
    }

    private void check_update() {
        // 获取当前版本号
        String currentVersion = getDescription().getVersion();
        // github加速地址，挨个尝试
        String[] githubUrls = {
            "https://ghp.ci/",
            "https://raw.fastgit.org/",
            ""
            //最后使用源地址
        };
        // 获取 github release 最新版本号作为最新版本
        // 仓库地址：https://github.com/Zhang12334/MeowResources
        String latestVersionUrl = "https://github.com/Zhang12334/MeowResources/releases/latest";
        // 获取版本号
        try {
            String latestVersion = null;
            for (String url : githubUrls) {
                HttpURLConnection connection = (HttpURLConnection) new URL(url + latestVersionUrl).openConnection();
                connection.setInstanceFollowRedirects(false); // 不自动跟随重定向
                int responseCode = connection.getResponseCode();
                if (responseCode == 302) { // 如果 302 了
                    String redirectUrl = connection.getHeaderField("Location");
                    if (redirectUrl != null && redirectUrl.contains("tag/")) {
                        // 从重定向URL中提取版本号
                        latestVersion = extractVersionFromUrl(redirectUrl);
                        break; // 找到版本号后退出循环
                    }
                }
                connection.disconnect();
                if (latestVersion != null) {
                    break; // 找到版本号后退出循环
                }
            }
            if (latestVersion == null) {
                getLogger().warning(checkfailedMessage);
                return;
            }
            // 比较版本号
            if (isVersionGreater(latestVersion, currentVersion)) {
                // 如果有新版本，则提示新版本
                getLogger().warning(updateavailableMessage + " v" + latestVersion);
                // 提示下载地址（latest release地址）
                getLogger().warning(updateurlMessage + " https://github.com/Zhang12334/MeowResources/releases/latest");
                getLogger().warning(oldversionmaycauseproblemMessage);
            } else {
                getLogger().info(nowusinglatestversionMessage);
            }
        } catch (Exception e) {
            getLogger().warning(checkfailedMessage);
        }
    }

    // 版本比较
    private boolean isVersionGreater(String version1, String version2) {
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");
        for (int i = 0; i < Math.max(v1Parts.length, v2Parts.length); i++) {
            int v1Part = i < v1Parts.length ? Integer.parseInt(v1Parts[i]) : 0;
            int v2Part = i < v2Parts.length ? Integer.parseInt(v2Parts[i]) : 0;
            if (v1Part > v2Part) {
                return true;
            } else if (v1Part < v2Part) {
                return false;
            }
        }
        return false;
    }
    
    private String extractVersionFromUrl(String url) {
        // 解析 302 URL 中的版本号
        int tagIndex = url.indexOf("tag/");
        if (tagIndex != -1) {
            int endIndex = url.indexOf('/', tagIndex + 4);
            if (endIndex == -1) {
                endIndex = url.length();
            }
            return url.substring(tagIndex + 4, endIndex);
        }
        return null;
    }

    @Override
    public void onDisable() {
        getLogger().info(shutdownMessage);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("MeowResources")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                // 检查是否是具有权限的用户
                if (sender.hasPermission("MeowResources.reload")) {
                    reloadConfig();
                    loadLanguage(); // 加载语言配置
                    sender.sendMessage(ChatColor.GREEN + reloadedMessage);
                    getLogger().info(reloadedMessage);
                } else {
                    sender.sendMessage(ChatColor.RED + nopermissionMessage);
                }
                return true;
            }
        }
        return false;
    }
}